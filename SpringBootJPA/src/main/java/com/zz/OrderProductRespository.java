package com.zz;

import com.zz.entity.OrderProduct;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zsj55
 */
public interface OrderProductRespository extends JpaRepository<OrderProduct,String> {

    @Query("select op from OrderProduct op where id=?1 and productName=?2")
    public OrderProduct findByIdAndName(String id, String name);
}
