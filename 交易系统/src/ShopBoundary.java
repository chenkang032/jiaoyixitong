public class ShopBoundary implements Boundary{
    @Override
    public void showMenu() {
        System.out.print("=====================\n" +
                "1.退出购物\n" +
                "2.选择物品加入购物车\n" +
                "3.选择物品移出购物车\n" +
                "4.支付\n" +
                "选择：");
    }
}
