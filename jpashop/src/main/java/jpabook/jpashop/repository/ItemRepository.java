package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;


    public void save(Item item) {
        // item의 id는 엔티티로 등록될 때 할당됨.
        // 즉, id가 null이면 새로운 엔티티로 등록하고
        // id가 null이 아니면 이미 엔티티로 등록되었기 때문에
        // merge(수정?대체?)를 해줌.
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
