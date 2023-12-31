package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name="order_item")
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private String id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private long orderPrice;
    private int count;


    public void setOrder(Order order) {
        this.order = order;
    }

    public void setItem(Item item ) {
        this.item = item;
    }
}
