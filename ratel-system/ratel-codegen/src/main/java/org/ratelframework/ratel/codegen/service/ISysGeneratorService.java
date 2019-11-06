package org.ratelframework.ratel.codegen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ratelframework.ratel.codegen.entity.GenConfig;

import java.util.List;
import java.util.Map;

/**
 * @author whd.java@gmail.com
 * @date 2019/2/1
 */
public interface ISysGeneratorService {
	/**
	 * 生成代码
	 *
	 * @param tableNames 表名称
	 * @return
	 */
	byte[] generatorCode(GenConfig tableNames);

	/**
	 * 分页查询表
	 *
	 * @param tableName 表名
	 * @return
	 */
	IPage<List<Map<String, Object>>> queryPage(Page page, String tableName);
}
