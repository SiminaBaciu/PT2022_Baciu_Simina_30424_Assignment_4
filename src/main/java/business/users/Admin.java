package business.users;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(String username, char[] password) {
        super(username, password);
        this.setRole(Roles.ADMIN);
    }
}
