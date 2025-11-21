Webshop Backend API
En fullständig REST API för en webshop byggd med Spring Boot, Java 17 och Maven.

Funktioner
Produkthantering - Hämta alla produkter eller specifik produkt

Orderhantering - Skapa och hämta ordrar med validering

Dubbel datakälla - Kombinerar hårdkodade produkter + DummyJSON API

Felhantering - Robust hantering av vanliga fel

Enhetstester - Testad affärslogik med JUnit

Teknologier
Java 17

Spring Boot 3.1.4

Maven - Build tool

JUnit 5 - Testning

RESTful API - JSON kommunikation

Datakällor
Produkter hämtas automatiskt från två källor vid startup:

5 hårdkodade produkter (Elektronik)

30 produkter från DummyJSON API (Skönhet, Parfym, Möbler, Matvaror)


Installation och körning
Förutsättningar
Java 17 eller högre
Maven


Bygg och kör

# Klona repot
git clone https://github.com/IsacBergstedt/webshop-java.git

# Gå till projektmappen
cd webshop-java

# Bygg projektet
mvn clean package

# Kör applikationen
mvn spring-boot:run
Applikationen startar på http://localhost:8080

API Dokumentation
Base URL
http://localhost:8080

Endpoints
Välkomstsida

GET /
Response:

{
  "message": "Welcome to Webshop API",
  "endpoints": "Available endpoints: GET /api/products, GET /api/products/{id}, GET /api/orders, GET /api/orders/{id}, POST /api/orders"
}

Produkter
Hämta alla produkter
GET /api/products
Response:

json
[
  {
    "id": 1,
    "name": "iPhone 14",
    "description": "Latest Apple smartphone",
    "price": 999.99,
    "imageUrl": "https://example.com/iphone14.jpg",
    "stock": 50
  },
  {
    "id": 2,
    "name": "Samsung Galaxy S23",
    "description": "Android flagship phone",
    "price": 899.99,
    "imageUrl": "https://example.com/galaxyS23.jpg",
    "stock": 30
  }
]

Hämta specifik produkt
GET /api/products/{id}
Ordrar


Hämta alla ordrar
GET /api/orders


Hämta specifik order
GET /api/orders/{id}


Skapa order
POST /api/orders
Content-Type: application/json
Request Body:

json
{
  "customerInfo": {
    "name": "Kundens namn",
    "address": "Kundens adress", 
    "email": "kund@example.com"
  },
  "items": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 6,
      "quantity": 1
    }
  ]
}
Response:

json
{
  "orderId": "ORD-1",
  "message": "Order created successfully",
  "totalAmount": 2009.97
}

Testning
Kör enhetstester
mvn test
Testa API med curl
Hämta alla produkter:

curl http://localhost:8080/api/products


Hämta specifik produkt:

curl http://localhost:8080/api/products/1
Skapa order:

curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerInfo": {
      "name": "Test Kund",
      "address": "Testgatan 1",
      "email": "test@example.com"
    },
    "items": [
      {
        "productId": 1,
        "quantity": 1
      }
    ]
  }'

  
Hämta alla ordrar:

curl http://localhost:8080/api/orders
Testa API med Postman/Thunder Client
Importera collection med följande endpoints:

GET / - Välkomstsida

GET /api/products - Alla produkter

GET /api/products/{id} - Specifik produkt

GET /api/orders - Alla ordrar

GET /api/orders/{id} - Specifik order

POST /api/orders - Skapa order

Testa felhantering:

GET /api/products/999 - Produkt finns inte (404)

POST /api/orders med ogiltig data - Valideringsfel (400)

Projektstruktur
src/
├── main/
│   ├── java/com/example/webshop/
│   │   ├── controller/     # REST Controllers
│   │   ├── service/        # Affärslogik
│   │   ├── repository/     # Dataåtkomst
│   │   ├── model/          # Datamodeller
│   │   ├── dto/            # Data Transfer Objects
│   │   └── exception/      # Custom exceptions
│   └── resources/          # Konfiguration
└── test/                   # Enhetstester


Felhantering
API:et returnerar lämpliga HTTP-statuskoder:

200 - Success

400 - Bad Request (ogiltig data)

404 - Not Found (produkt/order finns inte)

500 - Internal Server Error


Exempel felrespons:
{
  "error": "Product not found",
  "message": "Product not found with id: 999"
}
