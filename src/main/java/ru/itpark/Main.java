package ru.itpark;

import ru.itpark.model.House;
import ru.itpark.repository.HouseRepositoryJdbcImpl;
import ru.itpark.service.HouseService;


public class Main {
    public static void main(String[] args) {

        final HouseService service = new HouseService(new HouseRepositoryJdbcImpl("jdbc:sqlite:db.sqlite"));
        final House house1 = service.register(new House(0, 3900000, 3, "Кировский", "Кремлевская"));
        System.out.println(house1);
        System.out.println(service.searchByUnderground("Горки"));

    }
}

