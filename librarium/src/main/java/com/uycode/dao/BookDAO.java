package com.uycode.dao;

import com.uycode.entity.Book;
import com.uycode.entity.Category;
import com.uycode.entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Evan
 * @date 2019/4
 */

@Transactional
public interface BookDAO extends JpaRepository<Book,Integer> {
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);

    @Query(value = "select * from book where id = :id", nativeQuery = true)
    Book findBookById(@Param("id") Integer id);
}
