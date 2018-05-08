package cn.cg.service;

import cn.cg.bean.KLineDotBean;
import cn.cg.bean.StockBean;
import cn.cg.bean.TradeDetailBean;
import cn.cg.mapper.KLineDotMapper;
import com.forms.beneform4j.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("kLineService")
public class KLineService {

    @Autowired
    KLineDotMapper mapper;

    @Autowired
    TradeDetailService tradeDetailService;

    /**
     * 查找该点的交易数据并更新这个点的相关数据到数据库中
     * @param dotBean
     */
    public void updateKLineDot(KLineDotBean dotBean){
        mapper.updateKLineDotBean(dotBean);
    }

    /**
     * 将该点插入数据库中
     * @param dotBean
     */
    public void insertKLineDot(KLineDotBean dotBean){
        //TODO

    }

    /**
     * 获取上一个节点
     * @param stockBean
     * @return
     */
    public KLineDotBean[] lastKLineDot(StockBean stockBean){
        return mapper.lastKLineDots(stockBean);
    }

    public KLineDotBean[] createKLineDotBean(StockBean stockBean){
        KLineDotBean[] returnBean = new KLineDotBean[3];

        returnBean[0] = createKLineDotBean(stockBean, 5);
        returnBean[1] = createKLineDotBean(stockBean, 15);
        returnBean[2] = createKLineDotBean(stockBean, 30);

        return returnBean;
    }

    /**
     * 创建一个K线点对象
     * @param stockBean
     * @param minute
     * @return
     */
    private KLineDotBean createKLineDotBean(StockBean stockBean, int minute){
        Date currentTime = new Date();
        KLineDotBean returnBean = createKLineDot(Tool.DATE.getFormatDate(currentTime, "yyyy-MM-dd"), Tool.DATE.getFormatDate(currentTime, "HH:mm:ss"), minute, stockBean.getStockCode());
        return returnBean;
    }


    /**
     * 设置这个点的开盘价、收盘价及成交量
     *
     * @param tradeDetails
     * @param dotBean
     */
    public void setKLineField(List<TradeDetailBean> tradeDetails, KLineDotBean dotBean){
        List<TradeDetailBean> list = filtTradeDetails(dotBean, tradeDetails);
        Collections.sort(list);
        dotBean.setStartPrice(Float.valueOf(list.get(0).getPrice()));
        dotBean.setEndPrice(Float.valueOf(list.get(list.size()-1).getPrice()));
        int amt = 0;
        for(TradeDetailBean bean : list){
            amt += bean.getAmt();
        }
        dotBean.setAmt(amt);
    }

    /**
     * 根据这个时间和类型生成一个KLine
     *
     * @param tradeDate
     * @param tradeTime 当前时间
     * @param type
     * @return
     */
    public KLineDotBean createKLineDot(String tradeDate, String tradeTime, int type, String stockCode) {
        KLineDotBean dotBean = new KLineDotBean();
        dotBean.setStockCode(stockCode);
        dotBean.setTradeDate(tradeDate);
        dotBean.setType(type);
        int hour = Integer.valueOf(tradeTime.substring(0,2));
        int minute = Integer.valueOf(tradeTime.substring(3,5));
        if (type == 1) {
            if (hour == 9 && minute == 30) {
                dotBean.setStartTime("09:00:00");
            } else {
                dotBean.setStartTime(tradeTime);
            }
            dotBean.setEndTime(tradeTime.substring(0, 6) + "59");
        } else {
            while(minute % type != 0){
                minute --;
            }
            if(hour == 9 && minute == 30){
                // 每天上午的第一个点
                dotBean.setStartTime("09:00:00");
                dotBean.setEndTime("09:" + (minute+type-1) + ":59");
            }else {
                if(hour==15 && minute==0){
                    dotBean.setEndTime("15:00:59");
                }else {
                    dotBean.setEndTime(numberFormat(hour) + ":" + numberFormat(minute+type-1) + ":59");
                }
                dotBean.setStartTime(numberFormat(hour) + ":" + numberFormat(minute) + ":00");
            }
        }
        if(dotBean.getStartTime().equals("09:00:00")){
            dotBean.setTradeTime("09:30:00");
        }else{
            dotBean.setTradeTime(dotBean.getStartTime());
        }
        return dotBean;
    }

    /**
     * 找到一个K线点对应的相关交易数据
     * @param dotBean
     * @param list
     * @return
     */
    private List<TradeDetailBean> filtTradeDetails(KLineDotBean dotBean, List<TradeDetailBean> list){
        List<TradeDetailBean> result = new ArrayList<TradeDetailBean>();
        if(list == null || list.size() == 0){
            return null;
        }
        for(TradeDetailBean tradeDetail : list){
            if(tradeDetail.getTradeTime().compareTo(dotBean.getEndTime())<0 && tradeDetail.getTradeTime().compareTo(dotBean.getStartTime())>0){
                result.add(tradeDetail);
            }
        }
        return result;
    }

    /**
     * 对数字进行格式化，对不满10的数字前面加上0
     *
     * @param number
     * @return
     */
    private String numberFormat(int number){
        if(number<10){
            return "0" + String.valueOf(number);
        }else{
            return String.valueOf(number);
        }
    }

}
