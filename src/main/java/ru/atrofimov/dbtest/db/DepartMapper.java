package ru.atrofimov.dbtest.db;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.atrofimov.dbtest.db.model.Depart;
import ru.atrofimov.dbtest.db.model.DepartAdd;

public interface DepartMapper {

    @Select("select * from depart_add where depart_id = #{id}")
    List<DepartAdd> getDepartAdd(@Param("id") long departId);

    @Select("select d.*, h.PARENT_ID from DEPART d "
        + "left join HIERARCHY h on d.DEPART_ID = h.CHILD_ID "
        + "where d.DEPART_ID = #{id}")
    Depart findById(@Param("id") long departId);

}
