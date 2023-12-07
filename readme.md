# Online Market Place

> This is the implementation for an online marketplace cli application.

## Requirements

-   Java 17 LTS

## Functions:

-   User Registration
    Requires:
    -   Unique User ID
    -   user name (can contain spaces)
    -   user email
    -   user password
-   Fetch User Data (can be used for login)
    Requires:
    -   User ID
-   Product Listing
    Requires:
    -   Unique Product ID
    -   Product Name (can contain spaces)
    -   Product Description (can contain spaces)
    -   Product Price
    -   Product Quantity in Stock
-   Product Lookup
    Requires:
    -   Product ID
-   Add product(s) to user cart
    Requires:
    -   User ID
    -   Product ID
    -   Quantity
-   View User Cart
    Requires:
    -   User ID
-   Checkout User Cart
    Requires:
    -   User ID
-   View User Order History
    Requires:
    -   User ID

> Note: After entering any value required press "enter"/"return" key.

## Building and Running

1. After Extraction of the zip Navigate to the current folder on the terminal.
2. To Compile
    ```bash
    javac -d out onlineMarketplace/Solution.java
    ```
3. To Run
    ```bash
    java -classpath out onlineMarketplace.Solution
    ```
4. Enter Approprite input as per requirements
