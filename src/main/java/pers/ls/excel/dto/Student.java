package pers.ls.excel.dto;

import lombok.Data;
import pers.ls.excel.util.ExcelField;

/**
 * demo dto
 */
@Data
public class Student {

    @ExcelField(title = "age-title",sort = 2, dynamicFieldName = "age-dynamic")
    private Integer age;

    @ExcelField(title = "name-title",sort = 1, dynamicFieldName = "name-dynamic")
    private String name;

    private String address= "上海";
}
