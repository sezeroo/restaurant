package com.example.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T>{

    //해당타입에 대해 옵셔널 하게 리턴해주는 메소드 생성
    //index 를 통해 T 객체의 값을 찾아주는 메소드.
    Optional<T>findById(int index);

    //저장해주는 메소드
    T save(T entity);

    //삭제하는 메소드
    void deleteById(int index);

    //전체를 리턴해주는 메소드 구현하기.
    List<T> findAll();

}
