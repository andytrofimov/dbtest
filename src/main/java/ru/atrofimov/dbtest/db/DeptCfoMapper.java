package ru.atrofimov.dbtest.db;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ru.atrofimov.dbtest.db.model.DeptCfo;

public interface DeptCfoMapper {

    @Update("call CALC_DEPT_CFO()")
    void callDeptCfo();

    @Select("select * from dept_cfo")
    List<DeptCfo> getDeptCfo();

}
