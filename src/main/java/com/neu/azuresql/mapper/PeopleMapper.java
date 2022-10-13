package com.neu.azuresql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.azuresql.pojo.po.People;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author neu
 * @since 2022年10月13日
 */
@Mapper
public interface PeopleMapper extends BaseMapper<People> {

}
