package onlineMarketplace;

import onlineMarketplace.DataObjects.CartItem;
import onlineMarketplace.DataObjects.Order;
import onlineMarketplace.DataObjects.Product;
import onlineMarketplace.DataObjects.User;
import onlineMarketplace.Exceptions.*;
import onlineMarketplace.Interfaces.MarketPlace;
import onlineMarketplace.Service.MarketPlaceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        MarketPlace marketplace = new MarketPlaceImpl();
        Scanner sc = new Scanner(System.in);
        int operation = 0;
        do {
            System.out.print("""
                    ================================Online Marketplace================================
                    Select an operation you want to perform:
                    1: Add User
                    2: Get User Info
                    3: Add Product
                    4: View Product
                    5: Add Product to Cart
                    6: Get User Cart Info
                    7: Checkout
                    8: Get Order History of User
                    0: Exit
                    Enter your choice:\s""");
            try {
                operation = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                operation = 9;
            }

            switch (operation) {
                case 0:
                    System.out.println("Exiting");
                    break;
                case 1:// get user info and add user
                    try {
                        System.out.println("Enter User Details for Registration");

                        System.out.print("User ID: ");
                        String userId = sc.next();
                        sc.nextLine();

                        System.out.print("User Name: ");
                        String name = sc.nextLine();

                        System.out.print("User Email: ");
                        String email = sc.next();

                        System.out.print("User Password: ");
                        String password = sc.next();

                        User user = new User(userId, name, email, password);
                        marketplace.createUser(user);
                        System.out.print("User Registered to Marketplace");
                    } catch (InvalidDataException e) {
                        System.out.println("!!!!Some details are entered incorrectly, Operation Aborted!!!!");
                    } catch (UserAlreadyExistException e) {
                        System.out.println("!!!!User with this ID already exists, Operation Aborted!!!!");
                    }
                    break;
                case 2: // read user id and print user info
                    try {
                        System.out.print("User ID of user to look for: ");
                        String userId = sc.next();
                        User user = marketplace.getUser(userId);
                        System.out.printf("""
                                User Details:
                                User Id: %s
                                User Name: %s
                                User Email: %s
                                %n""", user.getUserId(), user.getName(), user.getEmail());
                    } catch (UserNotFoundException e) {
                        System.out.println("!!!!No User found with this UserID!!!!");
                    }
                    break;
                case 3:// read product info and add product
                    try {
                        System.out.println("Enter Product Details for Listing");
                        System.out.print("Product Id: ");
                        String productId = sc.next();
                        sc.nextLine();
                        System.out.print("Product Name: ");
                        String productName = sc.nextLine();
                        System.out.print("Product Description: ");
                        String productDesc = sc.nextLine();
                        System.out.print("Product Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Product Quantity: ");
                        int qty = sc.nextInt();

                        Product product = new Product(productId, productName, productDesc, price, qty);
                        marketplace.addProduct(product);
                        System.out.println("Product Listed on marketplace Successfully");
                    } catch (InputMismatchException e) {
                        System.out.println("!!!!Some details are entered incorrectly, Operation Aborted!!!!");
                        sc.next();
                    } catch (InvalidDataException e) {
                        System.out.println("!!!!Some details are entered incorrectly, Operation Aborted!!!!");
                    } catch (ProductAlreadyExistException e) {
                        System.out.println("!!!!Product with this ID already exists, Operation Aborted!!!!");
                    }
                    break;
                case 4:// look for product
                    try {
                        System.out.print("Product Id for product to look for: ");
                        String productId = sc.next();
                        Product product = marketplace.getProduct(productId);
                        System.out.printf("""
                                Product Details:
                                Product ID: %s
                                Product Name: %s
                                Product Description: %s
                                Product Price: %f
                                Product Availability in Stock: %d
                                %n""", product.getProductId(), product.getName(), product.getDescription(),
                                product.getPrice(), product.getQuantityInStock());
                    } catch (ProductNotFoundException e) {
                        System.out.println("!!!!No Product found with this ProductID!!!!");
                    }
                    break;
                case 5:// add product to cart
                    try {
                        System.out.println("Enter the following details to add product to user cart");
                        System.out.print("User Id: ");
                        String userId = sc.next();
                        System.out.print("Product Id: ");
                        String productId = sc.next();
                        System.out.print("Quantity Needed: ");
                        int qty = sc.nextInt();
                        marketplace.addToCart(userId, productId, qty);
                        System.out.println("Items have been added to the cart successfully");
                    } catch (InputMismatchException e) {
                        System.out.println("!!!!Some details are entered incorrectly, Operation Aborted!!!!");
                        sc.next();
                    } catch (UserNotFoundException e) {
                        System.out.println("!!!!No User found with this UserID!!!!");
                    } catch (OutOfStoctException e) {
                        System.out.println(
                                "!!!!The Inventory stock does not have this product in the quantity you wish for!!!!");
                    } catch (InvalidDataException e) {
                        System.out.println("!!!!Some details are entered incorrectly, Operation Aborted!!!!");
                    } catch (ProductNotFoundException e) {
                        System.out.println("!!!!No Product found with this ProductID!!!!");
                    }
                    break;
                case 6:// get user cart
                    try {
                        System.out.print("User ID whose cart needs to be viewed: ");
                        String userId = sc.next();
                        Map<String, CartItem> cart = marketplace.getCart(userId);
                        if (cart.isEmpty())
                            System.out.println("The user's cart is empty");
                        else {
                            System.out.println("User Cart:");
                            for (Map.Entry<String, CartItem> item : cart.entrySet())
                                System.out.println("Product ID: " + item.getValue().getProduct().getProductId()
                                        + ", Quantity Needed: " + item.getValue().getQuantity());
                        }
                    } catch (UserNotFoundException e) {
                        System.out.println("!!!!No User found with this UserID!!!!");
                    }
                    break;
                case 7: // checkout
                    try {
                        System.out.print("User ID for checkout: ");
                        String userID = sc.next();
                        double bill = marketplace.checkout(userID);
                        System.out.println("Checkout Successful and the total amount for the order is " + bill);
                    } catch (UserNotFoundException e) {
                        System.out.println("!!!!No User found with this UserID!!!!");
                    } catch (EmptyCartException e) {
                        System.out.println("The User's cart is empty, add some items for checkout");
                    } catch (OutOfStoctException e) {
                        System.out.println(
                                "!!!!The Inventory stock has ran out of some product(s) in the you wish for!!!!");
                    }
                    break;
                case 8:// get order history
                    try {
                        System.out.print("User ID for order history: ");
                        String userID = sc.next();
                        List<Order> userOrders = marketplace.getOrderHistory(userID);
                        if (userOrders.isEmpty())
                            System.out.println("This User has not made any orders yet");
                        else {
                            System.out.println("User's Order History");
                            for (Order order : userOrders)
                                System.out.println("Order ID: " + order.getOrderId() + ", Total Price for order: "
                                        + order.getTotalPrice());
                        }
                    } catch (UserNotFoundException e) {
                        System.out.println("!!!!No User found with this UserID!!!!");
                    }
                    break;
                default:
                    System.out.println("!!!!Incorrect Input Try Again!!!!");
            }
        } while (operation != 0);
        sc.close();
    }
}
