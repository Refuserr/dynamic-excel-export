package pers.ls.excel.controller;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.ls.excel.dto.Student;
import pers.ls.excel.util.ExcelUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class ExcelController {

    @GetMapping("/dynamicExcelExport")
    public void dynamicExcelExport(@RequestParam("dynamicFields") List<String> dynamicFields, HttpServletResponse response) throws IllegalAccessException, IOException {
        List<Student> students = mockData();
        Workbook workbook = ExcelUtil.createWorkbook(dynamicFields, students, Student.class);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String filename = "cms_".concat(String.valueOf(System.currentTimeMillis()));
        response.setHeader("Content-disposition", "attachment;filename=" + filename + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    private List<Student> mockData() {
        Student student = new Student();
        student.setAge(12);
        student.setName("张三");
        return Arrays.asList(student);
    }
}
