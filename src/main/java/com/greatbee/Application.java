package com.greatbee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

/**
 * Application
 * <p/>
 * Created by CarlChen on 2017/5/27.
 */
@SpringBootApplication(exclude = {SessionAutoConfiguration.class, DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
@ImportResource({"classpath:server.xml"})
@EnableCaching
public class Application {

    private static final String CONFIG_PATH="--configPath";
    public static String configPath = "";

    public static void main(String[] args) throws Exception {
        buildConfigPath(args);
        SpringApplication.run(Application.class, args).registerShutdownHook();
    }

    private static void buildConfigPath(String[] args){
        if(args.length<=0){
            return ;
        }
        for(int i=0;i<args.length;i++){
            if(args[i].contains(CONFIG_PATH)){
                configPath =args[i].split("=")[1];
                break;
            }
        }
    }

}
