public class ManagerBoundary implements Boundary {
    @Override
    public void showMenu() {
        System.out.print("=====================\n" +
                "1.离开当前界面\n" +
                "2.修改自身密码\n" +
                "3.管理客户\n" +
                "4.管理商品\n" +
                "选择：");
    }
}
