package org.ratelframework.ratel.excel.config;

import cn.hutool.core.collection.CollUtil;
import org.ratelframework.ratel.excel.core.handler.ExcelResBodyReturnValueHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author whd.java@gmail.com
 * @date 2020/03/21 23:49
 * @apiNote Describe the function of this class in one sentence
 */
@Configuration
public class ExcelExportHandler implements InitializingBean {

    @Resource
    private RequestMappingHandlerAdapter adapter;

    public ExcelExportHandler() {
    }

    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> list = this.adapter.getReturnValueHandlers();
        if (CollUtil.isEmpty(list)) {
            list = new ArrayList();
        }

        List<HandlerMethodReturnValueHandler> newList = new ArrayList();
        list.forEach((n) -> {
            if (n instanceof ExcelResBodyReturnValueHandler) {
                newList.add(0, n);
            } else {
                newList.add(n);
            }

        });
        this.adapter.setReturnValueHandlers(newList);
    }
}
