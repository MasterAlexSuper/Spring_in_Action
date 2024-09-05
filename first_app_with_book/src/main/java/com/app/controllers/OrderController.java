package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.app.data.OrderRepository;
import com.app.models.TacoOrder;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

   private OrderRepository orderRepo;

   public OrderController(OrderRepository orderRepository){
      this.orderRepo = orderRepository;
   }

   @GetMapping("/current")
   public String orderForm() {
      return "orderform";
   }

   @PostMapping
   public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
      if (errors.hasErrors()) {
         return "orderForm";
      }
      orderRepo.save(order);
      sessionStatus.setComplete();

      return "redirect:/";
   }

}
