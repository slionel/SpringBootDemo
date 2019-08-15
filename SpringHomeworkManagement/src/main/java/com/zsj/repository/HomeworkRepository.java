package com.zsj.repository;

import com.zsj.entity.Homework;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author zsj55
 */
public interface HomeworkRepository extends CrudRepository<Homework, String> {
    /**根据开放时间和类型查找作业
     * @param type
     * @param openTime
     * @return
     */
    public List<Homework> findByTypeAndOpenTime(String type, Date openTime);

    /**根据开放时间查找作业
     * @param date
     * @return
     */
    public List<Homework> findByOpenTime(Date date);


}
