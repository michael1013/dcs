package cn.gnetop.dcs.system.helper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DcsComment {
	public String comment() default "";
	public String value() default "";
	public String description() default "";
	public boolean required() default false;
}
