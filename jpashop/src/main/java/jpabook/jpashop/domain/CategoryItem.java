package jpabook.jpashop.domain;


import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class CategoryItem {
    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;
}
