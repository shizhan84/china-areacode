package cn.okcoming.areacode.config;

import cn.okcoming.areacode.service.AreaService;
import cn.okcoming.areacode.service.impl.AreaServiceImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * 内存数据库配置 用来存放区域信息
 */
@Configurable
public class DataConfiguration {

    /**
     * 名字长一点 避免冲突
     * @return
     */
    @Bean(name = "areacodeJdbcTemplate")
    public JdbcTemplate jdbcTemplate(){
        DataSource dataSource = new EmbeddedDatabaseBuilder().
                setType(EmbeddedDatabaseType.H2).
                addScripts("init_schema.sql","init_data.sql").
                setScriptEncoding("utf-8").
                setName("data").build();
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public AreaService areaService(@Qualifier("areacodeJdbcTemplate") JdbcTemplate jdbcTemplate){
        return new AreaServiceImpl(jdbcTemplate);
    }
}
