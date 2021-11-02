DROP DATABASE car_rental;
CREATE DATABASE car_rental;
use car_rental;

CREATE TABLE admins
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    admin_login    VARCHAR(50),
    admin_password VARCHAR(50)
);

INSERT INTO admins
VALUE (NULL, 'admin','root');

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

CREATE TABLE clients
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    client_login    VARCHAR(50),
    client_password VARCHAR(50),
    passport_id     INT,
    FOREIGN KEY (passport_id) REFERENCES passports (id)
);

INSERT INTO clients
VALUE (NULL, 'stason420','s200113',NULL );
INSERT INTO clients
VALUE (NULL, 'nesty420','n200104',NULL );

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
    client_id  INT NOT NULL,
    car_id     INT NOT NULL,
    price      DOUBLE,
    status     VARCHAR(50),
    order_date datetime,
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (car_id) REFERENCES cars (id)
);

CREATE TABLE refunds
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    order_id          INT NOT NULL,
    damage_status     VARCHAR(50),
    price             DOUBLE
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
