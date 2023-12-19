package jpabook.jpashop;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

//@Entity : DB의 테이블과 맵핑되는 클래스
@Entity
@Getter
@Setter
public class Member {
//  @Id : primaryKey임을 나타냄
//  @GeneratedValue : 기본키 자동생성 = autoIncrement (strategy 지정 가능)
    @Id @GeneratedValue
    private Long id;
    private String username;
}
