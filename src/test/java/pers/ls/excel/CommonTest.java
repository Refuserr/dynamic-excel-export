package pers.ls.excel;

import org.junit.jupiter.api.Test;
import pers.ls.excel.util.ExcelEntity;

import java.util.TreeSet;

public class CommonTest {

    @Test
    public void sortTest() {
        ExcelEntity e1 = ExcelEntity.builder()
                .sort(2)
                .title("e1")
                .build();
        ExcelEntity e2 = ExcelEntity.builder()
                .sort(1)
                .title("e2")
                .build();
        TreeSet<ExcelEntity> excelEntities = new TreeSet();
        excelEntities.add(e1);
        excelEntities.add(e2);
        for (ExcelEntity e : excelEntities) {
            System.err.println(e.getTitle());
        }
    }

}
