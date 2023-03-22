INSERT INTO discount (code, id_customer, discount_in_percent) VALUES (5, 21, 10);
INSERT INTO discount (code, id_customer, discount_in_percent) VALUES (6, 12, 69);

INSERT INTO users (username, password) VALUES ('Dankmstr', '1234');
INSERT INTO users (username, password) VALUES ('PenguinKiller69', '1234');
INSERT INTO users (username, password) VALUES ('TrackMate', '1234');

INSERT INTO role (role_name) VALUES ('ADMIN');
INSERT INTO role (role_name) VALUES ('USER');

INSERT INTO authority (authority_name) VALUES ('CREATE');
INSERT INTO authority (authority_name) VALUES ('READ');
INSERT INTO authority (authority_name) VALUES ('UPDATE');
INSERT INTO authority (authority_name) VALUES ('DELETE');

INSERT INTO user_role VALUES (1, 1);
INSERT INTO user_role VALUES (2, 2);
INSERT INTO user_role VALUES (3, 2);

INSERT INTO role_authority VALUES (1, 1);
INSERT INTO role_authority VALUES (1, 2);
INSERT INTO role_authority VALUES (1, 3);
INSERT INTO role_authority VALUES (1, 4);
INSERT INTO role_authority VALUES (2, 1);