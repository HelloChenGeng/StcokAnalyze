package cn.cg.mapper;

import cn.cg.bean.KLineDotBean;
import cn.cg.bean.StockBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChenGeng on 2018/2/3.
 */
@Repository("kLineDotMapper")
public interface KLineDotMapper {

    int mInsert(KLineDotBean bean);

    List<KLineDotBean> lastKLineDots(StockBean stockBean);

    KLineDotBean updateKLineDotBean(KLineDotBean dotBean);

    KLineDotBean getLastKlineDotBean(KLineDotBean newDot);

    void delete(String stockCode);

}
