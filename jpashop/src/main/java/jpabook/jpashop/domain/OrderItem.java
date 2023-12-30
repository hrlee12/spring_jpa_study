package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;

@Entity
@Getter
@Table(name="order_item")
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private String id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    private long orderPrice;
    private int count;
}
