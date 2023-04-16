package com.yyr.config;


import com.yyr.dto.OperaLogForm;
import com.yyr.pojo.User;
import com.yyr.service.AuthService;
import com.yyr.service.CommonService8003;
import com.yyr.utils.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Title : LogCustomAspect
 * @Package
 * @Description :TODO（日志拦截器）
 * @Author:
 * @Date:
 */
@Aspect
@Component
public class LogCustomAspect {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private AuthService authService;
    @Autowired
    private CommonService8003 commonService8003;


    /**
     * 描述   定义切点
     *
     * @param
     * @return {@link null  }
     * @throws
     * @author
     */
    @Pointcut("@annotation(com.yyr.config.logCustom)")
    public void logAspect() {
    }

    /**
     * 描述  拦截返回值，进行记录
     *
     * @param joinPoint 切点，   returnValue 返回值
     * @return {@link null  }
     * @throws
     * @author
     */
    @AfterReturning("logAspect() && @annotation(logCustom)" )
    public void doLogCustom(JoinPoint joinPoint, logCustom logCustom) throws Exception {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        if(httpServletRequest.getRequestURI().contains("login")){
            return;
        }
        String token = httpServletRequest.getHeader("X-Token");
        User user = authService.getUserByToken(token);
        if (null == user) {
            return;
        }

        //添加操作日志
        String servletPath = httpServletRequest.getServletPath();
        String ip = IPUtil.GetIpAddress(httpServletRequest);
        OperaLogForm sysLog = new OperaLogForm();
        sysLog.setOperaTime(new Date());
        sysLog.setOperaLog(logCustom.description());
        sysLog.setOperaLogName(servletPath);
        sysLog.setIpaddr(ip);
        sysLog.setOperaLogOperaby(user.getUserId());
        sysLog.setOperaLogSysroleId(user.getSysRoleId());
        commonService8003.addSysOperaLog(sysLog);
    }


    public String getDescription(JoinPoint joinPoint) throws Exception {
        //类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //方法名
        String methodName = joinPoint.getSignature().getName();
        //参数
        Object[] arguments = joinPoint.getArgs();
        //通过反射获取示例对象
        Class targetClass = Class.forName(targetName);
        //通过实例对象方法数组
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            //判断方法名是不是一样
            if (method.getName().equals(methodName)) {
                //对比参数数组的长度
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    //获取注解里的日志信息
                    description = method.getAnnotation(logCustom.class).description();
                    break;
                }
            }
        }
        return description;
    }


    /**
     * 获取当前的request
     * 这里如果报空指针异常是因为单独使用spring获取request
     * 需要在配置文件里添加监听
     * <listener>
     * <listener-class>
     * org.springframework.web.context.request.RequestContextListener
     * </listener-class>
     * </listener>
     *
     * @return
     */
    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
}
