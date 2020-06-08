package tsf.reservation.controller;

import tsf.reservation.database.models.Restaurant;
import tsf.reservation.view.RestaurantView;

import java.util.ArrayList;

public class RestaurantController {
    private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
    private RestaurantView restaurantView;

    public RestaurantController(RestaurantView restaurantView) {
        this.restaurantView = restaurantView;
        restaurants.add(new Restaurant("ATU68423028", "Restaurant", "Casa Antica",
                "casa@antica", "www.casa_antica.at", "+43 676 85201709" , 6840,
                "Götzis", "Flughafenallee", 15));
        restaurants.add(new Restaurant("ATU10069745", "Pizzeria", "La Trattoria il Castello",
                "laTrattoria@ilCastello", "https://la-trattoria-il-castello", "+43 5522 80017",
                6812, "Meiningen", "Germanygasse", 32));
    }


    public ArrayList<Restaurant> getRestaurants() {
        restaurantView.printOutput(restaurants== null ?"null restaurants" : "all restaurants");
        return restaurants;
    }

    public Restaurant getSingleRestaurant(String companyBookNumber) {
        Restaurant singleRestaurant = null;
        for (Restaurant restaurant : restaurants) {
            if (companyBookNumber.equalsIgnoreCase(restaurant.getCompanyBookNumber())) {
                singleRestaurant = restaurant;
            }
        }
        restaurantView.printOutput(singleRestaurant == null ? "null restaurant" : "single restaurant " + singleRestaurant.getCompanyBookNumber());
        return singleRestaurant;
    }
}
