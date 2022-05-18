package org.billingsystem.com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    String orderId;
    List<Item> items;
    double orderTotal;
    double payment;

    public Order()
    {
        orderId = UUID.randomUUID().toString().replaceAll("-", "");
        items = new ArrayList<>();
    }

    public String getOrderId() {return orderId;}

    public void addItemToOrder(Item newItem)
    {
        if (newItem == null) return;
        double currTotal = newItem.getPrice();
        int currQuantity = newItem.getQuantity();

        if (items != null && items.size() > 0 && items.contains(newItem))
        {
            Item curr = getCurrItem(newItem);
            currTotal += curr.getPrice();
            currQuantity += curr.getQuantity();

            items.remove(curr);

        }

        items.add(new Item(newItem.getName(), currQuantity, currTotal));
        this.orderTotal = getOrderTotal();

    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment += payment;
    }

    public Item getCurrItem(Item newItem)
    {
        Item curr = newItem;

        for (Item item: items)
        {
            if (item.getName().equals(newItem.getName()))
                return item;
        }

        return curr;
    }

    public void setOrderTotal(double total) {this.orderTotal = total;}
    public double getOrderTotal() {return this.orderTotal;}

    public double calculateOrderTotal()
    {
        double total = 0.0;

        for (Item item : items)
            total += item.getPrice();

        this.orderTotal = total;
        return total;
    }



    public String generateReceipt()
    {
        StringBuilder receipt = new StringBuilder("Order Number: " +orderId);
        receipt.append("\n");
        for (Item item: items)
        {
            receipt.append(item.getName() + " " + item.getQuantity() + "  " + item.getPrice());
            receipt.append("\n");
        }

        receipt.append("Final OrderTotal:   " +getPayment());
        return receipt.toString();
    }
}
