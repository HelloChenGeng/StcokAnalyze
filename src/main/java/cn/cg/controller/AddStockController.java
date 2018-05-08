package cn.cg.controller;

import cn.cg.constants.GlobalProperties;
import cn.cg.form.AddStockForm;
import cn.cg.thread.AddStockThread;
import cn.cg.tool.UrlLoader;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ChenGeng on 2017/12/7.
 */
@Controller
@RequestMapping("addStock")
public class AddStockController {

    @RequestMapping("preAdd")
    public ModelAndView preAdd(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("addstock");
        return mav;
    }

    @RequestMapping("getInformation")
    @ResponseBody
    public JSONObject getInformation(HttpServletRequest request){
        String stockCode = request.getParameter("stockCode");
        String[] strings = getStockInformation(stockCode);
        JSONObject object = new JSONObject();
        object.put("stockName", strings[0]);
        object.put("nowPrice",strings[3]);
        return object;
    }

    @RequestMapping("add")
    public ModelAndView add(AddStockForm form){
        ModelAndView mav = new ModelAndView();
        form.setStockName(getStockInformation(form.getStockCode())[0]);
        mav.setViewName("success");
        mav.addObject("successText", "添加股票");
        AddStockThread runnable = GlobalProperties.app.getBean(AddStockThread.class);
        runnable.setForm(form);
        Thread thread = new Thread(runnable);
        thread.start();
        return mav;
    }

    private String[] getStockInformation(String stockCode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://hq.sinajs.cn/list=");
        if(stockCode.startsWith("3") || stockCode.startsWith("0")){
            stringBuilder.append("sz");
        }else {
            stringBuilder.append("sh");
        }
        stringBuilder.append(stockCode);
        String returnString = UrlLoader.loadAsString(stringBuilder.toString(), "gbk");
        returnString = returnString.substring(returnString.indexOf("\"")+1, returnString.lastIndexOf("\""));
        return returnString.split(",");
    }

}
