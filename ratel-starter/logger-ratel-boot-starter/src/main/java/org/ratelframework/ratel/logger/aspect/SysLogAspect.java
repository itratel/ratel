package org.ratelframework.ratel.logger.aspect;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.ratelframework.ratel.common.core.utils.SpringContextHolder;
import org.ratelframework.ratel.logger.annotation.RatelLog;
import org.ratelframework.ratel.logger.event.SysLogEvent;
import org.ratelframework.ratel.logger.utils.SysLogUtil;
import org.ratelframework.ratel.upms.api.entity.SysLog;
import org.springframework.util.StopWatch;

/**
 * <p>
 *  SysLogAspect is used for recording log in Spring Async event notification, Mainly for
 *  improving log efficiency.
 * </p>
 * @author whd.java@mail.com
 * @date 2019/11/1 15:17
 * @since 1.0.0
 */
@Aspect
@Slf4j
public class SysLogAspect {

    @SneakyThrows
    @Around("@annotation(ratelLog)")
    public Object around(ProceedingJoinPoint point,
                         RatelLog ratelLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

        SysLog logVo = SysLogUtil.getSysLog();
        logVo.setTitle(ratelLog.value());
        // 发送异步日志事件
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object obj = point.proceed();
        stopWatch.stop();
        logVo.setTime(stopWatch.getTotalTimeMillis());
        SpringContextHolder.publishEvent(new SysLogEvent(logVo));
        return obj;
    }

}
