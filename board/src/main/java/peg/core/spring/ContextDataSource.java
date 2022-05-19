package peg.core.spring;

//import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextDataSource {
	
	/*
	 * @Bean public BasicDataSource
	 * mainDataSource(@Value("${jdbc.${main.db.type}.driverClassName}")String
	 * driverClassName ,@Value("${jdbc.${main.db.type}.url}")String url
	 * ,@Value("${jdbc.${main.db.type}.username}")String username
	 * ,@Value("${jdbc.${main.db.type}.password}")String password
	 * ,@Value("${jdbc.${main.db.type}.initialSize}")int initialSize
	 * ,@Value("${jdbc.${main.db.type}.maxActive}")int maxActive
	 * ,@Value("${jdbc.${main.db.type}.minIdle}")int minIdle
	 * ,@Value("${jdbc.${main.db.type}.maxWait}")long maxWait
	 * ,@Value("${jdbc.${main.db.type}.validationQuery}")String validationQuery) {
	 * BasicDataSource dataSource = new BasicDataSource();
	 * dataSource.setDriverClassName(driverClassName); dataSource.setUrl(url);
	 * dataSource.setUsername(username); dataSource.setPassword(password);
	 * dataSource.setInitialSize(initialSize); dataSource.setMaxActive(maxActive);
	 * dataSource.setMinIdle(minIdle); dataSource.setMaxWait(maxWait);
	 * dataSource.setValidationQuery(validationQuery);
	 * dataSource.setTestWhileIdle(true); return dataSource; }
	 */
//	@Bean
//	public BasicDataSource subDataSource(@Value("${jdbc.${sub.db.type}.driverClassName}")String driverClassName
//										,@Value("${jdbc.${sub.db.type}.url}")String url
//										,@Value("${jdbc.${sub.db.type}.username}")String username
//										,@Value("${jdbc.${sub.db.type}.password}")String password
//										,@Value("${jdbc.${sub.db.type}.initialSize}")int initialSize
//										,@Value("${jdbc.${sub.db.type}.maxActive}")int maxActive
//										,@Value("${jdbc.${sub.db.type}.minIdle}")int minIdle
//										,@Value("${jdbc.${sub.db.type}.maxWait}")long maxWait
//										,@Value("${jdbc.${sub.db.type}.validationQuery}")String validationQuery) {
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(driverClassName);
//		dataSource.setUrl(url);
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
//		dataSource.setInitialSize(initialSize);
//		dataSource.setMaxActive(maxActive);
//		dataSource.setMinIdle(minIdle);
//		dataSource.setMaxWait(maxWait);
//		dataSource.setValidationQuery(validationQuery);
//		dataSource.setTestWhileIdle(true);
//		return dataSource;
//	}
	
	
	// jndi-lookup
//	@Bean
//	public DataSource mainDataSource(@Value("${main.jndi.name}")String jndiName) throws NamingException {
//		JndiDataSourceLookup jdsl = new JndiDataSourceLookup();
//		jdsl.setResourceRef(true);
//		DataSource dataSource = jdsl.getDataSource(jndiName);
//		return dataSource;
//	}
//	@Bean
//	public DataSource subDataSource(@Value("${sub.db.used}")String used
//								   ,@Value("${sub.jndi.name}")String subJndiName
//								   ,@Value("${main.jndi.name}")String mainJndiName) throws NamingException {
//		String jndiName = "";
//		if("Y".equalsIgnoreCase(used)) jndiName = subJndiName;
//		else jndiName = mainJndiName;
//		
//		JndiDataSourceLookup jdsl = new JndiDataSourceLookup();
//		jdsl.setResourceRef(true);
//		DataSource dataSource = jdsl.getDataSource(jndiName);
//		return dataSource;
//	}
}
