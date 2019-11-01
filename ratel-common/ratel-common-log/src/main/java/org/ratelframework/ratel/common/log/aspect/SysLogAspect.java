package org.ratelframework.ratel.common.log.aspect;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.ratelframework.ratel.admin.api.entity.SysLog;
import org.ratelframework.ratel.common.core.utils.SpringContextHolder;
import org.ratelframework.ratel.common.log.event.SysLogEvent;
import org.ratelframework.ratel.common.log.util.SysLogUtils;
import org.springframework.util.StopWatch;

/**
 * <p>
 *  SysLogAspect is used for recording log in Spring Async event notification, Mainly for
 *  improving log efficiency.
 * </p>
 * @author whd.java@mail.com
 * @date 2019/11/1 15:17
 * @version 0.0.1
 * @since 0.0.1
 */
@Aspect
@Slf4j
public class SysLogAspect {

    @SneakyThrows
    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint point,
                         org.ratelframework.ratel.common.log.annotation.SysLog sysLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

        SysLog logVo = SysLogUtils.getSysLog();
        logVo.setTitle(sysLog.value());
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
