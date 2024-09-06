import java.util.Scanner;

public class Begin {




    public void sign() {
        new Customer();
    }

    public void login() throws Exception{
        Manager instance = Manager.getInstance();//获得管理类的实例
        Scanner scanner = new Scanner(System.in);//获得扫描类的实例
        CustomersController cc = CustomersController.getInstance();
        while (true) {
            System.out.print("用户名：");
            String next = scanner.next();
            if (cc.map.containsKey(next)) {//验证账号是否存在
                Customer customer = cc.customers.get(cc.map.get(next));
                if (!customer.canLogIn) {//判断是否能登入
                    System.out.println("该账号已锁定");
                    break;
                }
                for (int i = 0; i < 5; i++) {//总共验证5次密码，若均为密码均错误，则账户锁定，需要管理员解锁
                    System.out.print("密码：");
                    next = scanner.next();
                    if(customer.canLogIn && customer.password.checkPassword(next)) {
                        if("admin".equals(customer.name)) {
                            instance.function();//管理员功能
                        } else {
                            customer.function();//顾客功能
                        }
                        return;
                    } else {
                        System.out.println("密码有误，请重新输入");
                    }
                }
                customer.canLogIn = false;
                System.out.println("该账号已锁定");
                break;
            } else {
                System.out.println("请合理输入");
            }

        }





    }

    public void function() {
        Manager instance = Manager.getInstance();
        BeginBoundary menu = new BeginBoundary();
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        while (true) {
            try {
                //"1.退出\n" +
                //                "2.登入\n" +
                //                "3.注册\n"
                menu.showMenu();
                num = Integer.parseInt(scanner.next());
                if (num == 1) {
                    break;//退出
                } else if (num == 2){
                    login();//登录
                } else if (num == 3){
                    sign();//注册
                }  else {
                    System.out.println("请合理输入");
                }

            } catch (Exception e) {
                System.out.println("请合理输入");
            }
        }

    }


}
