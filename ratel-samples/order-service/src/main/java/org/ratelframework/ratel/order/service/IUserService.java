package org.ratelframework.ratel.order.service;

import org.ratelframework.ratel.order.pojo.po.User;

/**
 * @author whd.java@gmail.com
 * @date 2019/11/10 17:52
 * @apiNote Describe the function of this class in one sentence
 */
public interface IUserService {

    User getOne();

    User selectOne();
}
