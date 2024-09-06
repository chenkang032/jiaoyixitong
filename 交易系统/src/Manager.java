import java.util.Scanner;

public class Manager {

    private static Manager manager = new Manager();

    private Manager() {

    }

    public static Manager getInstance() {
        return manager;
    }


    private Customer i = new Customer(0);
    private CustomersController cc = CustomersController.getInstance();
    private PasswordController pc = PasswordController.getInstance();


    public void resetPassword() {
        pc.resetPassword(i.password);
    }

    public void setPowerOfLogin(String name) {
        Customer customer = cc.customers.get(cc.map.get(name));
        customer.canLogIn = true;
    }


    public void function1() {//管理用户
        ManageCustomerBoundary boundary = new ManageCustomerBoundary();
        Scanner scanner = new Scanner(System.in);
        CustomersController instance = CustomersController.getInstance();
        int num = 0;
        while (true) {
            try {
                boundary.showMenu();
                num = Integer.parseInt(scanner.next());
                if (num == 1) {
                    cc.showAllCustomers();
                } else if (num == 2){
                    System.out.print("请输入用户名:");
                    String str = scanner.next();
                    if (instance.map.containsKey(str)) {
                        cc.delete(str);
                    } else {
                        System.out.print("\n请输入已存在的用户名");
                    }
                } else if (num == 3){
                    System.out.print("请输入用户名:");
                    String str = scanner.next();
                    if (instance.map.containsKey(str)) {
                        cc.showACustomer(str);

                    } else {
                        System.out.print("\n请输入已存在的用户名");
                    }
                } else if (num == 4){
                    System.out.print("请输入用户名:");
                    String str = scanner.next();
                    if (instance.map.containsKey(str)) {
                        pc.resetACustomerPassword(instance.get(str));

                    } else {
                        System.out.print("\n请输入已存在的用户名");
                    }
                } else if (num == 5) {
                    System.out.print("请输入用户名:");
                    String str = scanner.next();
                    if (instance.map.containsKey(str)) {
                        setPowerOfLogin(str);
                        System.out.print("解锁成功");
                    } else {
                        System.out.print("\n请输入已存在的用户名");
                    }

                } else if (num == 6){
                    break;
                } else {
                    System.out.println("请合理输入");
                }

            } catch (NumberFormatException e) {
                System.out.println("请合理输入");
            }
        }
    }

    public void function2() {//管理商品
        ProductBoundary menu = new ProductBoundary();
        Scanner scanner = new Scanner(System.in);
        ProductsController pc = ProductsController.getInstance();
        int num = 0;
        while (true) {
            try {
                //"1.退出当前界面\n" +
                //                "2.展示所有商品详细信息\n" +
                //                "3.展示指定商品详细信息\n" +
                //                "4.修改商品信息\n" +
                //                "5.增加商品\n" +
                //                "6.删除商品\n" +
                //                "选择：");
                menu.showMenu();
                num = Integer.parseInt(scanner.next());
                if (num == 1) {
                    break;
                } else if (num == 2){
                    pc.showAllProducts();
                } else if (num == 3){
                    System.out.print("请输入商品名:");
                    String str = scanner.next();
                    if (pc.map.containsKey(str)) {
                        pc.products.get(pc.map.get(str) - 1).show();
                    } else {
                        System.out.println("\n请输入已存在的商品");
                    }
                } else if (num == 4){
                    System.out.print("请输入商品名:");
                    String str = scanner.next();
                    if (pc.map.containsKey(str)) {
                        pc.products.get(pc.map.get(str) - 1).correct();
                    } else {
                        System.out.println("\n请输入已存在的商品");
                    }
                } else if (num == 5) {
                    Product.getAProduct();
                } else if (num == 6) {
                    System.out.print("请输入商品名:");
                    String str = scanner.next();
                    if (pc.map.containsKey(str)) {
                        pc.delete(pc.products.get(pc.map.get(str) - 1));
                    } else {
                        System.out.println("\n请输入已存在的商品");
                    }
                } else {
                    System.out.println("请合理输入");
                }

            } catch (NumberFormatException e) {
                System.out.println("请合理输入");
            }
        }
    }

    public void function() {
        ManagerBoundary menu = new ManagerBoundary();
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        while (true) {
            try {
                menu.showMenu();
                num = Integer.parseInt(scanner.next());
                if (num == 1) {
                    break;//离开当前界面
                } else if (num == 2){
                    resetPassword();//修改自身密码
                } else if (num == 3){
                   function1();//管理客户
                } else if (num == 4){
                    function2();//管理商品
                } else {
                    System.out.println("请合理输入");
                }

            } catch (NumberFormatException e) {
                System.out.println("请合理输入");
            }
        }
    }

}
