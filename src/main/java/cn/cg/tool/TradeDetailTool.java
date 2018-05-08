package cn.cg.tool;

import cn.cg.bean.StockBean;
import cn.cg.bean.TradeDetailBean;
import cn.cg.constants.GlobalProperties;
import cn.cg.service.HolidayService;
import com.alibaba.fastjson.JSONArray;
import com.forms.beneform4j.util.Tool;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ChenGeng on 2018/1/18.
 */
public class TradeDetailTool {

    /**
     * 加载当前股票的交易明细
     *
     * @param code
     * @param page
     * @param list
     * @return 返回总共的页数
     */
    public static int loadDetail(String code, int page, List<TradeDetailBean> list){
        HolidayService holidayService = GlobalProperties.app.getBean("holidayService", HolidayService.class);
        String lastTradeDate = holidayService.lastTradeDate();
        if(list == null){
            return 0;
        }
        String marketFlag = null;
        if(code.startsWith("0") || code.startsWith("3")){
            marketFlag = "2";
        }else{
            marketFlag = "1";
        }
        String url = "http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/CompatiblePage.aspx?Type=OB&stk="+ code + marketFlag +"&Reference=json&limit=0&page=" + String.valueOf(page);
        String result = UrlLoader.loadAsString(url, "utf-8");
        int totalPageCount = 0;
        String pageString = result.substring(result.indexOf(":")+1, result.indexOf(","));
        totalPageCount = Integer.valueOf(pageString);
        result = result.substring(result.indexOf("["), result.lastIndexOf("]")+1);
        parseJson(list, result, code, lastTradeDate);
        if(totalPageCount == page){
            return page;
        }else{
            return loadDetail(code, page+1, list);
        }
    }

    /**
     * 加载当前股票的交易明细
     *
     * @param stockBean
     * @param list
     * @return 返回总共的页数
     */
    public static void loadDetail(StockBean stockBean, List<TradeDetailBean> list){
        if(!stockBean.getLastTimeDealTime().startsWith(Tool.DATE.getFormatDate(new Date(), "yyyy-MM-dd"))){
            stockBean.setLastTimePageCount(1);
        }
        String code = stockBean.getStockCode();
        int page = stockBean.getLastTimePageCount();
        HolidayService holidayService = GlobalProperties.app.getBean("holidayService", HolidayService.class);
        String lastTradeDate = holidayService.lastTradeDate();
        if(list == null){
            return;
        }
        String marketFlag = null;
        if(code.startsWith("0") || code.startsWith("3")){
            marketFlag = "2";
        }else{
            marketFlag = "1";
        }
        String url = "http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/CompatiblePage.aspx?Type=OB&stk="+ code + marketFlag +"&Reference=json&limit=0&page=" + String.valueOf(page);
        String result = UrlLoader.loadAsString(url, "utf-8");
        int totalPageCount = 0;
        String pageString = result.substring(result.indexOf(":")+1, result.indexOf(","));
        totalPageCount = Integer.valueOf(pageString);
        result = result.substring(result.indexOf("["), result.lastIndexOf("]")+1);
        parseJson(list, result, code, lastTradeDate);
        if(totalPageCount != page){
            TradeDetailBean lastTradeDetail = list.get(list.size()-1);
            stockBean.setLastTimePageCount(page+1);
            stockBean.setLastTimeDealTime(lastTradeDetail.getTradeDate() + " " + lastTradeDetail.getTradeTime());
            loadDetail(stockBean, list);
        }else{
            TradeDetailBean lastTradeDetail = list.get(list.size()-1);
            stockBean.setLastTimeDealTime(lastTradeDetail.getTradeDate() + " " + lastTradeDetail.getTradeTime());
        }
    }

    public static void filtTradeDetail(String lastTradeDateTime, List<TradeDetailBean> list){
        Iterator<TradeDetailBean> iterator = list.iterator();
        TradeDetailBean tradeDetail = null;
        while(iterator.hasNext()){
            tradeDetail = iterator.next();
            boolean isFlag = lastTradeDateTime.compareTo(tradeDetail.getTradeDate() + " " + tradeDetail.getTradeTime()) > 0;
            if(isFlag){
                iterator.remove();
            }
        }
    }

    private static void parseJson(List<TradeDetailBean> list, String jsonString, String code, String lastTradeDate){
        JSONArray array = JSONArray.parseArray(jsonString);
        for(int i = 0;i<array.size();i++){
            String dataString = array.getString(i);
            String dateString = dataString.substring(0, dataString.indexOf(","));
            dataString = dataString.substring(dataString.indexOf(",")+1);
            String priceString = dataString.substring(0, dataString.indexOf(","));
            dataString = dataString.substring(dataString.indexOf(",")+1);
            String dealCountString = dataString.substring(0,dataString.indexOf(","));
            String sellFlag = dataString.substring(dataString.lastIndexOf(",")+1);
            TradeDetailBean dealRecord = new TradeDetailBean();
            String timeString = dateString.substring(0,8);
            dealRecord.setCode(code);
            dealRecord.setAmt(Integer.valueOf(dealCountString));
            dealRecord.setPrice(Float.valueOf(priceString));
            dealRecord.setTradeDate(lastTradeDate);
            dealRecord.setTradeTime(timeString);
            dealRecord.setSellFlag(Integer.valueOf(sellFlag));
            list.add(dealRecord);
        }
    }

}
