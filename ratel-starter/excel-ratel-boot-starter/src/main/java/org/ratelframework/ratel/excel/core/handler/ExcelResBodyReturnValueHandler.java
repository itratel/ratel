package org.ratelframework.ratel.excel.core.handler;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.ratelframework.ratel.excel.annotation.ExcelResBody;
import org.ratelframework.ratel.excel.annotation.ExcelField;
import org.ratelframework.ratel.excel.config.ExcelExportConfig;
import org.ratelframework.ratel.excel.core.formatter.ReturnValueFormatter;
import org.ratelframework.ratel.excel.enums.FileSuffix;
import org.ratelframework.ratel.excel.extend.ExtendExcelUtil;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author whd.java@gmail.com
 * @date 2020/03/21 23:49
 * @apiNote Describe the function of this class in one sentence
 */
@Slf4j
public class ExcelResBodyReturnValueHandler implements HandlerMethodReturnValueHandler {

    private ExcelExportConfig excelExportConfig;

    public ExcelResBodyReturnValueHandler() {
    }

    /**
     * Whether the given {@linkplain MethodParameter method return type} is
     * supported by this handler.
     * @param returnType the method return type to check
     * @return {@code true} if this handler supports the supplied return type;
     * {@code false} otherwise
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return AnnotationUtils.findAnnotation(returnType.getContainingClass(), ExcelResBody.class) != null
                || returnType.getMethodAnnotation(ExcelResBody.class) != null;
    }

    /**
     * Handle the given return value by adding attributes to the model and
     * setting a view or setting the
     * {@link ModelAndViewContainer#setRequestHandled} flag to {@code true}
     * to indicate the response has been handled directly.
     * @param returnValue the value returned from the handler method
     * @param returnType the type of the return value. This type must have
     * previously been passed to {@link #supportsReturnType} which must
     * have returned {@code true}.
     * @param mavContainer the ModelAndViewContainer for the current request
     * @param webRequest the current request
     * @throws Exception if the return value handling results in an error
     */

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        Assert.state(response != null, "No HttpServletResponse");
        if (!(returnValue instanceof Collection)) {
            String msg = "返回值类型必须是Collection类型";
            this.writeError(response, msg);
        } else {
            Collection<?> results = (Collection)returnValue;
            if (CollUtil.isEmpty(results)) {
                log.warn("返回值为空");
                String msg = "返回值为空";
                this.writeError(response, msg);
            } else {
                ExcelResBody export = returnType.getMethodAnnotation(ExcelResBody.class);
                List<String> alias = export.alias().length == 0 ? new ArrayList<>() : Arrays.asList(export.alias());
                List<String> fieldNames = export.fieldNames().length == 0 ? new ArrayList<>() : Arrays.asList(export.fieldNames());
                if (alias.size() != fieldNames.size()) {
                    String msg = "列名和字段名称长度不一致";
                    this.writeError(response, msg);
                } else {
                    List<Map<String, Object>> rows = this.returnValueHandle(results, fieldNames, alias);
                    this.export(response, fieldNames, alias, export, rows);
                }
            }
        }
    }

    private List<Map<String, Object>> returnValueHandle(Collection<?> results, List<String> fieldNames, List<String> alias) {
        Class<?> returnClass = this.getClass(results);
        List<Map<String, Object>> rows = new LinkedList<>();
        results.forEach((n) -> {
            Map<String, Field> fieldMap = this.getFieldMap(returnClass);
            Map<String, Object> result = new LinkedHashMap<>(16);
            Iterator var8 = fieldMap.entrySet().iterator();

            while(true) {
                while(true) {
                    Field field;
                    ExcelField excelField;
                    String fieldName;
                    String aliasName;
                    do {
                        do {
                            if (!var8.hasNext()) {
                                if (CollUtil.isNotEmpty(result)) {
                                    rows.add(result);
                                }

                                return;
                            }

                            Map.Entry<String, Field> fieldEntry = (Map.Entry)var8.next();
                            field = fieldEntry.getValue();
                            excelField = field.getAnnotation(ExcelField.class);
                            fieldName = this.getFieldName(fieldNames, field, excelField);
                            aliasName = this.getAlias(alias, fieldNames, fieldName, excelField);
                        } while(StrUtil.isBlank(fieldName));
                    } while(StrUtil.isBlank(aliasName));

                    Object original = ReflectUtil.getFieldValue(n, field);
                    if (excelField != null && !excelField.formatter().isAssignableFrom(ReturnValueFormatter.class)) {
                        Class<? extends ReturnValueFormatter> type = excelField.formatter();
                        Object value = ReflectUtil.newInstance(type, new Object[0]).format(n, original);
                        result.put(aliasName, value);
                    } else {
                        result.put(aliasName, original);
                    }
                }
            }
        });
        return rows;
    }

    private String getFieldName(List<String> fieldNames, Field field, ExcelField excelField) {
        if (excelField == null) {
            return field.getName();
        } else if (excelField.ignore()) {
            return null;
        } else {
            if (!fieldNames.contains(field.getName())) {
                fieldNames.add(field.getName());
            }

            return field.getName();
        }
    }

    private String getAlias(List<String> alias, List<String> fieldNames, String fieldName, ExcelField excelField) {
        if (excelField == null) {
            return this.getString(alias, fieldNames, fieldName);
        } else if (excelField.ignore()) {
            return null;
        } else if (StrUtil.isBlank(excelField.alias())) {
            return this.getString(alias, fieldNames, fieldName);
        } else {
            if (!alias.contains(excelField.alias())) {
                alias.add(excelField.alias());
            }

            return excelField.alias();
        }
    }

    private String getString(List<String> alias, List<String> fieldNames, String fieldName) {
        int index = fieldNames.indexOf(fieldName);
        if (index < 0) {
            return null;
        } else {
            if (!alias.contains(alias.get(index))) {
                alias.add(alias.get(index));
            }

            return alias.get(index);
        }
    }

    private List<String> getNeedExportFields(Class<?> returnClass) {
        Field[] fields = ReflectUtil.getFields(returnClass);
        return Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
    }

    private Map<String, Field> getFieldMap(Class<?> beanClass) {
        Field[] fields = ReflectUtil.getFields(beanClass);
        HashMap<String, Field> map = new LinkedHashMap<>(fields.length);
        Field[] var4 = fields;
        int var5 = fields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            map.put(field.getName(), field);
        }

        return map;
    }

    private Class<?> getClass(Collection<?> results) {
        Iterator var2 = results.iterator();
        if (var2.hasNext()) {
            Object result = var2.next();
            return result.getClass();
        } else {
            return null;
        }
    }

    private void writeError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(message);
        response.getWriter().flush();
    }

    private String getFileName(ExcelResBody export) throws UnsupportedEncodingException {
        return URLEncoder.encode(StrUtil.isBlank(export.fileName()) ? export.title() : export.fileName(), "UTF-8") + export.suffix().getName();
    }

    private void export(HttpServletResponse response, List<String> fieldNames, List<String> alias, ExcelResBody export, Collection<?> rows) throws IOException {
        String title = export.title();
        ExcelWriter writer = ExtendExcelUtil.getBigWriter(this.excelExportConfig.getRowAccessSize());
        writer.renameSheet(StrUtil.isBlank(export.sheetName()) ? title : export.sheetName());
        if (CollUtil.isEmpty(fieldNames)) {
            Class<?> returnClass = this.getClass(rows);
            fieldNames = this.getNeedExportFields(returnClass);
        }

        if (CollUtil.isEmpty(alias)) {
            alias = fieldNames;
        }

        for(int i = 0; i < alias.size(); ++i) {
            writer.addHeaderAlias(fieldNames.get(i), alias.get(i));
        }

        writer.merge(alias.size() - 1, title);
        writer.write(rows, true);
        String filename = this.getFileName(export);
        if (FileSuffix.XLSX.equals(export.suffix())) {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
        } else if (FileSuffix.XLS.equals(export.suffix())) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        }

        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        response.setHeader("filename", filename);
        response.setHeader("Access-Control-Expose-Headers", "filename");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

    public ExcelResBodyReturnValueHandler setExcelExportConfig(ExcelExportConfig excelExportConfig) {
        this.excelExportConfig = excelExportConfig;
        return this;
    }
}