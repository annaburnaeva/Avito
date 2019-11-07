create table houses
(
    id          integer primary key autoincrement,
    price       integer not null check ( price > 0 ),
    rooms       integer not null check ( rooms > 0 ),
    district    text    not null,
    underground text
);
insert into houses(price, rooms, district, underground)
values (7000000, 2, 'Вахитовский', 'Суконная Слобода');
insert into houses(price, rooms, district, underground)
VALUES (4500000, 1, 'Московский', 'Кремлевская');
insert into houses(price, rooms, district, underground)
VALUES (5600000, 1, 'Кировский', 'Яшьлек');
insert into houses(price, rooms, district, underground)
VALUES (10600000, 4, 'Вахитовский', 'Горки');
insert into houses(price, rooms, district, underground)
VALUES (8900000, 3, 'Авиастроительный', 'Дубравная');
insert into houses(price, rooms, district, underground)
VALUES (11200000, 4, 'Кировский', 'Аметьево');
insert into houses(price, rooms, district, underground)
VALUES (2900000, 1, 'Авиастроительный', 'Горки');