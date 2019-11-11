package ru.itpark;

import ru.itpark.model.House;
import ru.itpark.service.HouseService;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:db.sqlite")) {
            try
                    (Statement statement = connection.createStatement()) {
                try
                        (ResultSet resultSet = statement.executeQuery("SELECT id,price,rooms,district,underground FROM houses")) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt("id"));
                        System.out.println(resultSet.getInt("price"));
                        System.out.println(resultSet.getInt("rooms"));
                        System.out.println(resultSet.getString("district"));
                        System.out.println(resultSet.getString("underground"));

                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        HouseService service = new HouseService();
        List<House> results1 = service.searchByDistrict("Кировский");
        System.out.println(results1);
//        List<House> housesByPriceAsc = service.sort((o1, o2) -> o1.getPrice() - o2.getPrice());
//        System.out.println(housesByPriceAsc);
//        List<House> housesByPriceDesc = service.sort((o1, o2) -> o2.getPrice() - o1.getPrice());
//        System.out.println(housesByPriceDesc);
    }
}
