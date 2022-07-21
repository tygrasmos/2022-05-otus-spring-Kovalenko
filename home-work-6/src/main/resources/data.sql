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

insert into comments(id, comment, book_id) values (1, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (2, 'Это классика конечно', 1);
insert into comments(id, comment, book_id) values (3, 'Умные мысли', 2);
insert into comments(id, comment, book_id) values (4, 'Старовато', 6);
insert into comments(id, comment, book_id) values (5, 'Автор слишком исскушён...', 5);
insert into comments(id, comment, book_id) values (6, 'Интересно, но мог бы и получше', 6);
insert into comments(id, comment, book_id) values (7, 'Да, это то, что нам нужно!', 3);
insert into comments(id, comment, book_id) values (8, 'Сложный язык', 2);
insert into comments(id, comment, book_id) values (9, 'Где-то, что-то такое я уже читал.', 2);
insert into comments(id, comment, book_id) values (10, 'Мощно.', 2);
insert into comments(id, comment, book_id) values (11, 'Как же вы достали своими нравоученями', 4);
insert into comments(id, comment, book_id) values (12, 'Люблю поэзию', 3);
/*insert into comments(id, comment, book_id) values (13, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (14, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (15, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (16, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (17, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (18, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (19, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (20, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (21, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (22, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (23, 'Прикольная книжонка', 1);
insert into comments(id, comment, book_id) values (24, 'Прикольная книжонка', 1);*/


