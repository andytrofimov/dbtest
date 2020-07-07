package ru.atrofimov.dbtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("ru.atrofimov.dbtest")
public class DbTestApplication {

    public static void main(String[] args) {

    }

}
