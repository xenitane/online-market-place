package onlineMarketplace.DataObjects;

import onlineMarketplace.Exceptions.InvalidDataException;
import onlineMarketplace.Utility;

public class Product {
    String productId;
    String name;
    String description;
    double price;
    int quantityInStock;

    public Product(String productId, String name, String description, double price, int quantityInStock) throws InvalidDataException {
        if (Utility.isNullOrEmpty(productId) || Utility.isNullOrEmpty(name) || Utility.isNullOrEmpty(price) || Utility.isNullOrEmpty(quantityInStock) || Utility.isNullOrEmpty(description))
            throw new InvalidDataException();
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

}
