package cz.raptors.dianotes;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan
public class SpringConfiguration {
    private static final Logger log = LoggerFactory.getLogger(SpringConfiguration.class);

/*    @Bean
    public static PropertyOverrideConfigurer propertyOverride() throws FileNotFoundException {
        *//*final PropertyOverrideConfigurer properties = new PropertyOverrideConfigurer();

        final String config = "/src/main/resources";
        if (config == null || config.isEmpty()) {
            log.error("No 'config' system property detected - ${artifactId} cannot start up.");
            throw new RuntimeException("No 'config' system property detected - ${artifactId} cannot start up.");
        }
        URL resource = SpringConfiguration.class.getResource("/dianotes.properties");
        File configFile = new File(resource.getFile());
        if (!configFile.exists() || !configFile.canRead()) {
            log.error("Attempted to load the ${artifactId} configuration file from {}/${artifactId}.properties, but either it does not exist or is not readable.", config);
            throw new RuntimeException(String.format("Attempted to load the ${artifactId} configuration file from %s/app.properties, but either it does not exist or is not readable.", config));
        }

        Resource location = new FileSystemResource(configFile);
        properties.setLocation(location);

        return properties;*//*
    }*/

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setBeanName("entityManagerFactory");
        emf.setPackagesToScan(new String[]{"cz.raptors.dianotes.entities"});

        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(jpaAdapter());
        emf.setJpaProperties(getJpaProperties());

        return emf;
    }

    @Bean
    public JpaVendorAdapter jpaAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        return jpaVendorAdapter;
    }
    private Properties getJpaProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.format_sql", "true");

            }
        };
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dianotes");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws Exception {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}