package config;

import contextHolder.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author yang
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(){}

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getContextHolder();
    }
}
