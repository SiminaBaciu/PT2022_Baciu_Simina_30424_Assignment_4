package business.product;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeItem implements MenuItem, Serializable {
    public ArrayList<BaseProduct> productList;
    private String title;
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;
    private int timesOrdered;

    public CompositeItem(String title, ArrayList<BaseProduct> productList) {
        this.title = title;
        this.productList = productList;
        float rating = 0;
        for(BaseProduct b : productList) {
            this.calories += b.getCalories();
            rating += b.getRating();
            this.protein += b.getProtein();
            this.fat += b.getFat();
            this.sodium += b.getSodium();
            this.price += b.computePrice();
        }
        this.rating = rating / productList.size();
        timesOrdered = 0;
    }

    @Override
    public int computePrice() {
        int price = 0;
        for(BaseProduct p : this.productList) {
            price += p.computePrice();
        }
        return price;
    }

    public String toString() {
        return title + ": " + rating + ", " + calories + ", " + protein + ", " + fat + ", " + sodium + ", " + price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<BaseProduct> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<BaseProduct> productList) {
        this.productList = productList;
    }

    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }

    public void incrementTimesOrdered() {
        timesOrdered++;
    }
}
