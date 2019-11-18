package ru.itpark.repository;

import ru.itpark.model.House;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class HouseRepositoryInMemoryImpl implements HouseRepository {
    private final Collection<House> houses = new ArrayList<>();
    private int nextId = 1;

    @Override
    public House save(House house) {
        if (house.getId() == 0) {
            house.setId(nextId++);
            nextId++;
            return house;
        }

        boolean removed = houses.removeIf(o -> o.getId() == house.getId());
        if (!removed) {
            throw new IllegalArgumentException("Id not exists: " + house.getId());
        }
        houses.add(house);
        return house;
    }

    @Override
    public Optional<House> getById(int id) {
        return houses.stream()
                .filter(o -> o.getId() == id)
                .findFirst();
    }
}

