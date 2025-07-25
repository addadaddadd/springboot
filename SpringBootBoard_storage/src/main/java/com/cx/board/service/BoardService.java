package com.cx.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cx.board.entity.BoardEntity;
import com.cx.board.repository.BoardRepository;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;

	public BoardEntity write(BoardEntity entity) {
		return boardRepository.save(entity);
	}

	public List<BoardEntity> show() {
		return boardRepository.findAllByOrderByIdDesc();
	}

	public Optional<BoardEntity> detail(Long id) {
		return boardRepository.findById(id);
	}

	public List<BoardEntity> search(String type, String keyword) {
		List<BoardEntity> list = null;
		switch (type) {
		case "title":
			list = boardRepository.findByTitleContaining(keyword);
			break;
		case "writer":
			list = boardRepository.findByWriterContaining(keyword);
			break;
		case "content":
			list = boardRepository.searchContent(keyword);
			break;
		default:
			list = boardRepository.findAllByOrderByIdDesc();
			break;
		}
		return list;
	}
}
