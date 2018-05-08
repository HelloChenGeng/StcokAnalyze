package cn.cg.controller;

import cn.cg.bean.TingPaiStockBean;
import cn.cg.constants.GlobalProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

/**
 * Created by ChenGeng on 2017/12/9.
 */
@Controller
@RequestMapping("tingpai")
public class TingPaiController {

    @RequestMapping("preList")
    public ModelAndView preList(){
        ModelAndView mav = new ModelAndView("tingpai");
        List<TingPaiStockBean> list = GlobalProperties.TingPaiStockList;
        Collections.sort(list);
        mav.addObject("list", list);
         return mav;
    }

}
