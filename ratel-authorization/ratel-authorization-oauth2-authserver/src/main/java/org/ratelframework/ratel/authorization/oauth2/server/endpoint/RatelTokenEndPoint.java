package org.ratelframework.ratel.authorization.oauth2.server.endpoint;

import cn.hutool.core.map.MapUtil;
import lombok.RequiredArgsConstructor;
import org.ratelframework.ratel.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

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
     * @param modelAndView 模型视图
     * @param error 表单登录失败处理回调的错误信息
     * @return {@link ModelAndView}
     */
    @GetMapping("/login")
    public ModelAndView require(ModelAndView modelAndView, @RequestParam(required = false) String error) {
        modelAndView.setViewName("ftl/login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    /**
     * 确认授权页面
     * @param request 请求
     * @param session 会话
     * @param modelAndView 模型视图
     * @return {@link ModelAndView}
     */
    @GetMapping("/confirm_access")
    public ModelAndView confirm(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        @SuppressWarnings("unchecked")
        Map<String, Object> scopeList = (Map<String, Object>) request.getAttribute("scopes");
        modelAndView.addObject("scopeList", scopeList.keySet());

        Object auth = session.getAttribute("authorizationRequest");
        if (auth != null) {
            AuthorizationRequest authorizationRequest = (AuthorizationRequest) auth;
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
            modelAndView.addObject("app", clientDetails.getAdditionalInformation());
            modelAndView.addObject("user", SecurityUtils.getUser());
        }
        modelAndView.setViewName("ftl/confirm");
        return modelAndView;
    }
}
