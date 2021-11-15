DROP DATABASE car_rental;
CREATE DATABASE car_rental;
use car_rental;


CREATE TABLE passports
(
    id             INT PRIMARY KEY,
    name           VARCHAR(50),
    surname        VARCHAR(50),
    patronymic     VARCHAR(50),
    birthday       datetime,
    address        VARCHAR(100)
);

CREATE TABLE users
(
    id              INT PRIMARY KEY,
    user_login      VARCHAR(50),
    user_password   VARCHAR(50),
    user_role       VARCHAR(50),
    passport_id     INT,
    FOREIGN KEY (passport_id) REFERENCES passports (id)
);

INSERT INTO users
VALUE (1, 'admin','root','ADMIN',NULL );
INSERT INTO users
VALUE (2, 'stason420','s200113','CLIENT',NULL );
INSERT INTO users
VALUE (3, 'nesty420','n200104','CLIENT',NULL );


CREATE TABLE cars
(
    id                INT PRIMARY KEY,
    model             VARCHAR(50),
    price_per_day     DOUBLE,
    employment_status VARCHAR(50),
    damage_status     VARCHAR(50)
);

CREATE TABLE refunds
(
    id                INT PRIMARY KEY,
    damage_status     VARCHAR(50),
    type_damage       VARCHAR(50),
    price             DOUBLE
);

CREATE TABLE orders
(
    id         INT PRIMARY KEY,
    user_id    INT NOT NULL,
    car_id     INT NOT NULL,
    price      DOUBLE,
    status     VARCHAR(50),
    order_date datetime,
    rental_Period INT NOT NULL,
    refund_id  INT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (car_id) REFERENCES cars (id),
    FOREIGN KEY (refund_id) REFERENCES refunds (id)
);





INSERT INTO cars
VALUES (1, 'MERCEDES s63 AMG', 210, 'FREE', 'WITHOUT DAMAGE');

INSERT INTO cars
VALUES (2, 'Tesla model x', 140, 'FREE', 'WITHOUT DAMAGE');

INSERT INTO cars
VALUES (3, 'MERCEDES g63 AMG', 200, 'FREE', 'WITHOUT DAMAGE');

INSERT INTO cars
VALUES (4, 'Nissan GTR',200,'FREE','WITHOUT DAMAGE');

INSERT INTO cars
VALUES (5, 'Porsche 911',280,'FREE','WITHOUT DAMAGE');

INSERT INTO cars
VALUES (6, 'Porsche 911 Turbo S',320,'FREE','WITHOUT DAMAGE');

INSERT INTO cars
VALUES (7, 'Porsche Cayenne',220,'FREE','WITHOUT DAMAGE');
