package com.zhenmei;


import ognl.Ognl;
import ognl.OgnlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

public class OgnlWrapper {

    private static Logger logger = LoggerFactory.getLogger(OgnlWrapper.class);

    private Map<String, Object> payLoad;

    public OgnlWrapper(Map<String, Object> payLoad) {
        this.payLoad = payLoad;
    }



    public <T> T get(String expression) {
        try {
            return (T) Ognl.getValue(expression, this.payLoad);
        } catch (OgnlException e) {
            logger.trace(String.format("get value with expression:[%s] due to error, return null instead of",
                    expression), e);
            return null;
        }
    }

    public Long getLong(String expression) {
        try {
            Object obj = Ognl.getValue(expression, this.payLoad);
            if (null == obj)
                return null;
            try {
                return Long.parseLong(obj.toString());
            } catch (NumberFormatException nfe) {
                logger.warn(String.format("get value with expression:[%s] due to error, return null. value[%s] cannot be cast to java.lang.Long",
                        expression,
                        obj.toString()));
                return null;
            }
        } catch (OgnlException e) {
            logger.trace(String.format("get value with expression:[%s] due to error, return null instead of",
                    expression), e);
            return null;
        }
    }

    public Integer getInt(String expression) {
        try {
            Object obj = Ognl.getValue(expression, this.payLoad);
            if (null == obj)
                return null;
            try {
                return Integer.parseInt(obj.toString());
            } catch (NumberFormatException nfe) {
                logger.warn(String.format("get value with expression:[%s] due to error, return null. value[%s] cannot be cast to java.lang.Integer",
                        expression,
                        obj.toString()));
                return null;
            }
        } catch (OgnlException e) {
            logger.trace(String.format("get value with expression:[%s] due to error, return null instead of",
                    expression), e);
            return null;
        }
    }



}
