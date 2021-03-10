package com.trace.dao.repository;

import com.trace.dao.entity.CodeManage;
import com.trace.dao.entity.CodeManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeManageMapper {
    int deleteByExample(CodeManageExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(CodeManage record);

    int insertSelective(CodeManage record);

    List<CodeManage> selectByExample(CodeManageExample example);

    CodeManage selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") CodeManage record, @Param("example") CodeManageExample example);

    int updateByExample(@Param("record") CodeManage record, @Param("example") CodeManageExample example);

    int updateByPrimaryKeySelective(CodeManage record);

    int updateByPrimaryKey(CodeManage record);
}