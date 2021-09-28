package com.example.restaurant.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
//데이터에 공통적으로 해당하는 사항이라고 생각하기.
//모든데이터베이스를 가지는 애들은 이 객체를 상속받을거다.
public class MemoryDbEntity {

    protected Integer index;




}
