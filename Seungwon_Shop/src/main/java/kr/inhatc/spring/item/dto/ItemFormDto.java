package kr.inhatc.spring.item.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import kr.inhatc.spring.item.constant.ItemSellStatus;
import kr.inhatc.spring.item.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemFormDto {
	private Long id;	//상품코드
	
	@NotBlank(message = "상품명은 필수 항목 입니다.")
	private String itemNm;	//상품이름
	
	@NotNull(message = "상품가격은 필수 항목 입니다.")
	private int price;	//상품가격
	
	@NotNull(message = "재고는 필수 항목 입니다.")
	private int stockNumber;	//재고수량
	
	private ItemSellStatus itemSellStatus;
	
	@NotBlank(message = "상품상세설명은 필수 항목 입니다.")
	private String itemDetail; //상품상세설명
	
	private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
	
	private List<Long> itemImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Item createItem() {
		return modelMapper.map(this, Item.class);
	}
	
	public static ItemFormDto of(Item item) {
		return modelMapper.map(item, ItemFormDto.class);
	}
}
