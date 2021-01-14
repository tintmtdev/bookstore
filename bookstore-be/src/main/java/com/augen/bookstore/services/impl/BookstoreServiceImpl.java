package com.augen.bookstore.services.impl;

import com.augen.bookstore.constants.DeliveryServiceEnum;
import com.augen.bookstore.constants.SearchTypeEnum;
import com.augen.bookstore.dto.*;
import com.augen.bookstore.services.BookstoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
public class BookstoreServiceImpl implements BookstoreService {

    @Override
    public List<BooksDTO> getBookstoreListByKeyword(SearchParameters parameters) {
        String searchType = (parameters.getSearchType() != null) ? parameters.getSearchType() : "";
        List<BooksDTO> booksDTOList;
        if (searchType.equals(SearchTypeEnum.STORE.getType())) {
            booksDTOList = getDataFromServer(parameters.getKeyword());
        } else if (searchType.equals(SearchTypeEnum.CSV.getType())) {
            booksDTOList = getDataFromCsv(parameters.getKeyword());
        } else {
            booksDTOList = getDataFromGoogle(parameters.getKeyword());
        }
        return booksDTOList;
    }

    private List<BooksDTO> getDataFromGoogle(String keyword) {
        List<BooksDTO> booksDTOList = new ArrayList<>();
        String googleUrl = "https://www.googleapis.com/books/v1/volumes?q=" + keyword;
        String responseData = getResponseGoogleUrl(googleUrl);
        if (responseData != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            GoogleBooksDTO booksDTO;
            try {
                booksDTO = objectMapper.readValue(responseData, GoogleBooksDTO.class);
                if (booksDTO != null) {
                    booksDTOList = booksDTO.getItems();
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return booksDTOList;
    }

    @Override
    public BooksDTO getBookDetail(String id) {
        String googleUrl = "https://www.googleapis.com/books/v1/volumes/" + id;
        String responseData = getResponseGoogleUrl(googleUrl);

        if (responseData != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                BooksDTO booksDTO = objectMapper.readValue(responseData, BooksDTO.class);
                if (booksDTO != null) {
                    booksDTO.setDeliveryServices(getCurrentBaseFactor());
                }
                return booksDTO;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String getResponseGoogleUrl(String gUrl) {
        String response = null;
        try {
            URL googleUrl = new URL(gUrl);
            HttpURLConnection connection = (HttpURLConnection)googleUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if ( connection.getResponseCode() == 200) {
                StringBuilder responseData = new StringBuilder();
                Scanner scanner = new Scanner(googleUrl.openStream());
                while (scanner.hasNext()) {
                    responseData.append(scanner.nextLine());
                }
                scanner.close();
                response = responseData.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private List<DeliveryServiceDTO> getCurrentBaseFactor() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonth().getValue();
        List<DeliveryServiceDTO> serviceDTOList = new ArrayList<>();
        for (DeliveryServiceEnum delivery : DeliveryServiceEnum.values()) {
            DeliveryServiceDTO deliveryService = new DeliveryServiceDTO();
            deliveryService.setServiceType(delivery.getType());
            deliveryService.setCost(delivery.getCost());
            if (currentMonth == 9) {
                deliveryService.setFactor(delivery.getSep());
                deliveryService.setTotal(delivery.getSep() * delivery.getCost());
            } else if (currentMonth <= 8 && currentMonth >= 6) {
                deliveryService.setFactor(delivery.getJuneToAug());
                deliveryService.setTotal(delivery.getJuneToAug() * delivery.getCost());
            } else {
                deliveryService.setFactor(delivery.getOtherMonth());
                deliveryService.setTotal(delivery.getOtherMonth() * delivery.getCost());
            }
            serviceDTOList.add(deliveryService);
        }
        return serviceDTOList;
    }

    private List<BooksDTO> getDataFromCsv(String keyword) {
        // TODO
        return null;
    }

    private List<BooksDTO> getDataFromServer(String keyword) {
        // TODO
        return null;
    }

    @Override
    public OrderBookDTO insertOrder(BuyBookDTO buyBook) throws Exception {
        if (buyBook.getService() == null) {
            throw new Exception("The service type not found");
        }
        Double costPrice = (buyBook.getCost() > 0) ? buyBook.getCost() : 0;
        OrderBookDTO orderBook = new OrderBookDTO();
        orderBook.setShipBy(buyBook.getService());
        orderBook.setTotalCost(costPrice);
        orderBook.setDateDelivery(new Date());

        long number = (long) (Math.random() * 999999999);
        orderBook.setDeliveryName("TestName"+number);
        orderBook.setDeliveryNumber(String.valueOf(number));

        return orderBook;
    }

}
