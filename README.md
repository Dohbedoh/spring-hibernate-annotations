## Abstract

This project is a sample of a Spring/Hibernate project with **Annotations configuration**. It is aimed to compare Spring/Hibernate configuration with another project, **xml-driven**, in parallel. 

## Project structure

```
Project
 |  src
 |   |-main
 |   |   |-java
 |   |   |   |-com
 |   |   |       |-dohbedoh
 |   |   |           |-SpringHibernateAnnotations.java
 |   |   |           |-SpringHibernateAnnotationsConfig.java
 |   |   |           |-config
 |   |   |               |-DataSourceConfig.java
 |   |   |               |-HibernateConfig.java
 |   |   |           |-dao
 |   |   |               |-AbstractDAO.java
 |   |   |               |-CompanyDAO.java
 |   |   |               |-impl
 |   |   |                   |-CompanyDAOImpl.java
 |   |   |           |-model
 |   |   |               |-Company.java
 |   |   |           |-service
 |   |   |               |-CompanyService.java
 |   |   |-resources
 |   |       |-logback.xml
 |   |       |-database
 |   |       |   |-bootstrap.sql
 |   |       |   |-database.properties
 |   |       |   |-hibernate.properties
 |   |-test
 |   |   |-java
 |   |   |   |-com
 |   |   |       |-dohbedoh
 |   |   |           |-dao
 |   |   |  			 |-CompanyDAOTest.java
 |   |   |           |-service
 |   |   |  			 |-CompanyServiceTest.java
 |   |   |-resources
 |   |       |-logback-test.xml
 pom.xml
```

## Spring

The configuration is divided in separate configuration **classes**:
- Data Source in *com.dohbedoh.config.DataSourceConfig.java*
- Hibernate and Transaction Manager in *com.dohbedoh.config.HibernateConfig.java*
- General configuration and Components scan in *com.dohbedoh.SpringHibernateAnnotationConfig.java*

####Project Spring Structure
```
Project
 |  src
 |   |-main
 |   |   |-java
 |   |   |   |-com
 |   |   |       |-dohbedoh
 |   |   |           |-SpringHibernateAnnotationsConfig.java
 |   |   |           |-config
 |   |   |               |-DataSourceConfig.java
 |   |   |               |-HibernateConfig.java
```

SpringHibernateAnnotationConfig.java is the entry point and set up the *@ComponentScan* and *@EnableTransactionManagement* (similar to the *<tx:annotation-driven/>*) for our application. We also create a *PropertySourcesPlaceholderConfigurer* to be able to inject properties values using *@Value* annotation in our configuration classes. 

There are different to access the properties. Both are demonstrated in this project. Our Data Source configuration is using a *PropertySourcePlaceHolderConfigurer* while Hibernate configuration is using the *Environment* Spring object. Spring recommend the use of the latter.

####SpringHibernateAnnotationConfig.java
```java
...

@Configuration
@EnableTransactionManagement
@ComponentScan
public class SpringHibernateAnnotationsConfig {
       
    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceHolder() {
       PropertySourcesPlaceholderConfigurer propertyPlaceholder = new PropertySourcesPlaceholderConfigurer();
       return propertyPlaceholder;
       
    }
}

...
```

## Database

The project is using a simple MySQL database. The SQL file *resources/database/bootstrap.sql* contains the queries necessary to create the database and the required tables.

###Data Model

A simple data model is used to play with Hibernate configuration. Basically, simple tables with primary/foreign keys. The following tables are created.

####Address Table: A table containing addresses *ADDRESS*

| Field               | Type          | NULL  | KEY                 |
| ------------------- |:-------------:|:-----:|:-------------------:|
| ADDRESS_ID          | smallint(6)   |  NO   |      PRIMARY        |
| ADDRESS_NUMBER      | varchar(4)    |  NO   |                     |
| ADDRESS_LINE_1      | varchar(12)   |  NO   |                     |
| ADDRESS_LINE_2      | varchar(12)   |  YES  |                     |
| ADDRESS_ZIP_CODE    | varchar(5 )   |  NO   |                     |
| ADDRESS_CITY        | varchar(12)   |  NO   |                     |
| ADDRESS_STATE       | varchar(12)   |  NO   |                     |
| ADDRESS_COUNTRY     | varchar(30)   |  NO   |                     |

####Contact Table: A table containing contact details *CONTACT*

| Field               | Type          | NULL  | KEY                 |
| ------------------- |:-------------:|:-----:|:-------------------:|
| CONTACT_ID          | smallint(6)   |  NO   |      PRIMARY        |
| CONTACT_PHONE_NUMBER| varchar(12)   |  NO   |                     |
| CONTACT_FAX_NUMBER  | varchar(12)   |  NO   |                     |
| CONTACT_EMAIL       | varchar(30)   |  NO   |                     |

####Company Table: A table containing companies *COMPANY* that references *ADDRESS* and *CONTACT*

| Field               | Type          | NULL  | KEY                 |
| ------------------- |:-------------:|:-----:|:-------------------:|
| COMPANY_ID          | smallint(6)   |  NO   |      PRIMARY        |
| COMPANY_NAME        | varchar(32)   |  NO   |                     |
| COMPANY_ADDRESS_ID  | smallint(12)  |  NO   |  ADDRESS.ADRESS_ID  |
| COMPANY_CONTACT_ID  | smallint(30)  |  NO   |  CONTACT.CONTACT_ID |

###Configuration

The properties to access to the database are located in *resources/database/database.properties*

####database.properties
```
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:8084/dohbedoh
jdbc.username=root
jdbc.password=admin
```

We use the mysql-connector-java, a JDBC driver for MySQL.

####pom.xml
```xml
...
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>${mysql-connector.version}</version>
</dependency>
...
```

We use the **mysql-connector-java** (JDBC driver for MySQL), and a *Apache DBCP2* (database connection pool).

####pom.xml
```xml
...

	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>${mysql-connector.version}</version>
	</dependency>
	
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-dbcp2</artifactId>
        <version>${apache-dbcp.version}</version>
    </dependency>

...
```

####Project DataSource Structure
```
Project
 |  src
 |   |-main
 |   |   |-java
 |   |   |   |-com
 |   |   |       |-dohbedoh
 |   |   |           |-config
 |   |   |               |-DataSourceConfig.java
```

The data source is configured in *com.dohbedoh.config.DataSourceConfig.java* and make use of the properties set up in *resources/database/database.properties*. We use the annotation **@PropertySource** to add the 'database.properties' file to the Spring *Environment* through the **PropertySourcePlaceholderConfigurer** defined above.


####DataSourceConfig.java
```java
...

@Configuration
@PropertySource("classpath:database/database.properties")
public class DataSourceConfig {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}

...
```

## Hibernate

####Project Hibernate Structure
```
Project
 |  src
 |   |-main
 |   |   |-java
 |   |   |   |-com
 |   |   |       |-dohbedoh
 |   |   |           |-config
 |   |   |               |-HibernateConfig.java
 |   |   |           |-model
 |   |   |               |-Company.java
```

The entry point of Hibernate configuration is *com.dohbedoh.config.HibernateConfig.java*. We set up the **SessionFactory** with Hibernate properties and specify *com.dohbedoh.model* as the package in which to find Hibernate Mapping Objects. We also set up a *HibernateTransactionManager* to which we attached the *SessionFactory* bean.

####HibernateConfig.java
```java
...

    @Autowired
        private Environment environment;
    
        @Bean
        public LocalSessionFactoryBean setupSessionFactory(DataSource dataSource) {
            LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);
            sessionFactoryBean.setPackagesToScan("com.dohbedoh.model");
    
            Properties hibernateProperties = new Properties();
            hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
            hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
            hibernateProperties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
    
            sessionFactoryBean.setHibernateProperties(hibernateProperties);
            return sessionFactoryBean;
        }

    @Bean
    public PlatformTransactionManager transactionManager(
            SessionFactory sessionFactory,
            DataSource dataSource) {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}

...
```

Each model mapping is defined inside the corresponding model class under *com.dohbedoh.model*. Mapping by annotations is straight-forward and really similar to mapping with. We use **JPA annotations** which are supported by Hibernate. Some hibernate annotations are deprecated (for example @Entity).

####Company.java
```xml
...

@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "COMPANY_ID", precision = 5, scale = 0)
    private int companyId;

    @Column(name = "COMPANY_NAME", length = 32, nullable = false)
    private String companyName;

    @Column(name = "COMPANY_ADDRESS_ID", precision = 5, scale = 0, nullable = false)
    private int addressId;

    @Column(name = "COMPANY_CONTACT_ID", precision = 5, scale = 0, nullable = false)
    private int contactId;

...
```

## Testing

Coming soon...
