package org.ratelframework.ratel.authorization.oauth2.server.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>RatelTokenEndPoint<p/>
 * RatelTokenEndPoint <br>
 *
 * @author whd.java@gmail.com
 * @date 2020/11/29 11:47
 * @since 1.0.0
 */
@RequiredArgsConstructor(onConstructor__=@Autowired)
public class RatelTokenEndPoint {

    private final ClientDetailsService clientDetailsService;

    private final TokenStore tokenStore;

    /**
     * 认证页面
     * @param modelAndView
     * @param error 表单登录失败处理回调的错误信息
     * @return ModelAndView
     */
    @GetMapping("/login")
    public ModelAndView require(ModelAndView modelAndView, @RequestParam(required = false) String error) {
        modelAndView.setViewName("ftl/login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}
