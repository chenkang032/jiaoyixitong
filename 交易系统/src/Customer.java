import java.time.LocalDate;
import java.util.*;

public class Customer {
    boolean canLogIn = true;
    String ID;
    String name;
    String level;
    LocalDate signInTime;
    double totalConsumption = 0;
    String tel;
    String email;
    Password password;
    HashMap<Product,Integer> cart = new HashMap<>();

    static ProductsController pc = ProductsController.getInstance();
    public  void setTel() {
        Scanner scanner = new Scanner(System.in);
        String  str;
        while (true) {
            System.out.print("请输入电话: ");
            if ((str = scanner.next()).matches("0?(13|14|15|18|17)[0-9]{9}")) {
                this.tel = str;
                break;
            } else {
                System.out.println("输入有误，请重新输入");
            }
        }
    }

    public Customer(int a) {
        this.name = "admin";
        try {
            this.password = new Password(0);
        } catch (Exception e) {

        }
        CustomersController cc = CustomersController.getInstance();
        cc.customers.add(this);
        cc.map.put(this.name,cc.customers.indexOf(this));
        StringBuilder b = new StringBuilder();
        b.append(cc.customers.size());
        this.ID = b.toString();
    }

    public void setEmail() {
        Scanner scanner = new Scanner(System.in);
        String  str;
        while (true) {
            System.out.print("请输入邮箱: ");
            if ((str = scanner.next()).matches("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}")) {
                this.email = str;
                break;
            } else {
                System.out.println("输入有误，请重新输入");
            }
        }
    }

    public void setName() {
        Scanner scanner = new Scanner(System.in);
        String str = "";
        while (true) {
            System.out.print("请输入用户名: ");
            if ((str = scanner.next()).matches("[A-Za-z0-9_\\-\\u4e00-\\u9fa5]{5,}")) {
                this.name = str;
                break;
            } else {
                System.out.println("输入有误，请重新输入");
            }
        }
    }

    public Customer() {
        setName();
        this.signInTime = LocalDate.now();
        setTel();
        setEmail();
        Scanner scanner = new Scanner(System.in);
        this.level = "铜牌客户";

        while (true) {
            try {
                this.password = Password.creatPassword();

                System.out.print("请确认密码：");
                String next = scanner.next();
                if (this.password.checkPassword(next)) {
                    break;
                }
            } catch (Exception e) {

            }
        }
        CustomersController cc = CustomersController.getInstance();
        cc.customers.add(this);
        cc.map.put(this.name,cc.customers.indexOf(this));
        StringBuilder b = new StringBuilder();
        b.append(cc.customers.size());
        this.ID = b.toString();

    }



    public void show() {
        int i = Integer.parseInt(this.ID);
        System.out.println("\n用户ID:" + (i - 1) +
                "\n用户名：" + this.name +
                "\n用户级别：" + this.level +
                "\n用户注册时间：" + this.signInTime +
                "\n用户累计消费总金额：" + this.totalConsumption +
                "\n用户手机号：" + this.tel +
                "\n用户邮箱：" + this.email
                );
    }

    public boolean getIn(Product aProduct) {
        if (aProduct.decrease()) {
            cart.put(aProduct, cart.getOrDefault(aProduct, 0) + 1);
            return true;
        } else {
            return false;
        }
    }

    public boolean getOut(Product aProduct) {
        if (cart.containsKey(aProduct) && cart.get(aProduct) > 0) {
            aProduct.increase();
            cart.put(aProduct, cart.get(aProduct) - 1);
            return true;
        } else {
            return false;
        }



    }

    public boolean pay() {
        Set<Product> products = cart.keySet();
        Iterator<Product> iterator = products.iterator();
        HistoryController instance = HistoryController.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("是否支付?  (y/n)");
            char ch = scanner.next().charAt(0);
            if (ch == 'n') return false;
            else if (ch == 'y') {
                while (iterator.hasNext()) {
                    Product next =  iterator.next();
                    this.totalConsumption += next.retailPrice * cart.get(next);
                    instance.orders.add(Order.creatOrder(next,LocalDate.now(),instance.orders.size() + 1));
                }
                cart.clear();
                System.out.println("支付成功");
                return true;

            } else {
                System.out.println("请重新输入");
            }

        }

    }



    public void function1() {//密码管理

        PasswordController.getInstance().managePassword(this.name);
    }

    public void function2() {//购物管理
        ShopBoundary menu = new ShopBoundary();
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        while (true) {
            try {
                menu.showMenu();
                num = Integer.parseInt(scanner.next());
                if (num == 1) {
                    break;
                } else if (num == 2){
                    pc.showAllProducts();
                    System.out.print("请输入商品名：");
                    String next = scanner.next();
                    if (pc.map.containsKey(next)) {
                        this.getIn(pc.products.get(pc.map.get(next) - 1));
                    } else {
                        System.out.println("请合理输入");
                    }

                } else if (num == 3){
                    showCart();
                    System.out.print("请输入商品名：");
                    String next = scanner.next();
                    Product product = pc.products.get(pc.map.get(next) - 1);
                    if (this.cart.containsKey(product)) {
                        this.getOut(product);
                    } else {
                        System.out.println("请合理输入");
                    }
                } else if (num == 4){
                    pay();
                } else {
                    System.out.println("请合理输入");
                }

            } catch (NumberFormatException e) {
                System.out.println("请合理输入");
            }
        }
    }

    public void showCart() {
        Set<Product> products = cart.keySet();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product next =  iterator.next();
            next.show();
            System.out.println("购物车内数量:" + cart.get(next));
        }
    }

    public void function3() {//历史管理
        HistoryController instance = HistoryController.getInstance();
        HistoryBoundary menu = new HistoryBoundary();
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        while (true) {
            try {
                menu.showMenu();
                num = Integer.parseInt(scanner.next());
                if (num == 1) {
                    break;
                } else if (num == 2){
                    instance.showAll();
                } else if (num == 3){
                    System.out.print("请输入订单id：");
                    String next = scanner.next();
                    int id = Integer.parseInt(next);
                    if (id <= instance.orders.size() && id > 0) {
                        instance.showDetail(instance.orders.get(id - 1));
                    } else {
                        System.out.println("请合理输入");
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

        CustomerBoundary menu = new CustomerBoundary();
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        while (true) {
            try {
                //"1.返回登录界面\n" +
                //                "2.管理密码\n" +
                //                "3.购物\n" +
                //                "4.查看购物历史" +
                //                "选择：");
                menu.showMenu();
                num = Integer.parseInt(scanner.next());
                if (num == 1) {
                    break;
                } else if (num == 2){

                    function1();
                } else if (num == 3){
                   function2();
                } else if (num == 4){
                    function3();
                } else {
                    System.out.println("请合理输入");
                }

            } catch (NumberFormatException e) {
                System.out.println("请合理输入");
            }
        }
    }

    public static void main(String[] args) {
        String str = "a";
        
    }
}
