INSERT INTO Room(name) values ('ZLI');
INSERT INTO Room(name) values ('BBW');
INSERT INTO Room(name) values ('TBZ');

INSERT INTO Category(name) values ('Programming');
INSERT INTO Category(name) values ('Gaming');
INSERT INTO Category(name) values ('Working');

INSERT INTO Application_User(username, password, role) values ('test', 'password', 'ADMIN');
INSERT INTO Application_User(username, password, role) values ('user', 'password', 'USER');
INSERT INTO Application_User(username, password, role) values ('muster', '1234', 'USER');

INSERT INTO entry(check_in, check_out, category_id, room_id, application_user_id) values
('2020-11-20 12:12:00','2020-11-21 12:12:00', 1, 1, 2);
INSERT INTO entry(check_in, check_out, category_id, room_id, application_user_id) values
('2020-11-20 12:12:00','2020-11-21 12:12:00', 2, 2, 2);
INSERT INTO entry(check_in, check_out, category_id, room_id, application_user_id) values
('2020-11-20 12:12:00','2020-11-21 12:12:00', 3, 3, 2);
