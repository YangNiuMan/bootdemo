package register;

import com.zaxxer.hikari.HikariDataSource;
import config.DynamicDataSource;
import entity.DataSourceMap;
import entity.DataSourceProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.Nullable;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.*;

/**
 * @author yang
 */
@EnableConfigurationProperties({DataSourceMap.class})
public class DynamicDataSourceRegister implements ApplicationContextAware {

    @Resource
    DataSourceMap dataSourceMap;

    private ApplicationContext applicationContext;

    @Value("#{'${datasource.serverName.list}'.split(',')}")
    private List<String> serverNames;

    private Map<Object, Object> targetDataSources = new HashMap<>(128);

    private Object defaultTargetDataSource;

    public DynamicDataSourceRegister() {
    }

    //@RefreshScope
    @Bean
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(defaultTargetDataSource);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/**/*.xml"));
        return bean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initDefaultTargetDataSource();
        initTargetDataSources();
    }

    private void initTargetDataSources() {
        serverNames.forEach(serverName -> {
            DataSource dataSource = buildDataSource(serverName,dataSourceMap);
            this.targetDataSources.put(serverName, dataSource);
        });
    }

    private void initDefaultTargetDataSource() {
        DataSource dataSource = buildDataSource("cpprd",dataSourceMap);
        this.defaultTargetDataSource = dataSource;
    }

    private DataSource buildDataSource(String serverName, DataSourceMap dataSourceMap) {
        String type ="com.zaxxer.hikari.HikariDataSource";
        Map<String, DataSourceProperties> dataSourceInfoMap = dataSourceMap.getDataSourceInfoMap();
        DataSourceProperties dataSourceProperties = dataSourceInfoMap.get(serverName);
        if(dataSourceProperties == null){
            throw new RuntimeException("数据源配置不存在");
        }else {
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory();
/*
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(type*/
/*dataSourceProperties.getType()*//*
);
            builder.getBeanDefinition().setAttribute("id", serverName);
            builder.addPropertyValue("driverClassName", dataSourceProperties.getDriverClassName());
            builder.addPropertyValue("jdbcUrl", dataSourceProperties.getJdbcUrl());
            builder.addPropertyValue("username", dataSourceProperties.getUsername());
            builder.addPropertyValue("password", dataSourceProperties.getPassword());
            builder.addPropertyValue("loginTimeout", Optional.ofNullable(dataSourceProperties.getLoginTimeout()).orElse("5"));
            builder.addPropertyValue("validationTimeout", Optional.ofNullable(dataSourceProperties.getValidationTimeout()).orElse("5000"));
            builder.addPropertyValue("connectionTimeout", Optional.ofNullable(dataSourceProperties.getConnectionTimeout()).orElse("58000"));
            builder.addPropertyValue("idleTimeout", Optional.ofNullable(dataSourceProperties.getIdleTimeout()).orElse("30000"));
            builder.addPropertyValue("maxLifetime", Optional.ofNullable(dataSourceProperties.getMaxLifetime()).orElse("570000"));
            builder.addPropertyValue("maximumPoolSize", Optional.ofNullable(dataSourceProperties.getMaximumPoolSize()).orElse("50"));
            builder.addPropertyValue("minimumIdle", Optional.ofNullable(dataSourceProperties.getMinimumIdle()).orElse("2"));
            builder.setDestroyMethodName("close");
            if("cpprd".equals(serverName)){
                builder.getBeanDefinition().setPrimary(true);
            }
            defaultListableBeanFactory.registerBeanDefinition(serverName, builder.getBeanDefinition());
*/

            RootBeanDefinition beanDefinition = new RootBeanDefinition();
            beanDefinition.setBeanClass(HikariDataSource.class);
            MutablePropertyValues values = new MutablePropertyValues();
            values.add("driverClassName", dataSourceProperties.getDriverClassName());
            values.add("jdbcUrl", dataSourceProperties.getJdbcUrl());
            values.add("username", dataSourceProperties.getUsername());
            values.add("password", dataSourceProperties.getPassword());
            values.add("loginTimeout", Optional.ofNullable(dataSourceProperties.getLoginTimeout()).orElse("5"));
            values.add("validationTimeout", Optional.ofNullable(dataSourceProperties.getValidationTimeout()).orElse("5000"));
            values.add("connectionTimeout", Optional.ofNullable(dataSourceProperties.getConnectionTimeout()).orElse("58000"));
            values.add("idleTimeout", Optional.ofNullable(dataSourceProperties.getIdleTimeout()).orElse("30000"));
            values.add("maxLifetime", Optional.ofNullable(dataSourceProperties.getMaxLifetime()).orElse("570000"));
            values.add("maximumPoolSize", Optional.ofNullable(dataSourceProperties.getMaximumPoolSize()).orElse("50"));
            values.add("minimumIdle", Optional.ofNullable(dataSourceProperties.getMinimumIdle()).orElse("2"));
            beanDefinition.setPropertyValues(values);
            if("cpprd".equals(serverName)){
                beanDefinition.setPrimary(true);
            }
            beanDefinition.setScope("singleton");
            defaultListableBeanFactory.registerBeanDefinition(serverName, beanDefinition);
            return (DataSource)applicationContext.getBean(serverName);
        }
    }
}
