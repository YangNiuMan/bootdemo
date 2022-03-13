package entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.Map;

/**
 * @author yang
 */

@RefreshScope
@ConfigurationProperties(prefix = "datasource.config")
public class DataSourceMap {

    private Map<String, DataSourceProperties> dataSourceInfoMap;

    public DataSourceMap() {
    }

    public DataSourceMap(Map<String, DataSourceProperties> dataSourceInfoMap) {
        this.dataSourceInfoMap = dataSourceInfoMap;
    }

    public Map<String, DataSourceProperties> getDataSourceInfoMap() {
        return dataSourceInfoMap;
    }

    public void setDataSourceInfoMap(Map<String, DataSourceProperties> dataSourceInfoMap) {
        this.dataSourceInfoMap = dataSourceInfoMap;
    }
}
