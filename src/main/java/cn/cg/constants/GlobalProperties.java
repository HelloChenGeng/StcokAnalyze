package cn.cg.constants;

import cn.cg.bean.TingPaiStockBean;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by ChenGeng on 2017/12/7.
 */
public class GlobalProperties {

    public static List<TingPaiStockBean> TingPaiStockList = null;

    public static ApplicationContext app;

    public static int MAX_TIME_OFFSET = 2;
}
