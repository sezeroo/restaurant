package com.example.restaurant.wishlist.repository;

import com.example.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest //상위 객체들을 모두 AutoWired 하여 사용할 수 있도록 해준다.
                //어노테이션 없이는 하나하나 임포트 해서 사용해야한다.
public class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity create(){
        WishListEntity wishListEntity = new WishListEntity();
        wishListEntity.setTitle("title");
        wishListEntity.setCategory("category");
        wishListEntity.setAddress("address");
        wishListEntity.setRoadAddress("readAddress");
        wishListEntity.setHomePageLink("");
        wishListEntity.setImage("");
        wishListEntity.setVisit(false);
        wishListEntity.setVisitCount(0);
        wishListEntity.setLastVisitDate(null);

        return wishListEntity;
    }

    @Test
    public void saveTest(){

        WishListEntity wishListEntity = create();

        System.out.println("wishListEntity: " + wishListEntity);


        WishListEntity expected = wishListRepository.save(wishListEntity);

         Assertions.assertNotNull(expected);
        Assertions.assertEquals(1,expected.getIndex());
    }

    @Test
    public void updateTest(){

        WishListEntity wishListEntity = create();
        WishListEntity expected = wishListRepository.save(wishListEntity);


        expected.setTitle("update test");
        WishListEntity saveEntity = wishListRepository.save(expected);

        Assertions.assertEquals("update test", saveEntity.getTitle());
        Assertions.assertEquals(1,wishListRepository.findAll().size());

    }

    @Test
    public void findByIdTest(){

        WishListEntity wishListEntity = create();
        wishListRepository.save(wishListEntity);

        Optional<WishListEntity> expected = wishListRepository.findById(1);

        Assertions.assertEquals(true, expected.isPresent());
        Assertions.assertEquals(1,expected.get().getIndex());


    }


    @Test
    public void removeTest(){

        WishListEntity created = create();
        wishListRepository.save(created);

        wishListRepository.deleteById(1);

        int count = wishListRepository.findAll().size();

        Assertions.assertEquals(0,count);
    }


    @Test
    public void listAllTest(){
        WishListEntity wishListEntity = create();

        WishListEntity expected1 = wishListRepository.save(wishListEntity);
        WishListEntity expected2 = wishListRepository.save(wishListEntity);

        int count = wishListRepository.findAll().size();

        Assertions.assertEquals("2",count);

    }

}
