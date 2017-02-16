package cn.mdzza.application;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by ydt on 2016/11/1.
 */
@Configuration
@MapperScan("cn.mdzza.*.dao")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/mdzza?useUnicode=true&characterEncoding=utf-8");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapping/**/*.xml"));
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	}
}
