### Инструкция по запуску на локальной машине
* создание базы данных
```bash
docker build -t socnet-db ./
docker run -d --name socnet-db-container -p 5432:5432 socnet-db
```
* подготовка базы данных (схема расположена в ```/src/main/resources/db/migration/V1_0__create_db_schema.sql```)
```bash
mvn flyway:migrate -Dflyway.configFiles=flyway.conf
```
* запуск приложения
```bash
mvn spring-boot:run
```
После этого приложение доступно по адресу http://localhost:5050.
