package cn.cg.mapper;

import cn.cg.bean.TradeDetailBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChenGeng on 2018/2/1.
 */
@Repository("tradeDetailMapper")
public interface TradeDetailMapper {

    int mInsertList(List<TradeDetailBean> list);

    List<TradeDetailBean> queryTradeDetail(String stockCode, String tradeDate, String startTime, String endTime);

    void delete(String stockCode);

}
