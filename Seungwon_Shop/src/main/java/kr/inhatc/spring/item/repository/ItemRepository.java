package kr.inhatc.spring.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import kr.inhatc.spring.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, 
				QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {
	
	List<Item> findByItemNm(String iTemNm);
	
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	
	@Query("Select i from Item i where i.itemDetail like %:itemDetail% "
			+ "order by i.price asc")
	List<Item> findByItemDetail(@Param("itemDetail")String itemDetail);
	
	@Query(value = "Select * from my_item i where i.itemDetail like %:itemDetail% "
			+ "order by i.price asc", nativeQuery = true)
	List<Item> findByItemDetailNative(@Param("itemDetail")String itemDetail);
}
