public class PasswordBoundary implements Boundary{

    @Override
    public  void showMenu() {
        System.out.print("=====================\n" +
                "1.忘记密码\n" +
                "2.修改密码\n" +
                "3.退出当前界面\n" +
                "选择：");
    }


}
