package tsf.reservation.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tsf.reservation.controller.RestaurantController;
import tsf.reservation.database.models.Restaurant;
import tsf.reservation.view.RestaurantView;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class RestaurantApiController {
    private RestaurantView restaurantView = new RestaurantView();
    private RestaurantController restaurantController = new RestaurantController(restaurantView);

    @GetMapping("/reservation/restaurant")
    public ArrayList<Restaurant> all() {
        return restaurantController.getRestaurants();
    }

    @GetMapping("/reservation/restaurant/{companyBookNumber}")
    public Restaurant singleRestaurant(@PathVariable String companyBookNumber) {
        return restaurantController.getSingleRestaurant(companyBookNumber);
    }
}
