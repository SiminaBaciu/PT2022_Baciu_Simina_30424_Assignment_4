package business.product;

import java.io.Serializable;

public interface MenuItem {
    public int computePrice();
    public String getTitle();
    public float getRating();
    public int getCalories();
    public int getProtein();
    public int getFat();
    public int getSodium();
    public String toString();
    public int getTimesOrdered();
    public void setTimesOrdered(int timesOrdered);
    public void incrementTimesOrdered();
}
