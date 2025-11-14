
---

# 🍕 **THE PIZZA SOLO PROJECT**

### *Year Up United · Software Development Track · Capstone #2 (2025)*

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/OOP-SOLID-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/CLI%20App-Console-green?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Year%20Up%20United-2025-purple?style=for-the-badge"/>
</p>

<p align="center">
  A modular Java application for building pizzas, choosing signature recipes, adding drinks & sides, calculating totals, and generating receipts.  
  Built with clean Object-Oriented Programming and a layered architecture.
</p>

---

# 🌟 **Overview**

This project is a **console-based pizza ordering system** created as part of **Capstone Project #2** for the Year Up United Software Development track.

The app allows users to:

✔ Build custom pizzas
✔ Select signature pizzas
✔ Add drinks and side items
✔ View order summaries
✔ Calculate subtotal, tax, and tip
✔ Save receipts to file

The entire system is built using **pure Java OOP**, without frameworks.

---

# 🧠 **Key Features**

## 🍕 Custom Pizza Builder

* Choose pizza size (S / M / L)
* Add/remove toppings
* Auto-calculated pricing

## ⭐ Signature Pizzas

Pre-built recipes:

* **Margherita**
* **Pepperoni**
* **Veggie**

## 🥤 Drinks & 🍞 Sides

* Simple and clean preset menus
* Easy selection by number

## 📊 Full Order Summary

Includes:

* Pizzas with toppings
* Drinks
* Side items
* Subtotal
* Tax (7%)
* Tip
* Final total

## 💾 Save Receipt

* `.txt` file
* Stored in `/receipts`
* Timestamped filename
* Clean, readable formatting

---

# 🧩 **OOP & Architecture Breakdown**

## 🧱 Encapsulation

Models keep data private with getters/setters.

## 🧠 Abstraction

Service classes contain all logic:

* PizzaService
* OrderService
* DrinkService
* SideItemService

## 🔁 Polymorphism via Interfaces

`ReceiptWriter` implemented by:

* `OrderPrinter` (console writer)
* `OrderFileWriter` (file writer)

## 🏛 Layered Architecture

```
UI Layer       -> Menu, ConsoleUI
Service Layer  -> Business logic
Model Layer    -> Pizza, Topping, Order, etc.
IO Layer       -> Receipt writing (console/file)
Utils          -> Input validation helpers
```

---

# 📁 **Project Structure**

```
src/
└── main/java/com/pizza/
    ├── model/
    │     Pizza.java
    │     Topping.java
    │     Drink.java
    │     SideItem.java
    │     Order.java
    │     └── signature/
    │           Margherita.java
    │           Pepperoni.java
    │           Veggie.java
    │
    ├── service/
    │     PizzaService.java
    │     OrderService.java
    │     DrinkService.java
    │     SideItemService.java
    │     ToppingService.java
    │
    ├── ui/
    │     Menu.java
    │     ConsoleUI.java
    │
    ├── io/
    │     OrderPrinter.java
    │     OrderFileWriter.java
    │
    └── util/
          InputValidator.java

/receipts
```

---

# 🧾 **Receipt Example**

```
======== ORDER RECEIPT ========

Pizzas:
 - Large Pepperoni
    + Cheese
    + Pepperoni

Drinks:
 - Coke

Sides:
 - Garlic Bread

------------------------------
Subtotal: $18.50
Tax (7%): $1.29
Tip: $3.00
TOTAL: $22.79
```

---

# 🏃 **How to Run**

```
1. Open the project in IntelliJ IDEA  
2. Run: ConsoleUI.main()  
3. Follow the menu prompts  
```

---

# 📚 **Lessons Learned**

* Applying real OOP concepts in a full project
* Structuring a console app using layered architecture
* Handling user input safely
* Writing to files with Java IO
* Building a flexible and extendable system

---

