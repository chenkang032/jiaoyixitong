public class HistoryBoundary implements Boundary{
    @Override
    public void showMenu() {
        System.out.print("=====================\n" +
                "1.离开当前界面\n" +
                "2.查看所有历史\n" +
                "3.查看一个购物历史的详细内容\n" +
                "选择：");
    }
}
