package com.cx.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cx.board.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	List<BoardEntity> findAllByOrderByIdDesc();

	List<BoardEntity> findByTitleContaining(String keyword);

	@Query("select b from BoardEntity b where b.content like %:keyword%")
	List<BoardEntity> searchContent(String keyword);

	List<BoardEntity> findByWriterContaining(String keyword);

}
