package ru.atrofimov.dbtest.db.model;

import lombok.Data;

@Data
public class Depart {

    Long departId;
    Long parentId;
    String departName;

}
