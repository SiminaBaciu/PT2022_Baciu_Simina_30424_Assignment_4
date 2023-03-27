package business.users;

import java.io.Serializable;
import java.util.Arrays;

public abstract class User implements Serializable {
    private String username;
    private char[] password;
    private Roles role;

    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password=" + Arrays.toString(password) +
                ", role=" + role +
                '}';
    }
}
