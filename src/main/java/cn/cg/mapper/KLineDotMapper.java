package cn.cg.mapper;

import cn.cg.bean.KLineDotBean;
import cn.cg.bean.StockBean;
import org.springframework.stereotype.Repository;

/**
 * Created by ChenGeng on 2018/2/3.
 */
@Repository("kLineDotMapper")
public interface KLineDotMapper {

    public int mInsert(KLineDotBean bean);

    public KLineDotBean[] lastKLineDots(StockBean stockBean);

    public KLineDotBean updateKLineDotBean(KLineDotBean dotBean);

}
