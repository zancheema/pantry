# Pantry Management Application
## Overview
- [Problem Statement](#problem-statement)
- [Solution Architecture](#solution-architecture)
- [Technologies Stack](#technologies-tack)
- [Implementation Modules](#implementation-modules)
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
Every week he goes to Costco to buy his groceries. The items are at wholesale
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

## Solution Architecture
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
