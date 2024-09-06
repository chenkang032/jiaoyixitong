
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ProductsController {
    private static ProductsController instance = new ProductsController();

     List<Product> products = new ArrayList<>();
     HashMap<String,Integer> map = new HashMap<>();


    public   boolean add(Product aProduct) {
        products.add(aProduct);
        map.put(aProduct.ID, products.indexOf(aProduct));
        return true;
    }
//    public  boolean delete() {
//        return delete(aProduct);
//    }

    public  boolean delete(Product aProduct) {
        Scanner scanner = new Scanner(System.in);
        if (products.size() == 0) return false;
        while (true) {
            System.out.print("是否删除该商品?  (y/n)");
            char ch = scanner.next().charAt(0);
            if (ch == 'n') return true;
            else if (ch == 'y') {
                if (map.containsKey(aProduct.name)) {
                    products.remove(aProduct);
                    map.remove(aProduct.name);
                    System.out.println("删除成功");
                    return true;
                }
                System.out.print("该商品不存在");
                return false;
            } else {
                System.out.println("请重新输入");
            }

        }





    }

    public  void showAllProducts() {
        if (products.size() == 0) {
            System.out.println("无商品");
            return;
        }
        for (int i = 0; i < products.size(); i++) {

            products.get(i).show();
            System.out.println("=====================");
        }
    }

    public  boolean showAProduct(String aProductName) {
        if (map.containsKey(aProductName)) {
            products.get(map.get(aProductName)).show();
            System.out.println("=====================");
            return true;
        }
        return false;

    }

    public  void showAProduct(Product aProduct) {
        showAProduct(aProduct.ID);
    }

    public  boolean correctAProduct(String aProductID) {
        if (map.containsKey(aProductID)) {
            products.get(map.get(aProductID)).correct();
            return true;
        }
        return false;
    }

    public  boolean correctAProduct(Product aProduct) {
        return correctAProduct(aProduct.ID);
    }

    private ProductsController(){};

    public static ProductsController getInstance() {
        return instance;
    }




}
