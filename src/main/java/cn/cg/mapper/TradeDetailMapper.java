package cn.cg.mapper;

import cn.cg.bean.TradeDetailBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChenGeng on 2018/2/1.
 */
@Repository("tradeDetailMapper")
public interface TradeDetailMapper {

    public int mInsertList(List<TradeDetailBean> list);

    public List<TradeDetailBean> queryTradeDetail(String stockCode, String tradeDate, String startTime, String endTime);

}
