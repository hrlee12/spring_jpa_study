package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name="orders")
public class Order {
    @Id @GeneratedValue
    @Column(name="order_id")
    private String id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;


    // cascade : 필드의 객체를 따로 persist해줄 필요 없이
    // 객체(Order)를 persist해주면 cascade로 필드의 객체도 엔티티로 등록됨.
    // CascadeType.All : 삭제할때도 cascade
    // persist를 각각 해주는 수고를 줄여줌.
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus stauts;

    //==연관관계 메서드==//
    // 두 엔티티의 연관관계를 만드는 코드를 일일이 치는게 아니라
    // 하나의 메서드로 만들어두자.
    // 중심관계를 잡고 있는 클래스에 만들면 좋음
    // 오더가 생기면 멤버랑 연결, 오더아이템즈, 딜리버리 생김.
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
