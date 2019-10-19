package com.yrx.datasourcemanager.manager.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by r.x on 2019/10/13.
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    @Pointcut("execution(public * com.yrx.datasourcemanager.manager.api..*.*(..))")
    private void pointcut() {

    }

    @Before("pointcut()")
    private void beforeExeApi(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // ip
        String ip = getRemoteHost(request);
        // 请求方式
        String httpMethod = request.getMethod();
        // url附带的参数
        String urlParam = getUrlParam(request);
        // body 参数
        String bodyParam = "";
        if (httpMethod.equals("POST")) {
            bodyParam = JSON.toJSONString(joinPoint.getArgs());
        }
        Signature signature = joinPoint.getSignature();
        // 方法名
        String methodName = signature.getName();
        // 目标类
        String targetClass = joinPoint.getTarget().getClass().getName();
        log.info("before exe api: from:[{}], httpMethod:[{}], target:[{}], method:[{}], urlParam:[{}], bodyParam:[{}]",
                ip, httpMethod, targetClass, methodName, urlParam, bodyParam);
    }

    private String getUrlParam(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder paramKv = new StringBuilder();
        StringJoiner urlParam = new StringJoiner(", ");
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            for (String s : entry.getValue()) {
                paramKv.append(entry.getKey()).append("=").append(s);
            }
            urlParam.add(paramKv.toString());
            paramKv = new StringBuilder();
        }
        return urlParam.toString();
    }

    /**
     * 获取目标主机的ip
     *
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

}
