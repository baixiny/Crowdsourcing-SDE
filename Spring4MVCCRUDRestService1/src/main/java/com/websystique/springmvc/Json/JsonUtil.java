package com.websystique.springmvc.Json;

import java.io.OutputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.springframework.core.annotation.AnnotationUtils;

public class JsonUtil {

    private static Log log = LogFactory.getLog(JsonUtil.class);
    
    private static ObjectMapper objectMapper = null;
    
    static {
        
        objectMapper = new ObjectMapper();
        
        //objectMapper.setDateFormat(new SimpleDateFormat(FormatUtil.DATE_FORMAT_LONG));
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
    }

    /*
    public static JsonUtil getInstance() {
        
        if (instance == null) {
            synchronized (JsonUtil.class) {
                if (instance == null) {
                    instance = new JsonUtil();
                }
            }
        }
        
        return instance;
    }
    */
    
    public static String stringify(Object object) {
    
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    public static String stringify(Object object, String... properties) {
    
        try {
            return objectMapper
                    .writer(new SimpleFilterProvider().addFilter(
                            AnnotationUtils.getValue(
                                AnnotationUtils.findAnnotation(object.getClass(), JsonFilter.class)).toString(), 
                                SimpleBeanPropertyFilter.filterOutAllExcept(properties)))
                    .writeValueAsString(object);    
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    public static void stringify(OutputStream out, Object object) {
        
        try {
            objectMapper.writeValue(out, object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    
    public static void stringify(OutputStream out, Object object, String... properties) {
        
        try {
            objectMapper
                .writer(new SimpleFilterProvider().addFilter(
                        AnnotationUtils.getValue(
                            AnnotationUtils.findAnnotation(object.getClass(), JsonFilter.class)).toString(), 
                            SimpleBeanPropertyFilter.filterOutAllExcept(properties)))
                .writeValue(out, object);    
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    
    public static <T> T parse(String json, Class<T> clazz) {
        
        if (json == null || json.length() == 0) {
            return null;
        }
        
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        
        return null;
    }
    
}