package com.zsj.repository;

import com.zsj.entity.Homework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author zsj55
 */
public interface HomeworkRepository extends JpaRepository<Homework, String> {
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


    /**根据开放时间和类型查找作业
     * 分页
     * @param type
     * @param openTime
     * @param pageable
     * @return
     */
    public Page<Homework> findByTypeAndOpenTime(String type, Date openTime, Pageable pageable);

}
