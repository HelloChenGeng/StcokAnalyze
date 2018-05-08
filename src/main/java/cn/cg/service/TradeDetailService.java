package cn.cg.service;

import cn.cg.bean.KLineDotBean;
import cn.cg.bean.TradeDetailBean;
import cn.cg.mapper.TradeDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ChenGeng on 2018/2/6.
 */
@Service
public class TradeDetailService {

    @Autowired
    TradeDetailMapper mapper;

    public int mInsert(List<TradeDetailBean> list){
        return mapper.mInsertList(list);
    }

    public List<TradeDetailBean> queryTradeDetail(KLineDotBean dotBean){
        return mapper.queryTradeDetail(dotBean.getStockCode(), dotBean.getTradeDate(), dotBean.getStartTime(), dotBean.getEndTime());
    }

}
