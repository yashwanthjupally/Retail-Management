# Retail Management System

A backend system for managing retail operations such as products, inventory, and orders, built with Java, Spring Boot, MySQL, and MongoDB (containerized with Docker).

<img width="1581" height="734" alt="Screenshot 2025-07-01 233020" src="https://github.com/user-attachments/assets/4acd0a6a-7959-445c-8d9d-97273c84038f" />


## Features

- **Product Management**: Add, update, and remove products.
- **Inventory Tracking**: Manage stock levels across stores.
- **Order Processing**: Handle order creation and updates.
- **Search**: Filter by price, category, and store.
- **Scalability**: MongoDB is containerized for easy scaling.

## Technologies

- **Backend**: Spring Boot (Java 11)
- **Databases**: 
  - MySQL for structured data (Products, Inventory).
  - MongoDB for unstructured data (Orders, Logs).
- **Tools**: Docker (for MongoDB), Maven, Git.

## Prerequisites

- Java JDK 11+
- Maven 3.6+
- MySQL 8.0+
- MongoDB 4.4+ or Docker

## Installation

1. Clone the repo:
   ```bash
   git clone https://github.com/yashwanthjupally/Retail-Management.git
   cd Retail-Management
2. Install dependencies:
   ```bash
   mvn clean install

3. Set up MySQL & Mongo:
      Create a database retail_db.
      Update application.properties with your MySQL credentials.
      Set up MongoDB

 4. Configuration
      In `src/main/resources/application.properties`, configure the following:
      
      ```properties
      # MySQL Configuration
      spring.datasource.url=jdbc:mysql://localhost:3306/retail_db?useSSL=false&serverTimezone=UTC
      spring.datasource.username=root
      spring.datasource.password=your_password
      
      # MongoDB Configuration
      spring.data.mongodb.uri=mongodb://localhost:27017/retail_orders
      
      # Server Configuration
      server.port=8080

    
6. Running the Application

      Run with Maven:
      mvn spring-boot:run
      The app will be available at http://localhost:8080

   API Endpoints

      Base URL: /api/v1

      Products:

         GET /products: Retrieve all products (supports search filters like name, priceMin, priceMax).

      Inventory:

         GET /inventory: Get stock levels for each product.

      Orders:

         POST /orders: Create a new order.
    
