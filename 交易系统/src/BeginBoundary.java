public class BeginBoundary implements Boundary{
    @Override
    public void showMenu() {
        System.out.print("=====================\n" +
                "1.退出\n" +
                "2.登入\n" +
                "3.注册\n" +
                "选择：");
    }
}
