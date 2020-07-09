package ru.atrofimov.dbtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.atrofimov.dbtest.db.DeptCfoMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.atrofimov.dbtest.db.Utils.executeScript;

@SpringBootTest
class DbTestApplicationTests {

    @Autowired
    DeptCfoMapper deptCfoMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void emptyTable() {
        executeScript(jdbcTemplate, "fixture-empty.sql");
        deptCfoMapper.callDeptCfo();
        assertThat(deptCfoMapper.getDeptCfo()).isEmpty();
    }

    @Test
    void defaultData() {
        executeScript(jdbcTemplate, "fixture-default.sql");
        deptCfoMapper.callDeptCfo();
        assertThat(deptCfoMapper.getDeptCfo()).hasSize(8);
    }

}
