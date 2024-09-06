import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CustomersController {


    private static CustomersController instance = new CustomersController();


    public  List<Customer> customers = new ArrayList<>();
    public  HashMap<String,Integer> map = new HashMap<>();

    public  boolean add(Customer aCustomer) {
        customers.add(aCustomer);
        map.put(aCustomer.ID, customers.indexOf(aCustomer));
        return true;
    }
    public boolean delete(Customer aCustomer) {
        return delete(aCustomer.ID);
    }

    public boolean delete(String aCustomerName) {
        Scanner scanner = new Scanner(System.in);
        if ("admin".equals(aCustomerName)) {
            System.out.print("该用户不能删除");
            return false;
        }
        if (customers.size() == 0) return false;
        while (true) {
            System.out.print("是否删除该用户?  (y/n)");
            char ch = scanner.next().charAt(0);
            if (ch == 'n') return true;
            else if (ch == 'y') {
                if (map.containsKey(aCustomerName)) {
                    customers.remove(map.get(aCustomerName));
                    map.remove(aCustomerName);
                    return true;
                }
                return false;
            } else {
                System.out.println("请重新输入");
            }

        }





    }

    public void showAllCustomers() {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).name == "admin") continue;
            customers.get(i).show();
            System.out.println("=====================");
        }
    }

    public boolean showACustomer(String aCustomerID) {
        if (map.containsKey(aCustomerID)) {
            customers.get(map.get(aCustomerID)).show();
            System.out.println("=====================");
            return true;
        }
        return false;

    }

    public Customer get(String aCustomerName) {
        return customers.get(map.get(aCustomerName));
    }

    public void showACustomer(Customer aCustomerID) {
        showACustomer(aCustomerID.ID);
    }



    private CustomersController(){};

    public static CustomersController getInstance() {
        return instance;
    }


}
