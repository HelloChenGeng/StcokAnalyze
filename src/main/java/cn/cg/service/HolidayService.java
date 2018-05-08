package cn.cg.service;

import cn.cg.bean.HolidayBean;
import cn.cg.mapper.HolidayMapper;
import cn.cg.tool.TimeFormatter;
import com.forms.beneform4j.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by ChenGeng on 2017/12/13.
 */
@Service("holidayService")
public class HolidayService {

    @Autowired
    HolidayMapper mapper;

    public boolean insertHoliday(String dateString){
        return mapper.mInsert(dateString)==0?false:true;
    }

    public List<HolidayBean> mList(){
        return mapper.mList();
    }

    public int delete(String dateString){
        return mapper.delete(dateString);
    }

    /**
     * 判断一天是否为节假日
     * @param dateString
     * @return
     */
    public boolean isHoliday(String dateString){
        Date date = TimeFormatter.getDate(dateString);
        boolean result;
        if(date.getDay()==0 || date.getDay()==6){
            result = true;
        }
        if(result = false){
            result = mapper.mSearch(dateString)==null?false:true;
        }
        return result;
    }

    /**
     * 获取上一个交易日的日期
     *
     * @return
     */
    public String lastTradeDate(){
        Date date = new Date();
        while(true){
            if(date.getDay() != 0 && date.getDay() != 6){
                if(null == mapper.mSearch(Tool.DATE.getFormatDate(date,"yyyy-MM-dd"))){
                    return Tool.DATE.getFormatDate(date, "yyyy-MM-dd");
                }
            }
            date = Tool.DATE.dateCalculate(date, 0,0, -1);
        }

    }

}
