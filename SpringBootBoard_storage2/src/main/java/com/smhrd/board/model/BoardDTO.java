package com.smhrd.board.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class BoardDTO {

	private Long id;// 번호

	private String title;

	private String writer;

	private String content;// 내용의 경우 길이가 길어야 하므로

	private String imgPath;

	private LocalDate writeDay;
}
