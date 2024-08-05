package TradeMaster;

import TradeMaster.User.CreateUser;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Username: ");
        String name = sc.nextLine();
        System.out.println("Enter initial balance: ");
        int balance = sc.nextInt();
        sc.nextLine();  // Consume newline left-over

        CreateUser user = new CreateUser(name, balance);

        boolean exit = false;

        while(!exit){
            System.out.println("1: Get Balance");
            System.out.println("2: Buy Stock");
            System.out.println("3: Sell Stock");
            System.out.println("4: Get History");
            System.out.println("5: Get All Stocks");
            System.out.println("6: Exit");
            int input = sc.nextInt();
            sc.nextLine();  // Consume newline left-over

            switch (input){
                case 1:
                    int val = user.getBalance();
                    System.out.println("The current balance is: " + val);
                    break;
                case 2:
                    System.out.println("Enter the symbol of the stock:");
                    String symbol = sc.nextLine();
                    System.out.println("Enter the quantity to buy:");
                    int quantity = sc.nextInt();
                    sc.nextLine();  // Consume newline left-over
                    user.buyStock(symbol, quantity);
                    break;
                case 3:
                    System.out.println("Enter the symbol of the stock:");
                    String sym = sc.nextLine();
                    System.out.println("Enter the quantity to sell:");
                    int quan = sc.nextInt();
                    sc.nextLine();  // Consume newline left-over
                    user.sellStock(sym, quan);
                    break;
                case 4:
                    List<String> res = user.getHistory();
                    System.out.println(res);
                    break;
                case 5:
                    System.out.println("This feature I will make later");
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Unknown data entered");
            }
        }

        sc.close();
    }
}
