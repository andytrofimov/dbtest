package ru.atrofimov.dbtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.atrofimov.dbtest.db.DepartMapper;

@SpringBootTest
class DbTestApplicationTests {

    @Autowired
    DepartMapper departMapper;

    @Test
    void contextLoads() {
//        departMapper.setSchema();
        System.out.println(departMapper.findById(1));
    }

}
