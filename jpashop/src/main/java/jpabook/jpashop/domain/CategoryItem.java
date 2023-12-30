package jpabook.jpashop.domain;


import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;

@Entity
@Getter
public class CategoryItem {
    @Id
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @Id
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;
}
