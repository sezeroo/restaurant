package com.example.restaurant.wishlist.repository;

import com.example.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.restaurant.wishlist.entity.WishListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//저장소로 사용할것이라는 어노테이션 붙여줘야함.
//만들어놓은 추상클래스 상속받아서 사용.
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {



}
