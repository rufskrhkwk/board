package pegsystem.jdbc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;


/**
 * @Class Name	: SubMapper.java
 * @Description : @Mapper 와 구분하기 위한 annotation interface
 * 				  dataSource 2개를 사용할때 각각의 MapperConfigurer 가 적용되게 하려면 @Mapper 와 구분되는 annotation이 필요함.
 * 				  egovframework.rte.psl.dataaccess.mapper.Mapper.class 와 동일한 역할.  
 * 
 * @author	박진우(jwpark@pegsystem.co.kr)
 * @since	2018.04.11
 * @version	1.0
 * @see
 * Copyright (C) PEGSYSTEM <http://www.pegsystem.co.kr>
 * 
 * 
 * @Modification Information
 *	  Date		  Modifier		Comment
 *  ----------   ----------    -------------------
 *  2018.04.11	  박진우   		최초생성
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SubMapper {
	/**
	 * The value may indicate a suggestion for a logical mapper name, to be turned into a Spring bean in case of an autodetected component.
	 * 
	 * @return the suggested mapper name, if any
	 */
	String value() default "";
}
