package com.sp.uc.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp.uc.model.User;
@Repository
public interface UsRepository extends JpaRepository<User, Long>{
	User findByCity(String city);
	Page<User> findByCityContaining(String city, Pageable page);
	List<User> findByCityContaining(String city, Sort sort);
//	List<User> findAll(Sort sort);
	List<User> findByUserIdGreaterThanOrderByNameDesc(Long id);
	List<User> findByUserIdGreaterThan(Long id);
	List<User> findByStateContaining(String state); 
}
