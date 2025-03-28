package configuration;

import model.entity.Location;
import model.entity.Session;
import model.entity.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class HibernateConfig {

    private final Environment environment;

    public HibernateConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"))
                .applySetting("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"))
                .applySetting("hibernate.connection.datasource", dataSource)
                .build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(User.class);
        metadataSources.addAnnotatedClass(Location.class);
        metadataSources.addAnnotatedClass(Session.class);

        Metadata metadata = metadataSources.buildMetadata();

        return metadata.getSessionFactoryBuilder().build();
    }
}
