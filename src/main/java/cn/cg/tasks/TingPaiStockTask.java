package cn.cg.tasks;

import cn.cg.bean.TingPaiStockBean;
import cn.cg.constants.GlobalProperties;
import cn.cg.tool.TimeFormatter;
import cn.cg.tool.UrlLoader;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ChenGeng on 2017/12/6.
 */
@Component
@Configurable
@EnableScheduling
public class TingPaiStockTask{

    static Logger logger = LoggerFactory.getLogger(TingPaiStockTask.class);

    public static void loadTingPaiStock() {
        logger.info("开始加载停牌股票信息");
        String url = "http://datainterface.eastmoney.com/EM_DataCenter/JS.aspx?type=FD&sty=SRB&st=0&sr=-1&p=1&ps=2000&mkt=1&fd=" + TimeFormatter.getDateString(true);
        String result = UrlLoader.loadAsString(url);
        result = result.substring(1,result.length()-1);
        JSONArray array = JSONArray.parseArray(result);
        List<TingPaiStockBean> list = new ArrayList<TingPaiStockBean>();
        for(int i = 0;i<array.size();i++){
            String string = (String) array.get(i);
//            string = string.substring(1,string.length()-1);
            String[] strings = string.split(",");
            TingPaiStockBean bean = new TingPaiStockBean();
            try{
                int temp = 0;
                if(strings.length==8){
                    temp = 1;
                }
                bean.setStockCode(strings[0]);
                bean.setStockName(strings[1]);
                bean.setStopDateTime(strings[2]);
                bean.setContinueDateTime(strings[3]);
                bean.setType(strings[4]);
                bean.setReason(strings[5]);
                bean.setMarketPlate(strings[6]);
                bean.setStopDate(strings[7]);
                if(temp == 0){
                    bean.setContinueDate(strings[8 - temp]);
                }

            }catch(Exception e){
                logger.error("获取停牌股票时出错，错误原因：{}，原字符串{}",e.getMessage(),string);
            }
            list.add(bean);
        }
        GlobalProperties.TingPaiStockList = list;
        logger.info("加载停牌股票信息成功");
    }

}