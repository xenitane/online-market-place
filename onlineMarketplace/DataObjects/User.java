package onlineMarketplace.DataObjects;

import onlineMarketplace.Exceptions.InvalidDataException;
import onlineMarketplace.Utility;

public class User {
    String userId;
    String name;
    String email;
    String password;

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public User(String userId, String name, String email, String password) throws InvalidDataException {
        if (Utility.isNullOrEmpty(userId) || Utility.isNullOrEmpty(email) || Utility.isNullOrEmpty(password)
                || Utility.isNullOrEmpty(name))
            throw new InvalidDataException();
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
