package entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yang
 */

public class DataSourceProperties {

    private String type;
    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;
    private String loginTimeout;
    private String validationTimeout;
    private String connectionTimeout;
    private String idleTimeout;
    private String maxLifetime;
    private String maximumPoolSize;
    private String minimumIdle;

    public DataSourceProperties() {
    }

    public DataSourceProperties(String type, String driverClassName, String jdbcUrl, String username, String password, String loginTimeout, String validationTimeout, String connectionTimeout, String idleTimeout, String maxLifetime, String maximumPoolSize, String minimumIdle) {
        this.type = type;
        this.driverClassName = driverClassName;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.loginTimeout = loginTimeout;
        this.validationTimeout = validationTimeout;
        this.connectionTimeout = connectionTimeout;
        this.idleTimeout = idleTimeout;
        this.maxLifetime = maxLifetime;
        this.maximumPoolSize = maximumPoolSize;
        this.minimumIdle = minimumIdle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginTimeout() {
        return loginTimeout;
    }

    public void setLoginTimeout(String loginTimeout) {
        this.loginTimeout = loginTimeout;
    }

    public String getValidationTimeout() {
        return validationTimeout;
    }

    public void setValidationTimeout(String validationTimeout) {
        this.validationTimeout = validationTimeout;
    }

    public String getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(String connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(String idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public String getMaxLifetime() {
        return maxLifetime;
    }

    public void setMaxLifetime(String maxLifetime) {
        this.maxLifetime = maxLifetime;
    }

    public String getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(String maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public String getMinimumIdle() {
        return minimumIdle;
    }

    public void setMinimumIdle(String minimumIdle) {
        this.minimumIdle = minimumIdle;
    }
}
