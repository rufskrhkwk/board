package peg.core.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
public class ContextTransaction {

	@Bean
	public DataSourceTransactionManager mainTxManager(DataSource mainDataSource) {
		return new DataSourceTransactionManager(mainDataSource);
	}
	
//	@Bean
//	public DataSourceTransactionManager subTxManager(DataSource subDataSource) {
//		return new DataSourceTransactionManager(subDataSource);
//	}
}
