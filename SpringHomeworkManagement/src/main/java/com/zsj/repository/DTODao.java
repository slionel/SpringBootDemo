package com.zsj.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zsj55
 */
@Repository
public class DTODao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> queryHomeworkDTOListMap(String homeworkId){
        String sql = "select u.name, uh.homeworkmaster_id, u.status from user u left join user_homework uh on (u.id = uh.user_id and uh.homeworkmaster_id=?);";
        Object[] args = {homeworkId};
        int[] argsTypes = {Types.VARCHAR};
        return this.jdbcTemplate.queryForList(sql, args, argsTypes);
    }


    public List<Map<String, Object>> queryHomeworkRankDTOListMap(String type){
        String sql;
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if("全部".equals(type)){
            sql = "select u.name,count(t.homeworkmaster_id ) co,u.status from user u left join (select u.id,u.name, uh.homeworkmaster_id,h.title from user u left join user_homework uh on u.id=uh.user_id left join homework h on uh.homeworkmaster_id=h.hid) t on t.id=u.id group by u.id order by count(t.homeworkmaster_id) desc;";
            mapList = this.jdbcTemplate.queryForList(sql);
        }
        else{
            sql = "select u.name,count(t.homeworkmaster_id ) co,u.status from user u left join (select u.id,u.name, uh.homeworkmaster_id,h.title from user u left join user_homework uh on u.id=uh.user_id left join homework h on uh.homeworkmaster_id=h.hid where h.type=?) t on t.id=u.id group by u.id order by count(t.homeworkmaster_id) desc;";
            Object[] args = {type};
            int[] argsTypes = {Types.VARCHAR};
            mapList = this.jdbcTemplate.queryForList(sql, args, argsTypes);
        }

        return mapList;
    }
}
