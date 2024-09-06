import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {
    Product product;
    LocalDate time;
    int id;
    public void showProductionDate() {
        System.out.println("" + time.getYear() + "年" + time.getMonthValue() + "月" + time.getDayOfMonth() + "日");
    }

    private Order(Product product, LocalDate time, int id) {
        this.product = product;
        this.time = time;
        this.id = id;
    }

    public static Order creatOrder(Product product, LocalDate time, int id) {
        return new Order(product, time, id);
    }
}
