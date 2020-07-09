package ru.atrofimov.dbtest.db;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Utils {

    @SneakyThrows
    public static void executeScript(JdbcTemplate jdbcTemplate, String name) {
        String sql = IOUtils.toString(new ClassPathResource(name).getURL(), UTF_8);
        for (String s : sql.split(";")) {
            jdbcTemplate.execute(s);
        }
    }

}
