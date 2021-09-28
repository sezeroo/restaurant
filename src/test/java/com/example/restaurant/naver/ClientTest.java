package com.example.restaurant.naver;

import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.service.WishListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientTest {

    @Autowired
    private NaverClient naverClient;

    @Autowired
    private WishListService wishListService;

    @Test
    public void localSearchTest(){

        SearchLocalReq searchLocalReq = new SearchLocalReq();

        searchLocalReq.setQuery("갈비집");

        SearchLocalRes result = naverClient.searchLocalRes(searchLocalReq);

        System.out.println(result);

    }

    @Test
    public void localImageSearchTest(){

        SearchImageReq searchImageReq = new SearchImageReq();

        searchImageReq.setQuery("갈비집");

        SearchImageRes result = naverClient.imageSearch(searchImageReq);

        System.out.println(result);


    }

    @Test
    public void ControllerTest(){

        WishListDto wishListdto = wishListService.search("갈비집");
        System.out.println(wishListdto);

    }




}
