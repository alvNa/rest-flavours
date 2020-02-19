DROP TABLE IF EXISTS orders;

CREATE TABLE orders(id VARCHAR(255), description VARCHAR(255), price INT);
INSERT INTO orders(id, description) VALUES ('1','android');
INSERT INTO orders(id, description) VALUES ('2','i-phone');
INSERT INTO orders(id, description) VALUES ('3','android');
INSERT INTO orders(id, description) VALUES ('4','i-phone');

COMMIT
--CREATE TABLE cars(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), price INT);
--INSERT INTO cars(name, price) VALUES('Audi', "52642");
--INSERT INTO cars(name, price) VALUES('Mercedes', 57127);
--INSERT INTO cars(name, price) VALUES('Skoda', 9000);
--INSERT INTO cars(name, price) VALUES('Volvo', 29000);
--INSERT INTO cars(name, price) VALUES('Bentley', 350000);
--INSERT INTO cars(name, price) VALUES('Citroen', 21000);
--INSERT INTO cars(name, price) VALUES('Hummer', 41400);
--INSERT INTO cars(name, price) VALUES('Volkswagen', 21600);