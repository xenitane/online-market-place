package onlineMarketplace.Interfaces;

import onlineMarketplace.DataObjects.CartItem;
import onlineMarketplace.DataObjects.Order;
import onlineMarketplace.DataObjects.Product;
import onlineMarketplace.DataObjects.User;
import onlineMarketplace.Exceptions.*;

import java.util.List;
import java.util.Map;

public interface MarketPlace {
    public void createUser(User user) throws UserAlreadyExistException;

    public User getUser(String userId) throws UserNotFoundException;

    public void addProduct(Product product) throws ProductAlreadyExistException;

    public Product getProduct(String productId) throws ProductNotFoundException;

    public void addToCart(String userId, String productId, Integer quantity)
            throws UserNotFoundException, OutOfStoctException, ProductNotFoundException, InvalidDataException;

    public Map<String, CartItem> getCart(String userId) throws UserNotFoundException;

    public double checkout(String userId) throws OutOfStoctException, EmptyCartException, UserNotFoundException;

    public List<Order> getOrderHistory(String userId) throws UserNotFoundException;
}