package cn.cg.service;

import cn.cg.bean.KLineDotBean;
import cn.cg.bean.StockBean;
import cn.cg.bean.TingPaiStockBean;
import cn.cg.bean.TradeDetailBean;
import cn.cg.constants.GlobalProperties;
import cn.cg.form.AddStockForm;
import cn.cg.mapper.KLineDotMapper;
import cn.cg.mapper.StockMapper;
import cn.cg.mapper.TradeDetailMapper;
import cn.cg.tool.TradeDetailTool;
import com.forms.beneform4j.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ChenGeng on 2017/12/17.
 */
@Service("stockService")
public class StockService {

    @Autowired
    StockMapper mapper;

    @Autowired
    TradeDetailMapper tradeDetailMapper;

    @Autowired
    KLineService kLineService;

    @Autowired
    KLineDotMapper kLineMapper;

    public void updatePageAndTime(StockBean stockBean){
        mapper.mUpdatePageAndTime(stockBean);
    }

    public List<StockBean> listMonitorStock(){
        return mapper.selectAll();
    }

    /**
     * 判断该股票现在是否处于停牌状态
     * @param stockCode
     * @return
     */
    public boolean isStop(String stockCode){
        String stopDateTime;
        String continueDateTime;
        List<TingPaiStockBean> list = GlobalProperties.TingPaiStockList;
        if(list != null && list.size()!=0){
            for(TingPaiStockBean bean : list){
                if(bean.getStockCode().equals(stockCode)){
                    stopDateTime = bean.getStopDate() + " " + bean.getStopDateTime();
                    if(stopDateTime.compareTo(Tool.DATE.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss"))<0){
                        continueDateTime = bean.getContinueDate() + " " + bean.getContinueDateTime();
                        if(bean.getContinueDate()==null || bean.getContinueDate().equals(" ") || continueDateTime.compareTo(Tool.DATE.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss"))>0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 添加股票信息到监控列表中
     * @param form
     * @return
     */
    public boolean addStock(AddStockForm form){
        boolean result = true;
        StockBean stockBean = new StockBean();
        stockBean.setStockCode(form.getStockCode());
        stockBean.setStockName(form.getStockName());
        stockBean.setLastKLineDealDate(Tool.DATE.getFormatDate(new Date(),"yyyy-MM-dd"));

        List<TradeDetailBean> tradeDetails = new ArrayList<TradeDetailBean>();
        int totalPageCount = TradeDetailTool.loadDetail(stockBean.getStockCode(), 1,tradeDetails);
        TradeDetailBean lastTrade = tradeDetails.get(tradeDetails.size()-1);
        stockBean.setLastTimeDealTime(lastTrade.getTradeDate() + " " + lastTrade.getTradeTime());
        stockBean.setLastTimePageCount(totalPageCount);
        mapper.mInsert(stockBean);
        tradeDetailMapper.mInsertList(tradeDetails);
        List<KLineDotBean> dots = new ArrayList<KLineDotBean>();

        KLineDotBean dotBean5 = kLineService.createKLineDot(lastTrade.getTradeDate(), "15:00:00", 5, form.getStockCode());
        dotBean5.setEma12(form.getExpma512());
        dotBean5.setEma26(form.getExpma526());
        kLineService.setKLineField(tradeDetails, dotBean5);
        dotBean5.setkDotNo(1);
        dotBean5.setDif(form.getExpma512()-form.getExpma526());
        dotBean5.setDea(form.getDea5());

        kLineMapper.mInsert(dotBean5);

        KLineDotBean dotBean15 = kLineService.createKLineDot(lastTrade.getTradeDate(), "15:00:00", 15, form.getStockCode());
        dotBean15.setStockCode(form.getStockCode());
        dotBean15.setEma12(form.getExpma1512());
        dotBean15.setEma26(form.getExpma1526());
        kLineService.setKLineField(tradeDetails, dotBean15);
        dotBean15.setkDotNo(1);
        dotBean15.setDif(form.getExpma1512()-form.getExpma1526());
        kLineMapper.mInsert(dotBean15);

        KLineDotBean dotBean1 = kLineService.createKLineDot(lastTrade.getTradeDate(), "15:00:00", 30, form.getStockCode());
        dotBean1.setStockCode(form.getStockCode());
        dotBean1.setEma12(form.getExpma3012());
        dotBean1.setEma26(form.getExpma3026());
        kLineService.setKLineField(tradeDetails, dotBean1);
        dotBean1.setkDotNo(1);
        dotBean1.setDif(form.getExpma3012()-form.getExpma3026());
        kLineMapper.mInsert(dotBean1);

        return result;
    }

}
