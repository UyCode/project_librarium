package com.uycode.controller;

import com.uycode.dao.BookDAO;
import com.uycode.dao.OrderedDAO;
import com.uycode.entity.Book;
import com.uycode.entity.Ordered;
import com.uycode.result.Result;
import com.uycode.result.ResultFactory;
import com.uycode.service.BookService;
import com.uycode.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hyper
 */

@RestController
public class OrderController {

    @Autowired
    OrderedDAO orderedDAO;

    @Autowired
    BookService bookService;

    @Autowired
    BookDAO bookDAO;

    @PostMapping("/api/borrow")
    public Result setBorrow(@RequestBody Ordered brw){
        Ordered already = orderedDAO.findByUidAndBid(brw.getUid(), brw.getBid());
        if(already != null){
            return ResultFactory.buildFailResult("already");
        }
        orderedDAO.save(brw);
        return ResultFactory.buildSuccessResult(brw);
    }

    @DeleteMapping("/api/bookReturn/{uid}/{bid}")
    public Result deleteBook(@PathVariable("uid") Integer uid,
                             @PathVariable("bid") Integer bid){
        Ordered ordered = orderedDAO.findByUidAndBid(uid, bid);
        orderedDAO.deleteById(ordered.getId());
        return ResultFactory.buildSuccessResult(bid);
    }

    @GetMapping(value="/api/orderedBooks/{uid}")
    public Result orderedBooks(@PathVariable Integer uid){
        List<Ordered> ord = orderedDAO.findByUid(uid);
        List<Book> books = new ArrayList<>();
        for (Ordered bid: ord
             ) {
            books.add(bookDAO.findBookById(bid.getBid()));
        }
        /*System.out.println(books);*/
        return ResultFactory.buildSuccessResult(books);
    }

}
