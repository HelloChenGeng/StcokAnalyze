package cn.cg.tasks;

import cn.cg.bean.KLineDotBean;
import cn.cg.bean.StockBean;
import cn.cg.bean.TradeDetailBean;
import cn.cg.service.HolidayService;
import cn.cg.service.KLineService;
import cn.cg.service.StockService;
import cn.cg.service.TradeDetailService;
import com.forms.beneform4j.util.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ChenGeng on 2018/2/7.
 */
@Component
public class KLineAddTask {

    static boolean isRunning = false;

    static Logger logger = LoggerFactory.getLogger(KLineAddTask.class);

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private StockService stockService;

    @Autowired
    private KLineService kLineService;

    @Autowired
    private TradeDetailService tradeDetailService;

    /**
     * 获取补充当前的K线列表
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
            kLineAddTask();
        }catch (Exception e){
            logger.error("加载交易数据时出错", e);
        }finally {
            if(getLock){
                isRunning = false;
            }
        }
    }

    private void kLineAddTask(){
        if(judgeTradeTime() == false){
            // 当前不是交易时间
            return;
        }
        List<StockBean> stockList = stockService.listMonitorStock();
        for(StockBean stockBean : stockList){
            if(stockService.isStop(stockBean.getStockCode())==false){
                // 当前股票没停牌，应该生成K线
                KLineDotBean[] newDots = kLineService.createKLineDotBean(stockBean);
                KLineDotBean[] oldDots = kLineService.lastKLineDot(stockBean);
                for(int i=0;i<3;i++){
                    KLineDotBean oldDot = oldDots[i];
                    KLineDotBean newDot = newDots[i];
                    List<TradeDetailBean> oldTradeDetails = tradeDetailService.queryTradeDetail(oldDot);
                    // 更新旧的点的数据
                    kLineService.setKLineField(oldTradeDetails, oldDot);
                    kLineService.updateKLineDot(oldDot);
                    if(!oldDot.getTradeTime().equals(newDot.getTradeTime())){
                        // 不是同一点，将新的点插入数据库中
                        newDot.setkDotNo(oldDot.getkDotNo()+1);
                        List<TradeDetailBean> newTradeDetails = tradeDetailService.queryTradeDetail(newDot);
                        //设置该点的相关交易数据
                        kLineService.setKLineField(newTradeDetails, newDot);
                        kLineService.calculateMACD(oldDot, newDot);
                        kLineService.insertKLineDot(newDot);
                        //TODO 更新旧点的相关MACD数据
                    } else {
                        // 当前还没更新到新的点上，需要更新旧的点的MACD数据
                        //TODO

                    }
                }
            }
        }
    }

    private boolean judgeTradeTime(){
        Date currentTime = new Date();
        if(holidayService.isHoliday(Tool.DATE.getFormatDate(currentTime,"yyyy-MM-dd"))){
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        if(hour == 13 || hour == 14 || hour==10 || (hour==11 && minute<31) || (hour == 9 && minute>29) || (hour==15 && minute<1)){
            return true;
        }
        else{
            return false;
        }
    }

}
