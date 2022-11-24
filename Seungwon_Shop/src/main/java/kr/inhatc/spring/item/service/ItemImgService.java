package kr.inhatc.spring.item.service;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import kr.inhatc.spring.item.entity.ItemImg;
import kr.inhatc.spring.item.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
	
	@Value(value = "${itemImgLocation}")
	public String itemImgLocation;
	
	
	private final ItemImgRepository itemImgRepository;
	private final FileService fileService;
	
	public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws IOException{
		String oriImgName = itemImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		if(!StringUtils.isEmpty(oriImgName)) {
			imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
			imgUrl = "/images/item/" + imgName;
		}
		
		itemImg.updateItemImg(oriImgName, imgName, imgUrl);
		itemImgRepository.save(itemImg);
	}
	
	public void updateItemImg(Long ItemImgId, MultipartFile itemImgFile) throws IOException {
		if(!itemImgFile.isEmpty()) {
			ItemImg itemImg = itemImgRepository.findById(ItemImgId).orElseThrow(EntityNotFoundException::new);
			if(!StringUtils.isEmpty(itemImg.getImgName())) {
				fileService.deleteFile(itemImgLocation + "/" + itemImg.getImgName());
			}
			
			String oriName = itemImgFile.getOriginalFilename();
			String imgName = fileService.uploadFile(itemImgLocation, oriName, itemImgFile.getBytes());
			String imgUrl = "/images/item" + imgName;
			
			itemImg.updateItemImg(oriName, imgName, imgUrl);
		}
	}
}
