package yh;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@SpringBootConfiguration
@MapperScan("yh.dao")
@EnableAsync
public class AppStart {
    public static Logger logger = LoggerFactory.getLogger(AppStart.class);


    public static void main(String[] args) {
        SpringApplication.run(AppStart.class,args);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SpringBoot Start Success");
    }
}
