package cn.cg.config;

/**
 * Created by ChenGeng on 2018/2/12.
 */

import cn.cg.constants.GlobalProperties;
import cn.cg.tasks.KLineAddTask;
import cn.cg.tasks.LoadTradeDataTask;
import cn.cg.tasks.TingPaiStockTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class QuartzConfigration {

    Logger logger = LoggerFactory.getLogger(QuartzConfigration.class);

    @Scheduled(cron = "0 * * * * * ")
    public void loadTradeDetailTask() throws Exception{
        try{
            logger.info("开始执行加载股票交易详情任务");
            GlobalProperties.app.getBean(LoadTradeDataTask.class).tasks();
            logger.info("执行加载股票交易详情任务完成");
        }catch (Exception e){
            logger.error("执行加载股票交易详情任务失败");
        }
    }

    /**
     * 获取当前没有交易的股票列表
     */
    @Scheduled(cron = "0 * * * * * ")
    public void getCurStopStock(){
        try {
            logger.info("开始执行加载停牌股票任务");
            GlobalProperties.app.getBean(TingPaiStockTask.class).loadTingPaiStock();
            logger.info("执行加载停牌股票任务完成");
        }catch (Exception e){
            logger.error("执行加载停牌股票任务失败");
        }
    }

    /**
     * 补充K线
     */
    @Scheduled(cron = "0 * * * * * ")
    public void createKLineTask(){
        try {
            logger.info("开始执行生成K线任务");
            GlobalProperties.app.getBean(KLineAddTask.class).tasks();
            logger.info("执行生成K线任务完成");
        }catch (Exception e){
            logger.error("执行生成K线任务失败");
        }
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(5);
        return taskScheduler;
    }

}