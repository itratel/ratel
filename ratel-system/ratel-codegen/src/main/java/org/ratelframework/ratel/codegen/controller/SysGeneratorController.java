package org.ratelframework.ratel.codegen.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.ratelframework.ratel.codegen.entity.GenConfig;
import org.ratelframework.ratel.codegen.service.ISysGeneratorService;
import org.ratelframework.ratel.common.core.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 代码生成器
 *
 * @author whd.java@gmail.com
 * @date 2019/2/1
 */
@RestController
@AllArgsConstructor(onConstructor__={@Autowired})
@RequestMapping("/generator")
public class SysGeneratorController {

	private final ISysGeneratorService sysGeneratorService;

	/**
	 * 列表
	 *
	 * @param tableName 参数集
	 * @return 数据库表
	 */
	@GetMapping("/page")
	public ResponseResult<IPage> list(Page page, String tableName) {
		return ResponseResult.ok(sysGeneratorService.queryPage(page, tableName));
	}

	/**
	 * 生成代码
	 */
	@PostMapping("/code")
	@SneakyThrows
	public void code(@RequestBody GenConfig genConfig, HttpServletResponse response) {
		byte[] data = sysGeneratorService.generatorCode(genConfig);

		response.reset();
		response.setHeader("Content-Disposition", String.format("attachment; filename=%s.zip", genConfig.getTableName()));
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}
}
