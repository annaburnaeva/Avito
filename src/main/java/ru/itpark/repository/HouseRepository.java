package ru.itpark.repository;

import ru.itpark.model.House;

import java.util.Optional;

public interface HouseRepository <T>{
    House save(House house);
    Optional<House> getById(int id);

}
