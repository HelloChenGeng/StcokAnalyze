package cn.cg.controller;

import cn.cg.bean.HolidayBean;
import cn.cg.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenGeng on 2017/12/14.
 */
@Controller
@RequestMapping("holiday")
public class HolidayController {

    @Autowired
    HolidayService service;

    @RequestMapping("list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("holiday");
        List<Integer> dayList = new ArrayList<Integer>();
        for(int i=1;i<=31;i++){
            dayList.add(i);
        }
        mav.addObject("dayList", dayList);
        List<HolidayBean> holidays = service.mList();
        mav.addObject("holidays", holidays);
        return mav;
    }

    @RequestMapping("add")
    public ModelAndView add(String year, String month, String day){
        if(month.length()==1){
            month = "0" + month;
        }
        if(day.length()==1){
            day = "0" + day;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(year).append("-").append(month).append("-").append(day);
        service.insertHoliday(sb.toString());
        return list();
    }

    @RequestMapping("delete")
    public ModelAndView delete(String dateString){
        service.delete(dateString);
        return list();
    }


}
