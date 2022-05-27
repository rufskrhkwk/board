package pegsystem.jdbc;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Class Name	: MybatisInterceptor.java
 * @Description : id가 특정 문자열로 끝나는 sql문을 가로채 캐릭터셋을 변경하는 Util Class
 * 
 * 				  sql문에 한글이 포함되어 있는 경우 DB의 캐릭터셋이 US-ASCII(7비트)이면 한글이 깨지기 떄문에 
 * 				  sql id에 특정 문자열(EXP, 꼬릿말)로 끝나는 sql문은 db로 보내기전 가로채서 캐릭터셋을 변경 한다.
 * 
 * 				  context-mapper.xml sqlSession bean을 생성할때 plug-in으로 추가 한다.
 * 				  in, out 캐릭터셋과 interceptor를 적용 할 꼬릿말을 생성자 argument로 지정해야 한다.
 * 
 * @author	박진우(jwpark@pegsystem.co.kr)
 * @since	2018.04.05
 * @version	1.0
 * @see
 * Copyright (C) PEGSYSTEM <http://www.pegsystem.co.kr>
 * 
 * 
 * @Modification Information
 *	  Date		  Modifier		Comment
 *  ----------   ----------    -------------------
 *  2018.04.04	  박진우   		최초생성
 */
@Intercepts({@Signature(type=StatementHandler.class, method="prepare", args={Connection.class})})
public class MybatisInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

	private String IN_CHARACTER_SET;
	private String OUT_CHARACTER_SET;
	private boolean CHARACTER_SET_CHECK;
	private String EXP;
	
	
	
	
	
	/**
	 * 생성자 : 캐릭터셋이 UTF-8이 아닌 경우, id가 exp로 끝나는 sql에만 인터셉터를 적용한다.
	 * @param in_character_set
	 * @param out_character_set
	 * @param exp
	 */
	public MybatisInterceptor(String in_character_set, String out_character_set, String exp) {
		this.IN_CHARACTER_SET = in_character_set;
		this.OUT_CHARACTER_SET = out_character_set;
		this.CHARACTER_SET_CHECK = "UTF-8".equalsIgnoreCase(in_character_set) ? false : true;
		this.EXP = exp;
	}
	
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if(CHARACTER_SET_CHECK) {
			StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
			MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
			String statementID = metaStatementHandler.getValue("delegate.mappedStatement.id").toString();
			String suffix = statementID.substring(statementID.length() - EXP.length(), statementID.length());
			if(EXP.equalsIgnoreCase(suffix)) {
				String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
				metaStatementHandler.setValue("delegate.boundSql.sql", new String(originalSql.getBytes(IN_CHARACTER_SET), OUT_CHARACTER_SET));
				
				logger.info("convert SQL : " + statementID);
			}
		}
	  
		return invocation.proceed();
	}

	
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}
	
	
	@Override
	public void setProperties(Properties arg0) {}
}