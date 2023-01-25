# Pantry Management Application
## Overview
- [Problem Statement](#problem-statement)
- [Proposed Solution](#proposed-solution)
- [Technologies Stack](#technologies-stack)
- [Architecture](#architecture)
  - [Entity Relationship Diagram](#entity-relationship-diagram)
  - [Use Cases](#use-cases)
    - [User](#user)
      - [Sign Up](#sign-up)
      - [Login](#login)
    - [Product](#product)
      - [Add Product](#add-product)
      - [Use Product](#use-product)
      - [View Products in Pantry](#view-products-in-pantry)
    
## Problem Statement
<p>
John Doe is a regular guy who lives in his apartment and works a 9-5 day job. 
Every week he goes to Tesco to buy his groceries. The items are at wholesale
but unfortunately, they are sold in packs.
</p>
<p>
John buys olive oil which comes in pack of 3. He uses one of them and thinks he
used it all. Next week, when John goes to Costco again, he buys another pack of 
3. Now, he has 5 bottles of olive oil :(
</p>
<p>
How can John keep track of the products he already has or does not have in the pantry? 
Moreover, how can he do it in a more convenient way? There are inventory management 
applications where you can manage the product manually. But, are they really convenient?
</p>

## Proposed Solution
- A mobile application that saves products just by scanning their barcode.
- No need to search the products manually to know if you need more.
- The app is very interactive. After scanning the product the app automatically searches 
inventory and asks that you have ... number of products, do you want to add more?
- When using a product, just scan the barcode and one instance of item will be deducted
from the inventory.
- Additionally, you can set the quantity of product, when adding or using (removing)
- Each inventory is user specific, so you can have different inventories per user.
Plus, each inventory/pantry is private to each user, that they can access only via 
authentication

## Technologies Stack
- Frontend
  - Framework: React Native
  - Platform: Mobile (IOS + Android)
  - Programming Language: Javascript
- Backend
  - Framework: Spring Boot
  - Platform: Web
  - Programming Language: Java

## Architecture

### Entity Relationship Diagram
![erd.png](https://github.com/zancheema/pantry/blob/main/docs/assets/erd.png)

### Use Cases

#### User

##### Sign Up
![user-signup.png](https://github.com/zancheema/pantry/blob/main/docs/assets/user-signup.png)

1. User sends signup request to <strong>/api/auth/signup</strong> endpoint with username 
and password as JSON encoded payload.
2. In case of successful signup (username not already occupied), <strong>201 Created</strong>
status code is returned. In case of failure, <strong>400 Bad Request</strong> status code is
returned.

##### Login
![user-login.png](https://github.com/zancheema/pantry/blob/main/docs/assets/user-login.png)

1. User sends login request to <strong>/api/auth/token</strong> endpoint with username 
and password as JSON encoded payload.
2. In case of successful login, a Json Web Token (JWT) is returned in response body as 
access token. In case of login failure, <strong>400 Bad Request</strong> status code is 
returned.

#### Product

##### Add Product
![product-add.png](https://github.com/zancheema/pantry/blob/main/docs/assets/product-add.png)

1. Delegate the barcode scanning task to Camera.
2. Camera returns the scanned barcode in Text format.
3. Add name and quantity of the barcode item to be added, and send these details to server,
along with Bearer Token as Authorization Header.
5. Server creates a new item in inventory or adds the quantity to it if it already exists.

##### Use Product
![product-add.png](https://github.com/zancheema/pantry/blob/main/docs/assets/product-use.png)

1. Delegate the barcode scanning task to Camera.
2. Camera returns the scanned barcode in Text format.
3. Enter quantity of the barcode item to be used, and send these details to server, along 
with Bearer Token as Authorization Header.
5. Server deducts the quantity from the item in pantry, and returns the remaining quantity

##### View Products in Pantry
![product-add.png](https://github.com/zancheema/pantry/blob/main/docs/assets/product-products.png)

1. Send GET request to server for <strong>/api/products</strong> endpoint along with Bearer Token 
as Authorization Header.
2. The Server returns response body, a JSON object which contains list of all products stored by 
the user as <strong>products</strong> property.
