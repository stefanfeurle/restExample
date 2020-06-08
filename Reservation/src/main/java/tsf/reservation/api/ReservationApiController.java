package tsf.reservation.api;

import org.springframework.web.bind.annotation.*;
import tsf.reservation.controller.ReservationController;
import tsf.reservation.controller.RestaurantController;
import tsf.reservation.controller.UserController;
import tsf.reservation.database.models.Reservation;
import tsf.reservation.database.models.User;
import tsf.reservation.view.ReservationView;
import tsf.reservation.view.RestaurantView;
import tsf.reservation.view.UserView;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class ReservationApiController {
    private RestaurantView restaurantView = new RestaurantView();
    private RestaurantController restaurantController = new RestaurantController(restaurantView);
    private ReservationView reservationView = new ReservationView();
    private ReservationController reservationController = new ReservationController(reservationView, restaurantController);


    @GetMapping("/reservation/reservation/{token}")
    public User checkUser(@PathVariable String token) {
        User user = reservationController.checkUser(token);
        return user;
    }

    @PostMapping("/reservation/reservation/{token}")
    public Reservation makeReservation(@RequestBody Reservation reservation, @PathVariable String token) {
        return reservationController.makeTableReservation(reservation, token);
    }

    @GetMapping("/reservation/reservation/all/{token}")
    public ArrayList<Reservation> getAllReservations(@PathVariable String token) {
        return reservationController.getAllReservations(token);
    }

    @GetMapping("/reservation/reservation/{id}/{token}")
    public Reservation getOneReservation(@PathVariable("id") int id, @PathVariable("token") String token) {
        return reservationController.getOneReservation(id, token);
    }

    @DeleteMapping("/reservation/reservation/{id}/{token}")
    public String deleteOneReservation(@PathVariable("id") int id, @PathVariable("token") String token) {
        return reservationController.deleteOneReservation(id, token);
    }
}
