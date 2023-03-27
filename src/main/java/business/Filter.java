package business;

import business.product.MenuItem;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;


public class Filter {

    public static String[] searchMenuItems(String criteria, String conditions, ArrayList<MenuItem> menuItems) {
        ArrayList<MenuItem> searchResults = new ArrayList<MenuItem>();

        if (criteria.equals("Name")) {
            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                    .filter(m -> m.getTitle().toLowerCase(Locale.ROOT).contains(conditions.toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
        } else {
            String[] temp = conditions.split(" ");
            char operand = temp[0].charAt(0);
            int number = Integer.parseInt(temp[1]);
            switch (criteria) {
                case "Rating":
                    switch (operand) {
                        case '<':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getRating() < number)
                                    .collect(Collectors.toList());
                            break;
                        case '=':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getRating() == number)
                                    .collect(Collectors.toList());
                            break;
                        case '>':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getRating() > number)
                                    .collect(Collectors.toList());
                            break;
                    }
                    break;
                case "Calories":
                    switch (operand) {
                        case '<':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getCalories() < number)
                                    .collect(Collectors.toList());
                            break;
                        case '=':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getCalories() == number)
                                    .collect(Collectors.toList());
                            break;
                        case '>':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getCalories() > number)
                                    .collect(Collectors.toList());
                            break;
                    }
                    break;
                case "Protein":
                    switch (operand) {
                        case '<':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getProtein() < number)
                                    .collect(Collectors.toList());
                            break;
                        case '=':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getProtein() == number)
                                    .collect(Collectors.toList());
                            break;
                        case '>':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getProtein() > number)
                                    .collect(Collectors.toList());
                            break;
                    }
                    break;
                case "Fat":
                    switch (operand) {
                        case '<':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getFat() < number)
                                    .collect(Collectors.toList());
                            break;
                        case '=':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getFat() == number)
                                    .collect(Collectors.toList());
                            break;
                        case '>':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getFat() > number)
                                    .collect(Collectors.toList());
                            break;
                    }
                    break;
                case "Sodium":
                    switch (operand) {
                        case '<':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getSodium() < number)
                                    .collect(Collectors.toList());
                            break;
                        case '=':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getSodium() == number)
                                    .collect(Collectors.toList());
                            break;
                        case '>':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.getSodium() > number)
                                    .collect(Collectors.toList());
                            break;
                    }
                    break;
                case "Price":
                    switch (operand) {
                        case '<':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.computePrice() < number)
                                    .collect(Collectors.toList());
                            break;
                        case '=':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.computePrice() == number)
                                    .collect(Collectors.toList());
                            break;
                        case '>':
                            searchResults = (ArrayList<MenuItem>) menuItems.stream()
                                    .filter(m -> m.computePrice() > number)
                                    .collect(Collectors.toList());
                            break;
                    }
                    break;
            }
        }
        return DeliveryService.getMenuItemsString(searchResults);
    }
}
