package peg.core.spring;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import egovframework.rte.psl.dataaccess.mapper.MapperConfigurer;
import pegsystem.jdbc.MybatisInterceptor;
import pegsystem.jdbc.RefreshableSqlSessionFactoryBean;

@Configuration
public class ContextMapper {
	

	@Bean
	public MybatisInterceptor mybatisInterceptor(@Value("${in.charaterSet}")String in_character_set
												,@Value("${out.charaterSet}")String out_character_set
												,@Value("${mybatis.interceptor.exp}")String exp) {
		return new MybatisInterceptor(in_character_set, out_character_set, exp);
	}
	
	
	@Bean
	public MapperConfigurer mainMapperConfigurer(){
		MapperConfigurer mapperConfigurer = new MapperConfigurer();
		mapperConfigurer.setBasePackage("peg");
		mapperConfigurer.setAnnotationClass(egovframework.rte.psl.dataaccess.mapper.Mapper.class);
		mapperConfigurer.setSqlSessionFactoryBeanName("mainSqlSessionFactoryBean");
		mapperConfigurer.setSqlSessionTemplateBeanName("mainSqlSessionTemplate");
		return mapperConfigurer;
	}
	
	@Bean
	public RefreshableSqlSessionFactoryBean mainSqlSessionFactoryBean(DataSource mainDataSource
																	 ,MybatisInterceptor mybatisInterceptor
																	 ,@Value("${main.db.type}")String db_type) throws IOException {
		PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
		RefreshableSqlSessionFactoryBean sqlSessionFactoryBean = new RefreshableSqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mainDataSource);
		sqlSessionFactoryBean.setConfigLocation(pmrpr.getResource("classpath:/jf-core/mapper/config/mapper-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(pmrpr.getResources("classpath:/jf-core/mapper/sql/" + db_type + "/**/*.xml"));
		sqlSessionFactoryBean.setCheckInterval(1000);
		sqlSessionFactoryBean.setPlugins(new Interceptor[] { mybatisInterceptor });
		return sqlSessionFactoryBean;
	}
	
	@Bean(destroyMethod="clearCache")
	public SqlSessionTemplate mainSqlSessionTemplate(SqlSessionFactory mainSqlSessionFactoryBean) {
		return new SqlSessionTemplate(mainSqlSessionFactoryBean);
	}
	
	
	
	
	
	
	
	
//	@Bean
//	public MapperConfigurer subMapperConfigurer() {
//		MapperConfigurer mapperConfigurer = new MapperConfigurer();
//		mapperConfigurer.setBasePackage("peg");
//		mapperConfigurer.setAnnotationClass(pegsystem.jdbc.SubMapper.class);
//		mapperConfigurer.setSqlSessionFactoryBeanName("subSqlSessionFactoryBean");
//		mapperConfigurer.setSqlSessionTemplateBeanName("subSqlSessionTemplate");
//		return mapperConfigurer;
//	}
//	
//	@Bean
//	public RefreshableSqlSessionFactoryBean subSqlSessionFactoryBean(DataSource subDataSource
//																	,MybatisInterceptor mybatisInterceptor
//																	,@Value("${sub.db.type}")String db_type) throws IOException {
//		PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
//		RefreshableSqlSessionFactoryBean sqlSessionFactoryBean = new RefreshableSqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(subDataSource);
//		sqlSessionFactoryBean.setConfigLocation(pmrpr.getResource("classpath:/jf-core/mapper/config/mapper-config.xml"));
//		sqlSessionFactoryBean.setMapperLocations(pmrpr.getResources("classpath:/jf-core/mapper/sql/" + db_type + "/**/*.xml"));
//		sqlSessionFactoryBean.setCheckInterval(1000);
//		sqlSessionFactoryBean.setPlugins(new Interceptor[] { mybatisInterceptor });
//		return sqlSessionFactoryBean;
//	}
//	
//	@Bean(destroyMethod="clearCache")
//	public SqlSessionTemplate subSqlSessionTemplate(SqlSessionFactory subSqlSessionFactoryBean) {
//		return new SqlSessionTemplate(subSqlSessionFactoryBean);
//	}
	
}
