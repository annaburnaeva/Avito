package ru.itpark.repository;

import ru.itpark.model.House;
import ru.itpark.util.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class HouseRepositoryJdbcImpl<T> implements HouseRepository<T> {
    private final String url;

    public HouseRepositoryJdbcImpl(String url) {
        this.url = url;
    }

    @Override
    public House save(House house) {
        return house.getId() == 0 ? insert(house) : update(house);
    }

    @Override
    public Optional<House> getById(int id) {
        String sql = "SELECT id, price, rooms, district, underground unit FROM houses WHERE id = ?";
        return JdbcTemplate.executeQueryForObject(url, sql, stmt -> stmt.setInt(1, id), rs -> new House(
                rs.getInt("id"),
                rs.getInt("price"),
                rs.getInt("rooms"),
                rs.getString("district"),
                rs.getString("underground")
        ));
    }

    private House insert(House house) {
        String sql = "INSERT INTO houses (price, rooms, district, underground) VALUES(?, ?, ?, ?);";

        int id = JdbcTemplate.executeUpdateReturningId(url, sql, stmt -> {
            stmt.setInt(1, house.getPrice());
            stmt.setInt(2, house.getRooms());
            stmt.setString(3, house.getDistrict());
            stmt.setString(4, house.getUnderground());
        });
        house.setId(id);
        return house;
    }

    private House update(House house) {
        String sql = "UPDATE houses SET price = ?, rooms = ?, district = ?, underground = ? WHERE id = ?;";

        JdbcTemplate.executeUpdate(url, sql, stmt -> {
            stmt.setInt(1, house.getPrice());
            stmt.setInt(2, house.getRooms());
            stmt.setString(3, house.getDistrict());
            stmt.setString(4, house.getUnderground());
            stmt.setInt(5, house.getId());
        });
        return house;
    }
}

