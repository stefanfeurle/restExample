package tsf.reservation.controller;

import tsf.reservation.database.models.Reservation;
import tsf.reservation.database.models.User;
import tsf.reservation.view.ReservationView;
import tsf.reservation.view.UserView;

import java.util.ArrayList;

public class ReservationController {
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private ArrayList<Reservation> myReservations = new ArrayList<>();

    private ReservationView reservationView;
    private UserView userView;
    private UserController userController = UserController.getInstance();
    private RestaurantController restaurantController;

    private int id = 10002;

    public ReservationController(ReservationView reservationView, RestaurantController restaurantController) {
        this.reservationView = reservationView;
        this.restaurantController = restaurantController;
        reservations.add(new Reservation(3, "ATU10069745",
                "veri.mallmann@gmail.com", "2020-05-27", "21:00", 10000));
        reservations.add(new Reservation(5, "ATU68423028",
                "louis.tries@gmail.com", "2020-05-28", "12:00", 10001));
        reservations.add(new Reservation(2, "ATU68423028",
                "veri.mallmann@gmail.com", "2020-05-28", "19:00", 10002));

    }

    public User checkUser(String token) {
        User user = userController.checkToken(token);
        reservationView.printOutput(user == null ? "null user token" : user.getEmail() + " " + user.getToken());
        return user;
    }

    public Reservation makeTableReservation(Reservation reservation, String token) {
        User user = userController.checkToken(token);
        if(user != null && reservation.getCompanyBookNumberRestaurant() != null && user.isLogin() &&
                reservation.getDate() != null && reservation.getTime() != null) {
            id++;
            reservation.setId(id);
            boolean isMade = reservations.add(reservation);
        } else {
            reservation = null;
        }
        reservationView.printOutput(reservation == null ? "null reservation" : "reservation " + reservation.getId());
        return reservation;
    }

    public ArrayList<Reservation> getAllReservations(String token) {
        User user = userController.checkToken(token);
        myReservations.clear();
        if (user != null && reservations != null) {
            reservations.forEach(reservation -> {
                if (user.getEmail().equals(reservation.getEmailUser())) {
                    myReservations.add(reservation);
                }
            });
        }
        reservationView.printOutput(myReservations == null ? "null myReservations" : "all myReservations");
        return myReservations;
    }

    public Reservation getOneReservation(int id, String token) {
        User user = userController.checkToken(token);
        Reservation myReservation = searchReservation(user, id);
        reservationView.printOutput(myReservation == null ? "null myReservation" : "myReservation " + myReservation.getId());
        return myReservation;
    }

    public String deleteOneReservation(int id, String token) {
        User user = userController.checkToken(token);
        Reservation deleteReservation = searchReservation(user, id);
        String delete = "delete failed";
        if (deleteReservation != null) {
            reservations.remove(deleteReservation);
            delete = "delete successful";
        }
        return delete;
    }

    private Reservation searchReservation(User user, int id) {
        Reservation searchedReservation = null;
        if (user != null && reservations != null) {
            for (Reservation reservation : reservations) {
                if (user.getEmail().equals(reservation.getEmailUser()) && id == reservation.getId()) {
                    searchedReservation = reservation;
                }
            }
        }
        return searchedReservation;
    }
}
