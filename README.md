# Billing System

Billing System will generate the bill/receipt for the order.

Billing System Components

1. Add item to the order
2. Complete the order
3. Make a payment
4. Generate Bill


## Flow Chart

#Step 1 Ask the customer to enter the item
  - Enter 1 to add item to the order
  - Enter 2 to cancel existing order
  - Enter 3 to complete the order


# Option 1. Add item to the order
 - Enter item name, value and quantity
 - repeat the step 1

# Option 2. Cancel existing order
  - clean all the data (should not have any persistant data)

# Option 3. Complete the order
  - Add the total and show the total amount, total quantity
  - Ask "Do you want to make payment (option 4) or cancel the order (option 2)?"

# Option 4. Make the payment
  - Enter the amount
  - deduct the amount from the order total
  - if total becomes = 0, then print bill (option 5)
  - keep on loop until total becomes 0
  - once payment is done and total becomes 0, print "Thank you for coming" and clear the data

$ Option 5 Print Receipt
 - Print the orderId, items with quantity and price
 - and the end of the receipt print the total amount paid
 - once payment is done and total becomes 0, print "Thank you for coming" and clear the data


