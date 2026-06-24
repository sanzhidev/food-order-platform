# Order Platform

Учебный микросервисный backend-проект на Spring Boot 3 / Java 21, имитирующий упрощённый клон сервиса доставки еды (по мотивам курса Павла Сорокина "Yandex Food Clone").

Проект состоит из трёх независимых сервисов, взаимодействующих через REST и Apache Kafka, плюс общий модуль с переиспользуемыми DTO и событиями.

## Архитектура
order-platform/

├── order-service       # создание заказов, оркестрация оплаты и доставки

├── payment-service      # обработка платежей

├── delivery-service     # назначение курьера и доставка

└── common-libs          # общие DTO, Kafka-события

**Поток данных:**

1. Клиент создаёт заказ через `order-service` (`POST /orders`).
2. `order-service` инициирует оплату через HTTP-вызов к `payment-service`.
3. После успешной оплаты `order-service` публикует событие `OrderPaidEvent` в Kafka.
4. `delivery-service` слушает это событие, назначает курьера.
5. `delivery-service` публикует `DeliveryAssignedEvent` обратно в Kafka.
6. `order-service` слушает это событие и обновляет статус заказа.

## Стек технологий

- Java 21, Spring Boot 3.5
- Spring Data JPA + PostgreSQL
- Apache Kafka (Spring Kafka)
- Lombok, MapStruct
- SpringDoc OpenAPI (Swagger UI)
- Gradle (Kotlin DSL), мультимодульная сборка
- Docker Compose (для локального окружения)

## Как запустить проект локально

### Требования

- JDK 21
- Docker и Docker Compose
- Gradle (используется wrapper, отдельно ставить не нужно)

### 1. Клонировать репозиторий

```bash
git clone https://github.com/sanzhidev/order-platform.git
cd order-platform
```

### 2. Поднять инфраструктуру (PostgreSQL + Kafka)

```bash
docker-compose -f order-service/docker-compose.yaml up -d
```

> Поднимет PostgreSQL и Kafka, необходимые для работы всех сервисов.

### 3. Собрать проект

```bash
./gradlew build
```

### 4. Запустить сервисы

Каждый сервис нужно запустить отдельно (в разных терминалах или через IntelliJ IDEA Run Configurations):

```bash
./gradlew :order-service:bootRun
./gradlew :payment-service:bootRun
./gradlew :delivery-service:bootRun
```

По умолчанию сервисы слушают на портах:

| Сервис            | Порт |
|-------------------|------|
| order-service     | 8080 |
| payment-service   | 8081 |
| delivery-service  | 8082 |

### 5. Проверить работу

Swagger UI доступен (если подключён springdoc-openapi) по адресу:
http://localhost:8080/swagger-ui.html

Пример создания заказа:

```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "items": [
      { "productId": 1, "quantity": 2 }
    ]
  }'
```

Пример оплаты заказа:

```bash
curl -X POST http://localhost:8080/orders/{id}/pay \
  -H "Content-Type: application/json" \
  -d '{
    "paymentMethod": "CARD"
  }'
```

После успешной оплаты в логах `delivery-service` появится сообщение об обработке события `OrderPaidEvent`, а заказ в `order-service` со временем перейдёт в статус `DELIVERY_ASSIGNED`.

## Структура базы данных

Каждый сервис использует свою независимую базу PostgreSQL (Database per Service):

- `orders` — для `order-service`
- `payments` — для `payment-service`
- `deliveries` — для `delivery-service`

## Статус проекта

Проект в активной разработке, используется как портфолио-проект для подготовки к Java/Spring собеседованиям.

## Автор

[Sanzhar](https://github.com/sanzhidev)