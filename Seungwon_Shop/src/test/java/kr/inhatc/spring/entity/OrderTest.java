package kr.inhatc.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.item.constant.ItemSellStatus;
import kr.inhatc.spring.item.entity.Item;
import kr.inhatc.spring.item.repository.ItemRepository;
import kr.inhatc.spring.member.entity.Member;
import kr.inhatc.spring.member.repository.MemberRepository;
import kr.inhatc.spring.order.entity.Order;
import kr.inhatc.spring.order.entity.OrderItem;
import kr.inhatc.spring.order.repository.OrderItemRepository;
import kr.inhatc.spring.order.repository.OrderRepository;

@SpringBootTest
@Transactional
class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    public Item createItem(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());

        item.setUpdateTime(LocalDateTime.now());
        return item;
    }

    @Test
    @DisplayName("영속성 전이 테스트 / 윤승원")
    public void cascadeTest() {
        Order order = new Order();
        for(int i=0;i<3;i++){
            Item item = this.createItem();
            //itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        em.flush();
        em.clear();
    }
    
    public Order createOrder(){
        Order order = new Order();
        for(int i=0;i<3;i++){
            Item item = createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        Member member = new Member();
        memberRepository.save(member);
        order.setMember(member);
        return order;
    }
    
    @Test
    @DisplayName("고아객체 제거 테스트 / 윤승원")
    public void orphanRemovalTest(){
        Order order = this.createOrder();
        order.getOrderItems().remove(0);
        em.flush();
    }
}