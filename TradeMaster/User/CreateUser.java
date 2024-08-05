package TradeMaster.User;

import TradeMaster.DataStore.TransactionStore;
import TradeMaster.DataStore.UserStore;
import TradeMaster.Transactions.BuyTransaction;
import TradeMaster.Transactions.SellTransaction;

import java.util.List;

public class CreateUser {
    public static UserStore.User user;
    public CreateUser(String name, int balance){
        user = new UserStore.User(name,balance);
        // search UserStore.users for current username , if yes then suggest change username
        boolean success = UserStore.doesUserExist(user.username);

        if(success){
            System.out.println("Username already taken | Change username");
            return;
        }

        UserStore.users.add(user);
    }

    public static void buyStock(String symbol,int quantity){
        BuyTransaction.buy(user.username,symbol,quantity);
    }

    public static void sellStock(String symbol,int quantity){
        SellTransaction.sell(user.username, symbol,quantity);
    }

    public static int getBalance(){
        UserStore.User myUser = UserStore.getUser(user.username);
        if(myUser!=null){
            return myUser.balance;
        }
        return 0;
    }

    public static List<String> getHistory(){
        return TransactionStore.history.get(user.username);
    }
}
