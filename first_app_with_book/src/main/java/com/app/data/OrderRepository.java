package com.app.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.models.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

   List<TacoOrder> findByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

}
