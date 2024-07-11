package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Order {
   private int id;
   private int amount;
   private boolean paid;//оплачен или не оплачен заказ

    public Order(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}
