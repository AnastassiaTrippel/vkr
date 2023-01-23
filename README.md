# [ВКР - МУИВ](https://github.com/AnastassiaTrippel/vkr)

## Software:
```
* Java 11
* Maven 4.0
* Docker
```

Также для разработки приложения рекомендуется использовать:
* OS [Ubuntu 20.04](https://releases.ubuntu.com/20.04/) 
* DBeaver - для подключению к БД
```bash
sudo snap install dbeaver-ce
```
* Postman - для создания "заглушек" и тестирования REST запросов
```bash
sudo snap install postman
```

## Необходимо установить maven для сборки приложения. Установка maven:
```bash
sudo apt install maven
```

## Сборка проекта и докер образа:
```bash
mvn clean install
mvn spring-boot:build-image
```

## Для работы приложения необходимо запустить докер-контейнеры с PostgreSQL и самим приложением. Запуск docker:

```bash
cd ./src/main/java/com/example/muivvkr/docker/
docker-compose up -d
```

## Остановка приложения:

```bash
cd ./src/main/java/com/example/muivvkr/docker/
docker-compose down
```


### Developer:
```
Анастасия Триппель

a-nastasia@bk.ru
```