package cn.gnetop.dcs.dao.provider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

	String column() default "";

	boolean humpToLine() default true;

}
