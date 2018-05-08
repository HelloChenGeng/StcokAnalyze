package cn.cg.mapper;

import cn.cg.bean.StockBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChenGeng on 2017/12/5.
 */
@Repository("stockMapper")
public interface StockMapper {

    public void mInsert(StockBean bean);

    public List<StockBean> selectAll();

    public void mUpdatePageAndTime(StockBean bean);

}
