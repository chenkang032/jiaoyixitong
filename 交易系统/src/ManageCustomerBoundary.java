public class ManageCustomerBoundary implements Boundary{
    @Override
    public void showMenu() {
        System.out.print("=====================\n" +
                "1.列出所有客户信息\n" +
                "2.删除指定用户信息\n" +
                "3.查询客户信息\n" +
                "4.重设用户密码\n" +
                "5.解锁用户账号\n" +
                "6.退出当前界面\n" +
                "选择：");
    }
}
