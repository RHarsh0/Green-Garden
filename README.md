# Grow Garden

## Project Logo:
<img src="https://i.ibb.co/K9Z97Dq/project-Logo.png" alt="Alt Text" width="300"/>


## Introduction

The GrowGarden Application is a web-based platform designed to facilitate the purchase and management of a wide variety of plants, seeds, and planters. The application provides customers with an intuitive and user-friendly interface to browse through the available products, place orders, and manage their purchases. Additionally, the admin endpoints empowers administrators to efficiently manage products, customers, and overall operations.

## Key Features:

### For Customers:

1. **Product Listing:** Customers can view a comprehensive list of available plants, seeds, and planters. Each product listing includes images, descriptions, prices, and other relevant details.

2. **Product Categories:** Products are categorized into plants, seeds, and planters, making it easy for customers to navigate and find what they are looking for.

3. **Search and Filters:** Customers can use search functionality and filters to quickly locate specific products based on criteria like plant type, size, flowering season, etc.

4. **Shopping Cart:** Customers can add multiple items to their shopping cart, review the cart, modify quantities, and proceed to checkout.
   
5. **Order Placement:** Customers can place orders for selected items, choose shipping options, and provide delivery details.

6. **User Accounts:** Customers can create accounts to save their preferences, view order history, and track the status of their current orders.

### For Admin:

1. **Plants Management:**
    - Add new plant products with details (name, description, price, image, etc.).
    - Update or delete existing plant products.
    - Manage plant categories and attributes.

2. **Seeds Management:**
    - Add new seed products with details.
    - Edit or remove existing seed products.
    - Organize seeds by categories and attributes.

3. **Planters Management:**
    - Add new planter products with relevant information.
    - Modify or delete existing planter products.
    - Categorize planters based on type, size, material, etc.

4. **Customer Management:**
    - View and manage customer accounts.
    - Monitor customer orders and order history.
    - Handle customer inquiries and support.


## Tech-Stacks:
    - Frontend: HTML, CSS, JavaScript.
    - Backend: Java, Spring-Boot, Spring-Data-JPA
    - Database: MYSQL.
    - User Authentication: JWT (JSON Web Tokens)

## Database
<img src="https://i.ibb.co/JFcZkH4/ER-Diagram.png" alt="Alt Text" width="700"/>

## Team Members

This project is a collaborative effort of the following team members:

- Rutwik Kumbhar
- Ankush.N
- Chandan Singh
- Harsh Rai

## Project Configuration

The project uses the following configuration for the Spring Boot application:

```properties
server.port = 5050
#To configuer your own server port please follow the path provided below and change the server.port value;
#Grow-Garden/src/main/resources/application.properties


spring.datasource.driver=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/growgarden
spring.datasource.username= Your Username
spring.datasource.password= Your Password

spring.jpa.hibernate.ddl-auto=update

```
## Setup

To run the application, follow these steps:

1. Ensure you have Java and MySQL installed on your system.

2. Set up the database with the provided connection details in the `application.properties`.

3. Run the Spring Boot application.

4. Access the application using the specified port (e.g., http://localhost:5050).

## Future implementation:
1. We are also planing to implement Payment Gateway for this application.
2. And also an user friendly user interface.

## Conclusion:
The Online Plant Nursery Application brings together plant enthusiasts and sellers, offering a convenient and seamless platform to buy and sell plants, seeds, and planters. With user-friendly interfaces for customers and administrators, the application simplifies product management, order processing, and customer interactions, creating a flourishing online community for gardening enthusiasts.
    
