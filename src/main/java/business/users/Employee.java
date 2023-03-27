package business.users;

import java.io.Serializable;

public class Employee extends User implements Serializable {

    public Employee(String username, char[] password) {
        super(username, password);
        this.setRole(Roles.EMPLOYEE);
    }
}
