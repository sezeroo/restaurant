package com.example.restaurant.naver;

import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NaverClient {

    //yaml 을 가져오는 방법
    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverSearchImage;

    //사용할 메소드  정의해주기.
    //api 가이드를 보고 어떤 파라미터가 필요한지 확인해주기.
    //파라미터 관련 클래스를 정의해주자(SearchLocalReq)
    //Request 보내주기 위한 메소드.
    public SearchLocalRes searchLocalRes(SearchLocalReq searchLocalReq){
        URI uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                //이전방식
                //.queryParam(searchLocalReq.getItem)
                //하지만 SearchLocalReq 에 메서드를 만들어 사용하는방법도있다.
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        System.out.println(uri);


        //헤더값을 넣어줘야 하기 때문에 생성.
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id",naverClientId);
        headers.set("X-Naver-Client-Secret",naverSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        //HttpEntity 는 Http 프로토콜에
        //body 와 header 값을 담을수 있다.
        HttpEntity<HttpHeaders> httpEntity  = new HttpEntity<>(headers);

        //RequestEntity 사용해보기.
//        RequestEntity<Void> requestEntity = RequestEntity
//                .get(uri)
//                .header("X-Naver-Client-Id",naverClientId)
//                .header("X-Naver-Client-Secret",naverSecret)
//                .build();

        System.out.println(httpEntity);

        ParameterizedTypeReference<SearchLocalRes> responseType =
                new ParameterizedTypeReference<SearchLocalRes>() {};


        ResponseEntity<SearchLocalRes> restTemplate= new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType);

       return restTemplate.getBody();
    }

    public SearchImageRes imageSearch(SearchImageReq searchImageReq){

        URI uri = UriComponentsBuilder.fromUriString(naverSearchImage)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        System.out.println("uri : " + uri);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id",naverClientId);
        httpHeaders.set("X-Naver-Client-Secret",naverSecret);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);

        ParameterizedTypeReference<SearchImageRes> responseType =
                new ParameterizedTypeReference<SearchImageRes>(){};

        ResponseEntity<SearchImageRes> restTemplate = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return restTemplate.getBody();
    }


}
