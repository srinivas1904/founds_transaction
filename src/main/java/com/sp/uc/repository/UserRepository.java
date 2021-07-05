package com.sp.uc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp.uc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	

}
