package business.users;

import java.io.Serializable;

public class Client extends User implements Serializable {
    private int id;
    private int timesOrdered;

    public Client(int id, String username, char[] password) {
        super(username, password);
        this.id = id;
        this.setRole(Roles.CLIENT);
        timesOrdered = 0;
    }

    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }

    public void incrementTimesOrdered() {
        this.timesOrdered++;
    }
}

