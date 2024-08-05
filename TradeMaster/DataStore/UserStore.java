package TradeMaster.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserStore {
    public static class User{
        public String username;
        public int balance;

        User(String name){
            this.username =  name;
        }

        public User(String name, int val){
            this.username = name;
            this.balance = val;
        }
    }

    public static List<User> users = new ArrayList<>();

    public static boolean doesUserExist(String username) {
        return users.stream().anyMatch(user -> user.username.equals(username));
    }

    public static User getUser(String username) {
        Optional<User> userOpt = users.stream()
                .filter(user -> user.username.equals(username))
                .findFirst();
        return userOpt.orElse(null);
    }

}
