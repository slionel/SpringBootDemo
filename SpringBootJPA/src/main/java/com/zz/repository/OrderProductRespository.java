package com.zz.repository;

import com.zz.entity.OrderProduct;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zsj55
 */
public interface OrderProductRespository extends JpaRepository<OrderProduct,String> {

}
