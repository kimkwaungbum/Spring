package com.example.jaego.repository;

import com.example.jaego.entity.Fruit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FruitMapper {
    Fruit selectById(int no);
}
