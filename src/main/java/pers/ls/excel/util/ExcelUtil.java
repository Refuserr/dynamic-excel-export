package pers.ls.excel.util;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.util.*;

public final class ExcelUtil<T> {

    public static <T> Workbook createWorkbook(List<String> fields, List<T> datas, Class<T> tClass) throws IllegalAccessException {
        Map<String, Field> map = getExcelEntity(fields, tClass);
        List<String> actualTitles = new ArrayList<>(map.keySet());
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("export");
        int rowIndex = 0;
        Row headRow = sheet.createRow(rowIndex++);
        for (int i = 0; i < actualTitles.size(); i++) {
            Cell cell = headRow.createCell(i);
            cell.setCellValue(actualTitles.get(i));
        }
        //设置数据
        for (T data : datas) {
            Row row = sheet.createRow(rowIndex++);
            for (int i = 0; i < actualTitles.size(); i++) {
                String title = actualTitles.get(i);
                Cell cell = row.createCell(i);
                Field field = map.get(title);
                field.setAccessible(true);
                Object o = field.get(data);
                cell.setCellValue(o.toString());
            }
        }
        return workbook;
    }


    public static <T> Map<String, Field> getExcelEntity(List<String> fields, Class<T> tClass) {
        Class<?> aClass = tClass;
        Field[] declaredFields = aClass.getDeclaredFields();
        Map<String, Field> excelFieldMap = new LinkedHashMap<>();//采用链表的原因是,需要通过keySet获取title的顺序序列
        TreeSet<ExcelEntity> excelEntities = new TreeSet();
        for (Field field : declaredFields) {
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if (excelField == null) {
                continue;
            }
            String dynamicFieldName = excelField.dynamicFieldName();
            if (CollectionUtil.isNotEmpty(fields)) {
                if (!fields.contains(dynamicFieldName)) {
                    continue;
                }
            }
            excelEntities.add(ExcelEntity.builder()
                    .sort(excelField.sort())
                    .title(excelField.title())
                    .field(field)
                    .build());
        }
        for (ExcelEntity entity : excelEntities) {
            excelFieldMap.put(entity.getTitle(), entity.getField());
        }
        return excelFieldMap;
    }
}
