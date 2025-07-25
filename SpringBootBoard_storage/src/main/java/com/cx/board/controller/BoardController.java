package com.cx.board.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cx.board.config.BucketConfig;
import com.cx.board.config.FileUploadConfig;
import com.cx.board.entity.BoardEntity;
import com.cx.board.entity.UserEntity;
import com.cx.board.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
    private final BucketConfig bucketConfig;
    private final AmazonS3 amazonS3;
    private final FileUploadConfig fileUploadConfig;
    
    BoardController(FileUploadConfig fileUploadConfig, BucketConfig bucketConfig, AmazonS3 amazonS3) {
		this.amazonS3 = amazonS3;
		this.fileUploadConfig = fileUploadConfig;
        this.bucketConfig = bucketConfig;
    }

	@PostMapping("/write")
	public String write(@RequestParam String title, @RequestParam String content,
			HttpSession session, @RequestParam MultipartFile image, Model model) {
	
		BoardEntity entity = new BoardEntity();
		
		if(!image.isEmpty()) {
			// 이미지의 이름 
			String img_name = image.getOriginalFilename();
			
			//java 안에 고유 번호를 만드는 객체 --UUID
			//이미지의 고유 이름 부여 
			String file_name = UUID.randomUUID() + "_" +img_name;
			
			try {
				 ObjectMetadata metadata = new ObjectMetadata();
	        metadata.setContentLength(image.getSize());
	        metadata.setContentType(image.getContentType());

	        PutObjectRequest request = new PutObjectRequest(bucketConfig.getbucketName(), file_name, image.getInputStream(), metadata)
	                .withCannedAcl(CannedAccessControlList.PublicRead); // public 접근 허용

	        amazonS3.putObject(request);
	        String imgPath = amazonS3.getUrl(bucketConfig.getbucketName(), file_name).toString();
			
					entity.setTitle(title);
					entity.setContent(content);
					entity.setImgPath(imgPath);

					// writer --session에서 가지고 오기 -- down casting
					UserEntity user = (UserEntity) session.getAttribute("user");
		
					String writer = user.getUserId();
		
					entity.setWriter(writer);
			}catch (Exception e) {
				e.printStackTrace();
			}
					
		}
		
			BoardEntity result = boardService.write(entity);
		
		model.addAttribute("imageUrl", entity.getImgPath());
		if(result != null) {
			// 성공
			//글이 작성이 될 시 index페이지로 이동
			return "redirect:/";
		}else {
			return "redirect:/board/write";
		}
		
	}
}