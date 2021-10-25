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