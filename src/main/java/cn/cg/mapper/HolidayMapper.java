package cn.cg.mapper;

import cn.cg.bean.HolidayBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChenGeng on 2017/12/13.
 */
@Mapper
public interface HolidayMapper {

    public int mInsert(@Param("dateString") String dateString);

    public List<HolidayBean> mList();

    public int delete(@Param("dateString") String dateString);

    public HolidayBean mSearch(@Param("dateString") String dateString);

}
