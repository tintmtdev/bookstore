package com.augen.bookstore.services;

import com.augen.bookstore.dto.BooksDTO;
import com.augen.bookstore.dto.BuyBookDTO;
import com.augen.bookstore.dto.OrderBookDTO;
import com.augen.bookstore.dto.SearchParameters;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookstoreService {
    List<BooksDTO> getBookstoreListByKeyword(SearchParameters parameters);

    BooksDTO getBookDetail(String id);

    OrderBookDTO insertOrder(BuyBookDTO buyBook) throws Exception;
}
