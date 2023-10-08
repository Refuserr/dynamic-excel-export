package pers.ls.excel.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelEntity implements Comparable {

    private int sort;

    private String title;

    private Field field;

    @Override
    public int compareTo(Object o) {
        ExcelEntity entity = (ExcelEntity) o;
        return sort - entity.getSort();
    }
}
