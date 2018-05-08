package cn.cg.tasks;

import cn.cg.bean.StockBean;
import cn.cg.bean.TradeDetailBean;
import cn.cg.mapper.TradeDetailMapper;
import cn.cg.service.HolidayService;
import cn.cg.service.StockService;
import cn.cg.service.TradeDetailService;
import cn.cg.tool.TradeDetailTool;
import com.forms.beneform4j.util.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ChenGeng on 2018/2/5.
 */
@Configuration
@Component // 此注解必加
@EnableScheduling // 此注解必加
public class LoadTradeDataTask {

    static Logger logger = LoggerFactory.getLogger(LoadTradeDataTask.class);

    @Autowired
    StockService stockService;

    @Autowired
    HolidayService holidayService;

    @Autowired
    TradeDetailService tradeDetailService;

    static boolean isRunning = false;

    /**
     * 获取当前没有交易的股票列表
     */
    public void tasks(){
        boolean getLock = false;
        try{
            if(isRunning == true){
                return;
            }else{
                isRunning = true;
                getLock = true;
            }
            if(holidayService.isHoliday(Tool.DATE.getFormatDate(new Date(), "yyyy-MM-dd"))){
                return;
            }
            loadTradeData();
        }catch (Exception e){
            logger.error("加载交易数据时出错", e);
        }finally {
            if(getLock){
                isRunning = false;
            }
        }
    }

    private void loadTradeData() throws Exception{
        List<StockBean> stockBeans = stockService.listMonitorStock();
        if(stockBeans == null || stockBeans.size()==0){
            return ;
        }
        for(StockBean stockBean : stockBeans){
            List<TradeDetailBean> tradeDetails = new ArrayList<TradeDetailBean>();
            String lastDealTime = stockBean.getLastTimeDealTime();
            TradeDetailTool.loadDetail(stockBean, tradeDetails);
            TradeDetailTool.filtTradeDetail(lastDealTime, tradeDetails);
            stockService.updatePageAndTime(stockBean);
            tradeDetailService.mInsert(tradeDetails);
        }
    }

}
