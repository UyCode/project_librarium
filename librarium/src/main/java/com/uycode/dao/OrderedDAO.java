package com.uycode.dao;

import com.uycode.entity.Book;
import com.uycode.entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface OrderedDAO extends JpaRepository<Ordered, Integer> {

    @Query(value = "select * from ordered where uid = :uid", nativeQuery = true)
    List<Ordered> findByUid(@Param("uid") Integer uid);

    Ordered findByUidAndBid(Integer uid, Integer bid);

}
