package tsf.reservation.database.models;

public class Reservation {
    int numberOfPeople;
    String companyBookNumberRestaurant;
    String emailUser;
    String date;
    String time;
    int id;

    public Reservation(int numberOfPeople, String companyBookNumberRestaurant, String emailUser, String date, String time, int id) {
        this.numberOfPeople = numberOfPeople;
        this.companyBookNumberRestaurant = companyBookNumberRestaurant;
        this.emailUser = emailUser;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    public Reservation() {
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public String getCompanyBookNumberRestaurant() {
        return companyBookNumberRestaurant;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
