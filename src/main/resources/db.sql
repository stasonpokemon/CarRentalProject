DROP DATABASE car_rental;
CREATE DATABASE car_rental;
use car_rental;


CREATE TABLE passports
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(50),
    surname        VARCHAR(50),
    patronymic     VARCHAR(50),
    day_birthday   INT,
    month_birthday INT,
    year_birthday  INT,
    address        VARCHAR(100)
);

CREATE TABLE users
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    user_login      VARCHAR(50),
    user_password   VARCHAR(50),
    user_role       VARCHAR(50),
    passport_id     INT,
    FOREIGN KEY (passport_id) REFERENCES passports (id)
);

INSERT INTO users
VALUE (NULL, 'stason420','s200113','CLIENT',NULL );
INSERT INTO users
VALUE (NULL, 'nesty420','n200104','CLIENT',NULL );
INSERT INTO users
VALUE (NULL, 'admin','root','ADMIN',NULL );

CREATE TABLE cars
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    model             VARCHAR(50),
    price_per_day     DOUBLE,
    employment_status VARCHAR(50),
    damage_status     VARCHAR(50)
);

CREATE TABLE orders
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    user_id    INT NOT NULL,
    car_id     INT NOT NULL,
    price      DOUBLE,
    status     VARCHAR(50),
    order_date datetime,
    refund_id  INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (car_id) REFERENCES cars (id),
    FOREIGN KEY (refund_id) REFERENCES refunds (id)
);

CREATE TABLE refunds
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    damage_status     VARCHAR(50),
    type_damage       VARCHAR(50),
    price             DOUBLE,
    FOREIGN KEY (order_id) REFERENCES orders (id)
);



INSERT INTO cars
VALUES (NULL, 'MERCEDES s63 AMG', 210, 'FREE', 'WITHOUT DAMAGE');

INSERT INTO cars
VALUES (NULL, 'Tesla model x', 140, 'FREE', 'WITHOUT DAMAGE');

INSERT INTO cars
VALUES (NULL, 'MERCEDES g63 AMG', 200, 'FREE', 'WITHOUT DAMAGE');

INSERT INTO cars
VALUES (NULL, 'Nissan GTR',200,'FREE','WITHOUT DAMAGE');

INSERT INTO cars
VALUES (NULL, 'Porsche 911',280,'FREE','WITHOUT DAMAGE');

INSERT INTO cars
VALUES (NULL, 'Porsche 911 Turbo S',320,'FREE','WITHOUT DAMAGE');

INSERT INTO cars
VALUES (NULL, 'Porsche Cayenne',220,'FREE','WITHOUT DAMAGE');
