package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//조건에 맞게 요청을 보낸후 서버쪽에서 보내주는
//Response 를 받을 클래스 작성.
public class SearchLocalRes {

    private String lastBuildDate;

    private int total;

    private int start;

    private int display;


    //가이드에 list 형식처럼 들어가 있다고 나와있음.
    private List<SearchLocalItem> items;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    //Response items 가 List 형식으로 여러개 들어있기때문에
    //SearchLocalItem 클래스를 만들어 리스트에 넣어주기.
    public static class SearchLocalItem{
        private String title;

        private String link;

        private String description;

        private String telephone;

        private String address;

        private String roadAddress;

        private String category;

        private String mapx;

        private String mapy;

    }



}

