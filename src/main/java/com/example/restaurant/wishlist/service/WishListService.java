package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.entity.WishListEntity;
import com.example.restaurant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query){

        //지역검색
        SearchLocalReq searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        SearchLocalRes searchLocalRes = naverClient.searchLocalRes(searchLocalReq);

        //검색결과가 있을때
        if(searchLocalRes.getTotal() > 0 ){

            //응답객체의 localItem 변수를 가져온다
            //stream 을 사용하여 가져와주자.
            SearchLocalRes.SearchLocalItem localItem =
                    searchLocalRes.getItems().stream().findFirst().get();

            //특수문자열 모두 삭제.
            String imageQuery = localItem.getTitle().replaceAll("<[^>]*>","");

            SearchImageReq searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            SearchImageRes searchImageRes = naverClient.imageSearch(searchImageReq);

            //이미지검색
            if(searchImageRes.getTotal() > 0){

                SearchImageRes.SearchImageItems imageItems =
                        searchImageRes.getItems().stream().findFirst().get();

                ///결과 리턴

                //내가 받아오고 싶은 객체
                //기존에 지정해놓은 WishListEntity 가있지만
                //WishListDto 를 만들어 사용해주자.
                WishListDto result = new WishListDto();

                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImage(imageItems.getLink());

                return result;
            }


        }
        //검색결과가 없으면.
        return new WishListDto();

    }


    public WishListDto add(WishListDto wishListdto) {
        //컨트롤러에서 Service 를 사용하기때문에
        //dto 객체를 전달받는다.
        //전달받은 dto 를 entity로 변환해주고
        //wishListRepository 에 저장해준다.
        WishListEntity entity = dtoToEntity(wishListdto);
        WishListEntity saveEntity = wishListRepository.save(entity);

        //그후 entity 를 dto 로 변환해준후 리턴해준다.
        return entityToDto(saveEntity);
    }


    //WishListDto 로 전달받은 객체를 Entity 로 변경해주어야
    //DbRepository 에 저장할수 있다.

    // dto 를 가지고 entity 로변경.
    private WishListEntity dtoToEntity(WishListDto wishListdto){
        WishListEntity entity = new WishListEntity();

        entity.setIndex(wishListdto.getIndex());
        entity.setTitle(wishListdto.getTitle());
        entity.setCategory(wishListdto.getCategory());
        entity.setAddress(wishListdto.getAddress());
        entity.setRoadAddress(wishListdto.getRoadAddress());
        entity.setHomePageLink(wishListdto.getHomePageLink());
        entity.setImage(wishListdto.getImage());
        entity.setVisit(wishListdto.isVisit());
        entity.setVisitCount(wishListdto.getVisitCount());
        entity.setLastVisitDate(wishListdto.getLastVisitDate());

        return entity;
    }


    //entity 를가지고 dto 로 변경.
    private WishListDto entityToDto(WishListEntity wishListEntity){
        WishListDto dto = new WishListDto();

        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImage(wishListEntity.getImage());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());

        return dto;

    }


    //위시리스트 전부 조회하기.
    public List<WishListDto> findAll() {


        return wishListRepository.findAll().stream().map(it-> entityToDto(it))
                .collect(Collectors.toList());
    }


    public void delete(int index) {

        wishListRepository.deleteById(index);
    }


    //방문횟수 늘려주기.
    public void addVisit(int index){

            Optional<WishListEntity> wishItem = wishListRepository.findById(index);

            if(wishItem.isPresent()){
                WishListEntity item = wishItem.get();

                item.setVisit(true);

                item.setVisitCount(item.getVisitCount()+1);
            }
        }




    }

