package kr.inhatc.spring.item.dto;

import java.time.LocalDateTime;

import kr.inhatc.spring.item.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {
	private Long id;	//상품코드
	
	private String itemNm;	//상품이름
	
	private int price;	//상품가격
	
	private int stockNumber;	//재고수량
	
	private ItemSellStatus itemSellStatus;
	
	private String itemDetail; //상품상세설명
	
	private LocalDateTime regTime;	//등록시간
	
	private LocalDateTime updateTime;
	
}
