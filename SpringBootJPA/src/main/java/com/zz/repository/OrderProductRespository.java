package com.zz.repository;

import com.zz.entity.OrderProduct;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zsj55
 */
public interface OrderProductRespository extends CrudRepository<OrderProduct,String> {
}
