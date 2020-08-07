package cn.okcoming.areacode.service.impl;

import cn.okcoming.areacode.dto.Area;
import cn.okcoming.areacode.service.AreaService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AreaServiceImpl implements AreaService {
    private JdbcTemplate jdbcTemplate;

    public AreaServiceImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Area> rowMapper(){
        return (rs,rowNum) -> {
            Area area = new Area();
            area.setId(rs.getLong("id"));
            area.setCode(rs.getString("code"));
            area.setName(rs.getString("name"));
            area.setParentCode(rs.getString("parent_code"));
            return area;
        };
    }

    @Override
    public List<Area> listAll() {
        return jdbcTemplate.query("SELECT id,code,name,parent_code FROM area", rowMapper());
    }

    @Override
    public Area getByCode(String code) {
        List<Area> list = jdbcTemplate.query("SELECT id,code,name,parent_code FROM area WHERE code = ?",new Object[]{code},rowMapper());
        return list.size()==0?null:list.get(0);
    }

    @Override
    public List<Area> getByParentCode(String parentCode) {
        List<Area> list = jdbcTemplate.query("SELECT id,code,name,parent_code FROM area WHERE parent_code = ?",new Object[]{parentCode},rowMapper());
        return list;
    }

    @Override
    public List<Area> getByCodes(List<String> codeList) {
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map map = new HashMap(2);
        map.put("codes",codeList);
        List<Area> list = jdbc.query("SELECT id,code,name,parent_code FROM area WHERE code in (:codes)",map,rowMapper());
        return list;
    }

    @Override
    public Area getByName(String name) {
        List<Area> list = jdbcTemplate.query("SELECT id,code,name,parent_code FROM area WHERE name = ? AND parent_code LIKE '%0000' LIMIT 1",new Object[]{name},rowMapper());
        return list.size()==0?null:list.get(0);
    }

    @Override
    public Area getLikeName(String name) {
        return null;
    }

    @Override
    public List<Map<String, Object>> query(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

}
