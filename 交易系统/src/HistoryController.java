import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.LinkedList;
import java.util.List;

public class HistoryController {
    private static HistoryController controller = new HistoryController();
    List<Order> orders = new LinkedList<>();

     void add(Order aOrder) {
        orders.add(aOrder);
    }

     void show(Order aOrder) {
        System.out.println("时间：" + aOrder.time + "\n商品名： " + aOrder.product.name + "\n订单id： " + aOrder.id);
    }
     void showDetail(Order aOrder) {
        this.show(aOrder);
        aOrder.product.show();
    }

     void showAll() {
        for (int i = 0; i < orders.size(); i++) {
            this.show(orders.get(i));
        }
    }

    private HistoryController(){};

     public static HistoryController getInstance(){
         return controller;
     }


}
