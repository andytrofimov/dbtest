# Проект автотестирования БД

## Prerequisites:
* JDK 1.8
* заполнить url в application.yaml
* структура базы в structure.sql (до liquibase руки не дошли)

## Запуск:
Команда `./gradlew test` запустит тесты

# Запрос из первого задания
```
select dep.DEPART_NAME,
        coalesce(da.ADD_VALUE, (select tmp.ADD_VALUE
                                from (select d.*, h.PARENT_ID, da.ADD_VALUE
                                      from DEPART d
                                               left join HIERARCHY h on d.DEPART_ID = h.CHILD_ID
                                               left join (select * from DEPART_ADD da where ADD_TYPE = 'CFO') da
                                                         on da.DEPART_ID = d.DEPART_ID
                                     ) tmp
                                where tmp.ADD_VALUE is not null
                                  and ROWNUM = 1
                                connect by tmp.DEPART_ID = prior tmp.PARENT_ID
                                start with tmp.DEPART_ID = dep.DEPART_ID)) cfo
 from DEPART dep
          left join (select * from DEPART_ADD da where ADD_TYPE = 'CFO') da
                    on da.DEPART_ID = dep.DEPART_ID
 order by dep.DEPART_ID;
 ```
