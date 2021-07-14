package ink.zhongshao;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


/**
 * 
 * 
 * @author Wangfan
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	void createMysql1DatasourcePool(){
    	HikariConfig config = new HikariConfig();
    	config.setJdbcUrl("jdbc:mysql://localhost:3306/simpsons");
    	config.setUsername("bart");
    	config.setPassword("51mp50n");
    	config.addDataSourceProperty("cachePrepStmts", "true");
    	config.addDataSourceProperty("prepStmtCacheSize", "250");
    	config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    	HikariDataSource ds = new HikariDataSource(config);
    }
}
