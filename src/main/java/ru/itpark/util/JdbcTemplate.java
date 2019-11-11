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
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
        ) {
            List<T> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(mapper.map(resultSet));
            }
            return result;

        }
    }
}
