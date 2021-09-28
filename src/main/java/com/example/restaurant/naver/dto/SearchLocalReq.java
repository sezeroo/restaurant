package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
//요청을 보낼떄 필요한 Request 클래스
public class SearchLocalReq {

    private String query = "";

    private int display = 1;

    private int start = 1;

    private String sort = "random";


    //보다편리하게 파라미터를 넘겨주기위해 메서드 정의해주기.
    //변수를 하나하나 설정해주는데 시간을 아끼기 위해.
    public MultiValueMap<String, String> toMultiValueMap(){

        MultiValueMap<String ,String> map = new LinkedMultiValueMap<>();

        map.add("query",query);
        map.add("display",String.valueOf(display));
        map.add("start",String.valueOf(start));
        map.add("sort",sort);

        return map;
    }



}
