package config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jca.support.LocalConnectionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration // конфигурация бинов
@EnableTransactionManagement
public class AppConfig {

    @Bean // настройка фабрики сессий для доступа к БД
    public LocalSessionFactoryBean localSessionFactoryBean(){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource()); // настройки доступа к БД
        localSessionFactoryBean.setPackagesToScan("model"); // путь к классам Entity
        localSessionFactoryBean.setHibernateProperties(hibernateProperties()); // настройки Хибернейта
        return  localSessionFactoryBean;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put(Environment.SHOW_SQL, "true");
        return properties;

    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource(); // используется пул соединений для доступа к БД
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/todo?useSSL=false&amp;serverTimezone=UTC");
        dataSource.setUsername("root"); // в тестовых целях
        dataSource.setPassword("root"); // в тестовых целях
        dataSource.getMaximumPoolSize(); // максимальное количество соединений
        return dataSource;
    }
}
