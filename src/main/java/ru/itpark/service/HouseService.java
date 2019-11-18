package ru.itpark.service;

import ru.itpark.exception.HouseNotFoundException;
import ru.itpark.model.House;
import ru.itpark.repository.HouseRepository;
import ru.itpark.util.JdbcTemplate;

import java.util.*;

public class HouseService {
    private final HouseRepository repository;

    public HouseService(HouseRepository repository) {
        this.repository = repository;
    }


    public House register(House house) {
        if (house.getId() != 0) {
            throw new IllegalArgumentException("House id must be 0 for registration");
        }
        return repository.save(house);
    }

    public List<House> searchByDistrict(String district) {
        List<House> listByDistrict = new ArrayList<>();
        listByDistrict.removeIf(o -> !o.getDistrict().equals(district));
        return listByDistrict;
    }

    public List<House> searchByUnderground(String underground) {

        List<House> listByUnderground = new ArrayList<>();
        listByUnderground.removeIf(o -> !o.getUnderground().equals(underground));
        return listByUnderground;
    }

    public List<House> searchByPrice(int minPrice, int maxPrice) {
        List<House> listByPrice = new ArrayList<>();
        listByPrice.removeIf(o -> o.getPrice() >= minPrice && o.getPrice() <= maxPrice);
        return listByPrice;
    }

    public List<House> sort(Comparator<House> comparator) {
        List<House> result = new ArrayList<>();
        result.sort(comparator);
        return result;
    }
}

