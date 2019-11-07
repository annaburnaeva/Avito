package ru.itpark.util;

import ru.itpark.model.House;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JdbcTemplate {
    private JdbcTemplate() {
    }

    public static <T> List<T> executeQuery(String url, String sql, RowMapper<T> mapper) throws SQLException {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {
            List<T> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(mapper.map(resultSet));
            }
            return result;
        }
    }

    public static void insert(Connection connection, House house) {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO houses (id, price, rooms, district, underground)\n" +
                                "VALUES " +
                                "(?, ?, ?, ?, ?);");
        ) {
            statement.setInt(1, house.getId());
            statement.setInt(2, house.getPrice());
            statement.setInt(3, house.getRooms());
            statement.setString(4, house.getDistrict());
            statement.setString(5, house.getUnderground());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
