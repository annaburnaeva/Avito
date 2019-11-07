package ru.itpark.service;

import ru.itpark.model.House;
import ru.itpark.util.JdbcTemplate;

import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;

public class HouseService {
    private Collection<House> houses;

    public HouseService() throws SQLException {
        this.houses = JdbcTemplate.executeQuery(
                "jdbc:sqlite:db.sqlite",
                "SELECT id, price, district, underground FROM houses",
                resultSet -> (new House(
                        resultSet.getInt("id"),
                        resultSet.getInt("price"),
                        resultSet.getString("district"),
                        resultSet.getString("underground")
                )));
    }

    public void add(House item) {
        houses.add(item);
    }

    public void addAll(House... items) {
        Collections.addAll(this.houses, items);
    }

    public void addAll(Collection<House> items) {
        this.houses.addAll(items);
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
        List<House> result = new ArrayList<>(houses);
        result.sort(comparator);
        return result;
    }

}

