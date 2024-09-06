public class ProductBoundary implements Boundary{
    public void showMenu() {
        System.out.print("=====================\n" +
                "1.退出当前界面\n" +
                "2.展示所有商品详细信息\n" +
                "3.展示指定商品详细信息\n" +
                "4.修改商品信息\n" +
                "5.增加商品\n" +
                "6.删除商品\n" +
                "选择：");
    }
}
