package com.cx.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cx.board.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	boolean existsByUserId(String userId); 
	Optional<UserEntity>findByUserIdAndPw(String id, String pw);
}
