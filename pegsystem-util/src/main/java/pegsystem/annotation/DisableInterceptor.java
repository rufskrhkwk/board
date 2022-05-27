package pegsystem.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Class Name	: DisableInterceptor.java
 * @Description : 인터페이스를 회피하기 위한 custom annotaion 
 * 
 * @author	박진우(jwpark@pegsystem.co.kr)
 * @since	2018.06.01
 * @version	1.0
 * @see
 * Copyright (C) PEGSYSTEM <http://www.pegsystem.co.kr>
 * 
 * 
 * @Modification Information
 *	  Date		  Modifier		Comment
 *  ----------   ----------    -------------------
 *  2018.06.01	  박진우   		최초생성
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DisableInterceptor {

}
