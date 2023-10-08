package pers.ls.excel.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Documented
public @interface ExcelField {

    int sort() default 0;

    String title() default "";

    /**
     * 该字段用于和调用方的传参映射
     *
     * @return
     */
    String dynamicFieldName() default "";

}
