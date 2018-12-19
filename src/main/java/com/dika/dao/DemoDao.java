package com.dika.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DemoDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate mysqlJdbcTemplate;
    
    public void test(){
        String sql = "select count(1) from T_PORT";
        List<Map<String, Object>> list = mysqlJdbcTemplate.queryForList(sql);
        System.out.println(sql);
        logger.info(sql);
    }
    
    public int insertCardData(List<Object[]> metemp){
        try{
            String sql="insert INTO T_CARD (UK, IP, TYPE, COLLECT_TIME, AREA_ID, CARD_INDEX, USED_PERCENT, TEMPERATURE) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            int[] res = mysqlJdbcTemplate.batchUpdate(sql, metemp);
//            try {
//                mysqlJdbcTemplate.getDataSource().getConnection().commit();
//            } catch (SQLException e) {
//                logger.error("------mysqlJdbcTemplate commit error-------");
//                logger.error(e.getMessage());
//                e.printStackTrace();
//            }
            return res.length;
        } catch (Exception e) {
            logger.error("-----------!!!!!!!!!!!!!!!!!!!sql execute fail!!!!!!!!!!!!!!!!!!!------------");
            logger.error(e.getMessage());
            return -1;
        }
    }
}
