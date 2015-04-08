package com.youkelai.daike.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wujun on 4/7/15.
 */
@DatabaseTable(tableName = "contact")
public class Contact {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String mobilephoneNumber;

    @DatabaseField
    private String nickName;

}
