package cn.okcoming.areacode.service;

import cn.okcoming.areacode.dto.Area;

import java.util.List;
import java.util.Map;


public interface AreaService {

    /**
     * 查询所有区域编码
     *
     * @return
     */
    List<Area> listAll();

    /**
     * 根据区域编码查询区域
     *
     * @param code
     * @return
     */
    Area getByCode(String code);

    /**
     * 根据父级区域编码查询子区域
     *
     * @param parentCode
     * @return
     */
    List<Area> getByParentCode(String parentCode);

    /**
     * 根据区域编码列表查询区域
     *
     * @param codes
     * @return
     */
    List<Area> getByCodes(List<String> codes);

    /**
     * 根据市级名称 查询区域信息
     *
     * @param name
     * @return
     */
    Area getByName(String name);

    Area getLikeName(String name);

    /**
     * 允许执行任何sql查询语句 模拟客户端工具
     */
    List<Map<String, Object>> query(String sql);

}
