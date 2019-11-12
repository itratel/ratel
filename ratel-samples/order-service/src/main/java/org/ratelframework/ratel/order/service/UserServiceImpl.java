package org.ratelframework.ratel.order.service;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.ratelframework.ratel.common.annotation.PrimaryJdbcTemplate;
import org.ratelframework.ratel.common.annotation.PrimarySqlSessionTemplate;
import org.ratelframework.ratel.common.annotation.SubJdbcTemplate;
import org.ratelframework.ratel.common.annotation.SubSqlSessionTemplate;
import org.ratelframework.ratel.order.pojo.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    @PrimaryJdbcTemplate
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @SubJdbcTemplate
    private JdbcTemplate subJdbcTemplate;

    @Override
    public User getOne() {
        log.info("使用primarySqlSessionTemplate调用getOne()查询主数据源");
        return primarySqlSessionTemplate.selectOne("userMapper.getOne");
    }

    @Override
    public User selectOne() {
        log.info("使用subSqlSessionTemplate调用selectOne()查询副数据源");
        return subSqlSessionTemplate.selectOne("userMapper.selectOne");
    }

    @Override
    public User findOne() {
        log.info("使用primaryJdbcTemplate调用findOne()查询主数据源");
        return primaryJdbcTemplate.queryForObject("SELECT * FROM user LIMIT 1", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User queryOne() {
        log.info("使用subJdbcTemplate调用queryOne()查询副数据源");
        return subJdbcTemplate.queryForObject("SELECT * FROM user LIMIT 1", new BeanPropertyRowMapper<>(User.class));
    }
}
