package onlineMarketplace.Service;

import onlineMarketplace.DataObjects.CartItem;
import onlineMarketplace.DataObjects.Order;
import onlineMarketplace.DataObjects.Product;
import onlineMarketplace.DataObjects.User;
import onlineMarketplace.Exceptions.*;
import onlineMarketplace.Interfaces.MarketPlace;

import java.util.*;


public class MarketPlaceImpl implements MarketPlace {
    Map<String, User> users;
    Map<String, Product> products;
    Map<String, Map<String, CartItem>> carts;
    Map<String, List<Order>> orders;

    public MarketPlaceImpl() {
        users = new HashMap<>();
        products = new HashMap<>();
        carts = new HashMap<>();
        orders = new HashMap<>();
    }

    // user management

    // adding user to the marketplace
    public void createUser(User user) throws UserAlreadyExistException {
        if (users.containsKey(user.getUserId()))
            throw new UserAlreadyExistException();

        users.put(user.getUserId(), user);
    }

    // getting user info
    public User getUser(String userId) throws UserNotFoundException {
        User user = users.get(userId);
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }

    // Product Management

    // adding product to the marketplace
    public void addProduct(Product product) throws ProductAlreadyExistException {
        if (products.containsKey(product.getProductId()))
            throw new ProductAlreadyExistException();
        products.put(product.getProductId(), product);
    }

    // getting product info
    public Product getProduct(String productId) throws ProductNotFoundException {

        Product product = products.get(productId);
        if (product == null)
            throw new ProductNotFoundException();
        return product;
    }

    // Cart Management

    // adding items to cart
    public void addToCart(String userId, String productId, Integer quantity)
            throws UserNotFoundException, ProductNotFoundException, OutOfStoctException, InvalidDataException {

        if (!users.containsKey(userId))
            throw new UserNotFoundException();

        if (quantity < 0)
            throw new InvalidDataException();

        Product product = getProduct(productId);
        if (product == null)
            throw new ProductNotFoundException();

        if (product.getQuantityInStock() < quantity)
            throw new OutOfStoctException();

        if (!carts.containsKey(userId))
            carts.put(userId, new HashMap<>());

        Map<String, CartItem> cart = carts.get(userId);

        CartItem cartItem = new CartItem(product, quantity);
        cart.put(productId, cartItem);
    }

    // getting info of user cart
    public Map<String, CartItem> getCart(String userId) throws UserNotFoundException {
        if (!users.containsKey(userId))
            throw new UserNotFoundException();
        return carts.getOrDefault(userId, Collections.emptyMap());
    }

    // Checkout
    public double checkout(String userId) throws UserNotFoundException, EmptyCartException, OutOfStoctException {
        if (!users.containsKey(userId))
            throw new UserNotFoundException();

        Map<String, CartItem> cart = carts.get(userId);

        if (cart == null || cart.isEmpty())
            throw new EmptyCartException();

        double totalPrice = 0.0;

        for (Map.Entry<String, CartItem> item : cart.entrySet()) {
            if (item.getValue().getQuantity() > item.getValue().getProduct().getQuantityInStock())
                throw new OutOfStoctException();

            totalPrice += item.getValue().getProduct().getPrice() * item.getValue().getQuantity();
            item.getValue().getProduct().setQuantityInStock(item.getValue().getProduct().getQuantityInStock() - item.getValue().getQuantity());
        }

        Order order = new Order(UUID.randomUUID().toString(), userId, cart, totalPrice);

        if (!orders.containsKey(userId))
            orders.put(userId, new ArrayList<>());

        orders.get(userId).add(order);
        carts.remove(userId);

        return totalPrice;
    }

    // Order History
    public List<Order> getOrderHistory(String userId) throws UserNotFoundException {
        if (!users.containsKey(userId))
            throw new UserNotFoundException();

        return orders.getOrDefault(userId, Collections.emptyList());
    }
}