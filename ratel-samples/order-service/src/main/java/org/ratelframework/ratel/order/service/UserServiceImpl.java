package org.ratelframework.ratel.order.service;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.ratelframework.ratel.common.annotation.PrimarySqlSessionTemplate;
import org.ratelframework.ratel.common.annotation.SubSqlSessionTemplate;
import org.ratelframework.ratel.order.pojo.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author whd.java@gmail.com
 * @date 2019/11/10 17:54
 * @apiNote Describe the function of this class in one sentence
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    @PrimarySqlSessionTemplate
    private SqlSessionTemplate primarySqlSessionTemplate;

    @Autowired
    @SubSqlSessionTemplate
    private SqlSessionTemplate subSqlSessionTemplate;

    @Override
    public User getOne() {
        log.info("调用getOne()查询主数据源");
        return primarySqlSessionTemplate.selectOne("userMapper.getOne");
    }

    @Override
    public User selectOne() {
        log.info("调用selectOne()查询副数据源");
        return subSqlSessionTemplate.selectOne("userMapper.selectOne");
    }
}
