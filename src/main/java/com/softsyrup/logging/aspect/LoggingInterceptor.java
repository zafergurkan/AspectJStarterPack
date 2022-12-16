package com.softsyrup.logging.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class LoggingInterceptor {

    private final static ObjectMapper mapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Around("@annotation(com.softsyrup.logging.aspect.LogMethod)")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        String methodName = pjp.getStaticPart().getSignature().toShortString();
        logMethodInvocationAndParameters(pjp);

        logger.info("method " + methodName +" starting");
        long startTime = System.nanoTime();
        try {
            result = pjp.proceed();
        }finally {
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            logger.info("method " + methodName +" end --- execution time as nanosecond -> " + executionTime);
            if (result != null)
            {
                logger.info("method output -> " + mapper.writeValueAsString(result));
            }
        }
        return result;
    }

    private void logMethodInvocationAndParameters(ProceedingJoinPoint jp) throws JsonProcessingException {
        String[] argNames = ((MethodSignature) jp.getSignature()).getParameterNames();
        Object[] values = jp.getArgs();
        Map<String, Object> params = new HashMap<>();
        if (argNames.length != 0) {
            for (int i = 0; i < argNames.length; i++) {
                params.put(argNames[i], values[i]);
            }
        }

        if (!params.isEmpty())
        {
            logger.info("method input -> " + mapper.writeValueAsString(params));
        }
    }
}
