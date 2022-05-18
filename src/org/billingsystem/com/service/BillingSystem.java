package org.billingsystem.com.service;


import org.billingsystem.com.model.Item;
import org.billingsystem.com.model.Order;

import java.util.*;

class BillingSystemService {

    Map<String, Order> orders;
    static Order order;

    BillingSystemService()
    {
        orders = new HashMap<String, Order>();
    }

    public void showBillingSystemOptions()
    {
        System.out.println("Enter 4 to previous orders by orderId");
        System.out.println("Enter 5 to show all previous orders");
        System.out.println("Enter 0 to show store options");
        System.out.println("Enter -1 to exit from Billing System");
    }

    public void showStoreOptions()
    {
        System.out.println("Enter 1 to add item to the order");
        System.out.println("Enter 2 to cancel existing order");
        System.out.println("Enter 3 to complete the order");
        System.out.println("Enter -1 to exit from Billing System");
    }

    public void getOrderById(String orderId)
    {
        if (orders == null || orders.size() == 0)
        {
            System.out.println("There are no previous orders to show");
            return;
        }

        for (Map.Entry<String, Order> curr: orders.entrySet())
        {
            if (curr.getKey().equals(orderId))
            {
                System.out.println("Printing the order information of " +orderId);
                System.out.println(curr.getValue().generateReceipt());
                return;
            }
        }
    }

    public void getOrders()
    {
        if (orders == null || orders.size() == 0)
        {
            System.out.println("There are no previous orders to show");
            return;
        }
        for (Map.Entry<String, Order> curr: orders.entrySet())
        {
            System.out.println();
            System.out.println(curr.getValue().generateReceipt());

        }

    }

    public void makePayment(Scanner scanner)
    {
        double currTotal = order.getOrderTotal();

        while (currTotal > 0)
        {
            System.out.println("Enter the amount equal or less than " +currTotal);
            double amount = scanner.nextDouble();
            if (amount > currTotal)
            {
                order.setOrderTotal(0.0);
                System.out.println("Your remaining payout " +(amount - currTotal));
                order.setPayment(currTotal);
                currTotal = 0.0;
            }
            else
            {
                order.setOrderTotal(Math.abs(amount - currTotal));
                currTotal = Math.abs(amount - currTotal);
                order.setPayment(amount);
            }

        }

        System.out.println("Thank you for purchase!!");
    }

    public void addOrderToDatabase()
    {
        if (order.getOrderTotal() > 0)
        {
            System.out.println("Please make the payment to complete the order");
            return;
        }

        orders.put(order.getOrderId(), order);
    }

    public void completeOrder(BillingSystemService billingSystem, Scanner scanner)
    {
        billingSystem.makePayment(scanner);
        billingSystem.addOrderToDatabase();
        System.out.println(order.generateReceipt());
    }

    public static void main(String[] args) throws Exception {
        BillingSystemService billingSystem = new BillingSystemService();
        order = new Order();
        Scanner scanner = new Scanner(System.in);
        billingSystem.showBillingSystemOptions();
        while (true)
        {

            int input = scanner.nextInt();

            if (input == 0)
            {
               billingSystem.showStoreOptions();
            }
            else if (input == 1)
            {
                System.out.println("Enter Item name ");
                String name = scanner.next();

                System.out.println("Enter the quantity ");
                int quantity = scanner.nextInt();

                System.out.println("Enter the price of each item");
                double price = scanner.nextDouble();

                order.addItemToOrder(new Item(name, quantity, quantity*price));
                order.calculateOrderTotal();
                billingSystem.showStoreOptions();
            }
            else if (input == 2)
            {
                System.out.println("As per request we are cancelling the current order");
                order = new Order();
                billingSystem.showBillingSystemOptions();
            }
            else if (input == 3)
            {
                System.out.println("As per request we are completing the current order");
                billingSystem.completeOrder(billingSystem, scanner);


                order = new Order();
                billingSystem.showBillingSystemOptions();
            }
            else if (input == 4)
            {
                System.out.println("Enter the order Id");
                String orderId = scanner.next();
                billingSystem.getOrderById(orderId);
                billingSystem.showBillingSystemOptions();
            }
            else if (input == 5)
            {
                billingSystem.getOrders();
                billingSystem.showBillingSystemOptions();
            }
            else
                break;

        }
    }
}
