public class CustomerBoundary implements Boundary{
    @Override
    public void showMenu() {
        System.out.print("=====================\n" +
                "1.返回登录界面\n" +
                "2.管理密码\n" +
                "3.购物\n" +
                "4.查看购物历史\n" +
                "选择：");
    }
}
