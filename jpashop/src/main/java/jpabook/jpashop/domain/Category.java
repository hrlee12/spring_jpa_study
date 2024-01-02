package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Category {
    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;
    private String name;

    @ManyToOne (fetch = LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy="parent")
    private List<Category> child = new ArrayList<>();


    //==연관관계 메서드==//
    public void setParent(Category category) {
        this.parent = category;
        category.getChild().add(this);
    }

}
