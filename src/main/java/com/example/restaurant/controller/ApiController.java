package com.example.restaurant.controller;

import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
@Slf4j
public class ApiController {

//    //기존
//    @Autowired
//    private WishListService wishListService;

    private final WishListService wishListService;



    //검색하기.
    @GetMapping("/search")
    public WishListDto search(@RequestParam String query){


        return wishListService.search(query);
    }

    //위시리스트 추가하기.
    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto wishListdto){

        log.info("{}",wishListdto);

       return wishListService.add(wishListdto);
    }


    //위시리스트 불러오기.
    @GetMapping("/wish-list")
    public List<WishListDto> findAll(){


        return wishListService.findAll();
    }

    //위시리스트 삭제하기.
    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index){
        wishListService.delete(index);

    }

    //방문횟수 추가하기.
    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){

        wishListService.addVisit(index);
    }



}
