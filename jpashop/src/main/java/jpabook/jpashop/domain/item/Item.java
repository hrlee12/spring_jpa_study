package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Item {
    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    //--비지니스 로직--//
    //수량 증가
    public void addStock(int quantity) {
        stockQuantity += quantity;
    }

    // 수량 감소
    public void removeStock(int quantity) {
        int restStock = stockQuantity - quantity;

        if (restStock < 0) {
            throw new NotEnoughStockException("수량이 음수입니다.");
        }

        stockQuantity = restStock;


    }

}
