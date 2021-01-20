-- PostgreSQL10

CREATE DATABASE "product_catalog";

CREATE USER tester WITH PASSWORD 'test_password';

GRANT ALL PRIVILEGES ON DATABASE product_catalog to tester;

GRANT USAGE ON SCHEMA public TO tester;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO tester;

GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO tester;

CREATE TABLE "product" (
                           "id" serial NOT NULL,
                           "name" varchar(45) DEFAULT NULL,
                           "type" varchar(45) DEFAULT NULL,
                           "description" varchar(250) DEFAULT NULL,
                           "price" int DEFAULT NULL,
                           PRIMARY KEY ("id")
);

ALTER SEQUENCE product_id_seq RESTART WITH 6;

INSERT INTO "product" VALUES
(1,'Масдаам','Сыр','Сыр с большими дырками',800),
(2,'Докторская','Колбаса','Вареная колбаса завода Ремит',400),
(3,'Московский провансаль','Майонез','Майонез оливковый 67%',70),
(4,'Rich','Сок','Сок апельсиновый востановленный',4),
(5,'Чиабатта','Хлеб','Итальянская булка',50);