package com.example.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	boolean existsByTitle(String title);

	Optional<Blog> findByTitle(String title);
}
