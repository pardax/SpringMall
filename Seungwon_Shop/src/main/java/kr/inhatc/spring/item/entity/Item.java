package kr.inhatc.spring.item.entity;

import kr.inhatc.spring.item.constant.ItemSellStatus;
import kr.inhatc.spring.utils.entity.BaseEntity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "my_item")
@Getter
@Setter
@ToString
@NoArgsConstructor	//bin생성자
@AllArgsConstructor
public class Item extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;	//상품코드
	
	@Column(nullable = false, length = 50)
	private String itemNm;	//상품이름
	
	@Column(nullable = false)
	private int price;	//상품가격
	
	@Column(nullable = false, name = "number")
	private int stockNumber;	//재고수량
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus;
	
	@Lob	//가변길이를 갖는 큰 데이터를 저장하는데 사용하믄 데이터형
	@Column(nullable = false)
	private String itemDetail; //상품상세설명
	
	
}
