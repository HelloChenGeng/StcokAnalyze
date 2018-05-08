package cn.cg;

import cn.cg.constants.GlobalProperties;
import cn.cg.tasks.TingPaiStockTask;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by ChenGeng on 2017/12/5.
 */
@SpringBootApplication
@MapperScan("cn.cg.mapper")
public class Starter {

    static Logger logger = LoggerFactory.getLogger(Starter.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Starter.class, args);
        GlobalProperties.app = applicationContext;
        TingPaiStockTask.loadTingPaiStock();
        logger.info("============= SpringBoot Start Success =============");
    }

}
