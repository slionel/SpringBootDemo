package com.zz;

import com.zz.entity.OrderMaster;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zsj55
 * CrudRepository泛型中的两个参数为 1. 需要进行操作的数据库表所对应的类  2. 这张表的主键
 */

public interface OrderMasterRepository extends CrudRepository<OrderMaster,String> {

}
