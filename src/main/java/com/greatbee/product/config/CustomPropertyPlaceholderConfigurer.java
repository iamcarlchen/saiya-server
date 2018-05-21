package com.greatbee.product.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * CustomPropertyPlaceholderConfigurer
 *
 * @author xiaobc
 * @date 17/9/11
 */
public class CustomPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static Map<String,String> ctxPropMap;

//    @Override
//    public void setLocation(Resource location) {
//        if(StringUtil.isValid(Application.configPath)){
//            System.out.println("configPath========================================"+ Application.configPath);
//            FileSystemResource resource=new FileSystemResource(Application.configPath);
//            super.setLocation(resource);
//        }else{
//            System.out.println("configPath======================================== default config");
//            super.setLocation(location);
//        }
//    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropMap = new HashMap();
        for (Object key : props.keySet()){
            String keyStr = key.toString();
            String value = String.valueOf(props.get(keyStr));
            ctxPropMap.put(keyStr,value);
        }
    }

    public static String getCtxProp(String name) {
        return ctxPropMap.get(name);
    }

    public static Map<String, String> getCtxPropMap() {
        return ctxPropMap;
    }

}
