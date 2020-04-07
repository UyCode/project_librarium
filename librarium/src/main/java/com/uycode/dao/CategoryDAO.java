package com.uycode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uycode.entity.Category;

/**
 * @author Evan
 * @date 2019/4
 */
public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
