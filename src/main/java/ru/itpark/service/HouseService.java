package ru.itpark.service;

import ru.itpark.model.House;
import ru.itpark.util.JdbcTemplate;

import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;

public class HouseService {
    //    private Collection<House> houses;
//
    public HouseService() throws SQLException {
        List<House> houses = JdbcTemplate.executeQuery(
                "jdbc:sqlite:db.sqlite",
                "SELECT id, price, district, underground FROM houses",
                resultSet -> (new House(
                        resultSet.getInt("id"),
                        resultSet.getInt("price"),
                        resultSet.getInt("rooms"),
                        resultSet.getString("district"),
                        resultSet.getString("underground")
                )));
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

