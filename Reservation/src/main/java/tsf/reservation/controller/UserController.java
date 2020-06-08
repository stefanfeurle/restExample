package tsf.reservation.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import tsf.reservation.database.models.User;
import tsf.reservation.view.UserView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class UserController {
    private ArrayList<User> users = new ArrayList<>();
    private UserView userView = new UserView();

    private static UserController instance = null;
    private UserController() {
        users.add(new User("Louis", "Tries", "louis.tries@gmail.com", "louis",
                "+43 676 7039385", "2020-04-11", "13:15:50", null, false));
        users.add(new User("Veridianna", "Mallmann", "veri.mallmann@gmail.com", "veri",
                "+43 676 2734344", "2020-04-16", "16:07:21", null, false));
    }

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    /*public UserController(UserView userView) {
        this.userView = userView;
        users.add(new User("Louis", "Tries", "louis.tries@gmail.com", "louis",
                "+43 676 7039385", "2020-04-11", "13:15:50", null, false));
        users.add(new User("Veridianna", "Mallmann", "veri.mallmann@gmail.com", "veri",
                "+43 676 2734344", "2020-04-16", "16:07:21", null, false));
    }*/

    public String loginUser(User loginUser) {
        for (User user : users) {
            if (user.getEmail().equals(loginUser.getEmail()) && user.getPassword().equals(loginUser.getPassword())) {
                user.setLogin(true);
                userView.printOutput("login");
                user.setToken(generateRandomToken());
                loginUser = user;
                break;
            }
        }
        userView.printOutput(loginUser.getToken() == null ? "null token" : loginUser.getEmail());

        String myReturn = null;

        if(loginUser.getToken() == null){
            myReturn = ("{\"token\": null }");
        } else {
            myReturn = ("{\"token\":\"" + loginUser.getToken() + "\"}");
        }
        return myReturn;
    }

    public String generateRandomToken() {
        Random random = new Random();
        int length = random.nextInt(8) +20;
        char[] values = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w',
                'x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W',
                'X','Y','Z','0','1','2','3','4','5','6','7','8','9'};

        String token = "";

        for (int i=0;i<length;i++) {
            int randomNumber = random.nextInt(values.length);
                token += values[randomNumber];
        }
        userView.printOutput(token);
        return token;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(String email) {
        User getUser = null;
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getEmail() != null) {
               getUser = user;
            }
        }
        userView.printOutput(getUser == null ? "null user (E-Mail)" : "login " +getUser.getEmail());
        return getUser;
    }

    public User checkToken(String token) {
        User checkUser = null;
        for (User user1 : users) {
            if (user1.getToken() != null) {
                if (user1.getToken().equals(token)) {
                    checkUser = user1;
                    break;
                }
            }
        }
        return checkUser;
    }

    public String logoutUser(User user, String token) {
        User logoutUser = checkToken(token);
        String logout = "logout failed";
        if (logoutUser != null) {
            logoutUser.setToken(null);
            logoutUser.setLogin(user.isLogin());
            userView.printOutput("logout " + logoutUser.getEmail());
            logout = "logout";
        }
        return logout;
    }
}
