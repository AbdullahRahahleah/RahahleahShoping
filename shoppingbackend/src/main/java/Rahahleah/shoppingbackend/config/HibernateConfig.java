package Rahahleah.shoppingbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages={"Rahahleah.shopingbackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {
	
	//Change the bellow based on the DBMS you choose
	private final static String DATABASE_URL ="jdbc:h2:tcp://localhost/~/onlineshopping";
	private final static String DATABASE_DRIVER ="org.h2.Driver";
	private final static String DATABASE_DIALECT ="org.hibernate.dialect.H2Dialect";
	private final static String DATABASE_USERNAME="sa";
	private final static String DATABASE_PASSWORD ="";
	
	//Datasource bean will be available  
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		//Providing the connection information
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);		
		return dataSource;
	}
	
	//session factory bean will be available 
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("Rahahleah.shopingbackend.dto");
		
		return builder.buildSessionFactory();
	}

	
	//All the hibernate properties will be returned in this method
	public Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect",DATABASE_DIALECT);;
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		
		
		//this property used to check, if I want to deal with a table and that table still doesn't exist so :
		//1- create : drop the current table then recreate it.
		//2- update : update the same table if it's eixsit.
		
		properties.put("hibernate.hbm2ddl.auto","create");
		
		
		return properties;
	}
	
	//TransactionManager bean
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		
		HibernateTransactionManager transactionManager= new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	
}
