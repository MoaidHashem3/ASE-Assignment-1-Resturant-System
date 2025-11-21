# 🍽️ Restaurant Ordering & Billing System
**Advanced Software Engineering – Assignment 1 (2025)**  
**Authors:**
- **Eyad (20230074)**
- **Moaid (20210625)**  
  **TA:** Marwa Ahmed

This project is a simple restaurant ordering system built in Java.  
It uses several basic Object-Oriented Design Patterns such as:
- Abstract Factory
- Decorator
- Observer
- Composite
- Strategy
- Facade

These patterns help organize the code, make the menu dynamic, allow add-ons, apply discounts, process payments, and generate a bill.

The system lets the user:
- View the menu
- Create an order
- Add items by ID
- Add toppings
- Apply discounts automatically
- Choose a payment method
- Receive a final bill

Everything is handled cleanly through a single **RestaurantFacade**.

---


---

## ▶️ How to Run the Project

### 1. **Compile the project**
From the project root directory run:

javac -d out src/com/restaurant/**/*.java

This compiles all `.java` files into the `out` folder.

---

### 2. **Run the main program**
java -cp out com.restaurant.ui.Main

---

## ✔️ What You Will See

The program will:
- Show the restaurant menu
- Ask you to create an order
- Let you add items by ID
- Let you add toppings
- Automatically apply all discount strategies
- Ask for payment method
- Show a full invoice (subtotal, discounts, tax, total)

---
# 📘 Order Example Bill Output
1- Create a new order (Option 1)

2- Add the following items using thier IDs(Option 3):
1) Veggie Pizza (v1)
2) Veggie Burger (v2)
3) Pepperoni Pizza (n1)

3- View Current Order Items (Option 5)

Output:

--- Items in Order ---
1) Veggie Pizza (80.00)
2) Veggie Burger (70.00)
3) Pepperoni Pizza (95.00)
   Subtotal: 245.00

4- Checkout (Option 6)

Output:

===== FINAL INVOICE =====

Bill for Order 1
Subtotal: 245.00
Discounts:
- Pizza 10% Off: -17.50
- Combo Discount: -5.00
- No Discount: -0.00
- 
  Tax: +31.15
- 
  Total: 253.65

PaymentResult {success=true, message='Paid in cash', method='CASH'}


