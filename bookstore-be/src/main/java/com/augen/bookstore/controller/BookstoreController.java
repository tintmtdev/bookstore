package com.augen.bookstore.controller;

import com.augen.bookstore.dto.BooksDTO;
import com.augen.bookstore.dto.BuyBookDTO;
import com.augen.bookstore.dto.OrderBookDTO;
import com.augen.bookstore.dto.SearchParameters;
import com.augen.bookstore.services.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("bookstore")
public class BookstoreController {

    @Autowired
    BookstoreService bookstoreService;

    @GetMapping("/list")
    public Mono<ResponseEntity<List<BooksDTO>>> getBookstoreList(SearchParameters parameters) {
        return Mono.just(ResponseEntity.ok().body(bookstoreService.getBookstoreListByKeyword(parameters)));
    }

    @GetMapping("/detail/{id}")
    public Mono<ResponseEntity<BooksDTO>> getBookDetail(@PathVariable("id") String id) {
        return Mono.just(ResponseEntity.ok().body(bookstoreService.getBookDetail(id)));
    }

    @PostMapping("add")
    public Mono<ResponseEntity<OrderBookDTO>> insertOrder(@RequestBody BuyBookDTO buyBookDTO) throws Exception {
        return Mono.just(ResponseEntity.ok().body(bookstoreService.insertOrder(buyBookDTO)));
    }
}
