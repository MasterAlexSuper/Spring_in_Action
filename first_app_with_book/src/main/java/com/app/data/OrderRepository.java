package com.app.data;

import org.springframework.data.repository.CrudRepository;

import com.app.models.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {

}
