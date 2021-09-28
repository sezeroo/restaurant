package com.example.restaurant.db;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//extends MemoryDbEntity 를 상속받았기 때문에
//상속받은 부모클래스의 변수를 사용한다.
 public abstract class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T> {

    // db 는 List<MemoryDbEntity> db 가 되는것.
    private final List<T> db = new ArrayList<>();

    //primaryKey 라고 생각하자.
    private int index = 0;

    //index 를 사용해 찾기.
    @Override
    public Optional<T> findById(int index) {

        //getIndex() 는 MemoryDbEntity 객체의 Index 를 불러오는것
        return  db.stream().filter(it->it.getIndex() ==index).findFirst();
    }

    @Override
    public T save(T entity) {

        Optional<T> OptionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        //db 에 데이터가 없는 경우

        if(!OptionalEntity.isPresent()){
            //db 와 일치하는 index 가 없기 때문에 db.index 값을 1 올려준다.
            index++;
            //1올려준 db.index 의 값과 entity 의 index 값을 일치시켜준다.
            entity.setIndex(index);
            db.add(entity);
        }
        //db에 데이터가 있는경우
        //덮어쓰기 해줘야한다.
        else{
            //이미 데이터가 있는경우에는 기존에 있던 index 를 가져와 update 해줘야한다,
            int PreIndex = OptionalEntity.get().getIndex();
            //저장해줄 entity 에 불러온 index 값을 저장해줌.
            entity.setIndex(PreIndex);

            //불러온 index 삭제
            deleteById(PreIndex);
            //db에 entity 값 넣어주기.
            db.add(entity);
        }
        return entity;

    }

    @Override
    public void deleteById(int index) {

        Optional<T> OptionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();



        if(OptionalEntity.isPresent()){
            db.remove(OptionalEntity.get());
        }

    }

    public List<T> findAll() {


        return db;
    }
}
