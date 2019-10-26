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

/**
 * 操作日志使用spring event异步入库
 *
 * @author whd.java@gmail.com
 */
@Aspect
@Slf4j
public class SysLogAspect {

    @Around("@annotation(sysLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point,
                         org.ratelframework.ratel.common.log.annotation.SysLog sysLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

        SysLog logVo = SysLogUtils.getSysLog();
        logVo.setTitle(sysLog.value());
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();
        logVo.setTime(endTime - startTime);
        SpringContextHolder.publishEvent(new SysLogEvent(logVo));
        return obj;
    }

}
