insert into authors(id, name) values (1, 'Пушкин');
insert into authors(id, name) values (2, 'Салтыков-Щедрин');
insert into authors(id, name) values (3, 'Лермонтов');
insert into authors(id, name) values (4, 'Желязны');
insert into authors(id, name) values (5, 'Пелевин');
insert into authors(id, name) values (6, 'Лесков');

insert into genres(id, name) values (1, 'Стихи');
insert into genres(id, name) values (2, 'Проза');
insert into genres(id, name) values (3, 'Фантастика');
insert into genres(id, name) values (4, 'Сказка');
insert into genres(id, name) values (5, 'Рассказ');
insert into genres(id, name) values (6, 'Новелла');

insert into books(id, name, author_id, genre_id) values (1, 'Девять принцев Амбера', 4, 3);
insert into books(id, name, author_id, genre_id) values (2, 'Generation Пи', 5, 2);
insert into books(id, name, author_id, genre_id) values (3, 'Мцыри', 3, 1);
insert into books(id, name, author_id, genre_id) values (4, 'Где то там...', 2, 5);
insert into books(id, name, author_id, genre_id) values (5, 'Сказка о царе Салтане', 1, 4);
insert into books(id, name, author_id, genre_id) values (6, 'Чапаев и пустота', 5, 2);

