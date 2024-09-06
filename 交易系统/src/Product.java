
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import java.util.Scanner;

public class Product {
    String ID;
    String name;
    String manufacturer;
    String category;
    double purchasePrice;
    double retailPrice;
    int quantity;
    LocalDate productionDate;

    public Product( String name, String manufacturer, String category, double purchasePrice, double retailPrice, int quantity, LocalDate productionDate) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.category = category;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.quantity = quantity;
        this.productionDate = productionDate;
        ProductsController pc = ProductsController.getInstance();
        pc.products.add(this);
        pc.map.put(this.name,pc.products.size());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pc.products.size());
        this.ID = stringBuilder.toString();

    }

    private Product() {

        set();
        ProductsController pc = ProductsController.getInstance();
        pc.products.add(this);
        pc.map.put(this.name,pc.products.size());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pc.products.size());
        this.ID = stringBuilder.toString();


    }
    public static Product getAProduct() {
        return new Product();
    }

    public static Product creatProduct(String ID, String name, String manufacturer, String category, double purchasePrice, double retailPrice, int quantity, LocalDate productionDate) {
        return new Product(name, manufacturer, category, purchasePrice, retailPrice, quantity, productionDate);
    }
    public void show() {
        System.out.println(
                "商品编号:" + this.ID +
                        "\n商品名称:" + this.name +
                        "\n商品生厂商:" + this.manufacturer +
                        "\n商品种类:" +  this.category +
                        "\n商品进货价:" + this.purchasePrice +
                        "\n商品销售价:" + this.retailPrice +
                        "\n商品数量:" + this.quantity +
                        "\n商品生产日期:" + this.productionDate
        );
    }

    public void correct() {
        Scanner scanner = new Scanner(System.in);
        final int minPosition = 0;
        final int maxPosition = 7;
        System.out.print(
                "1. 修改商品编号\n" +
                        "2. 修改商品名称\n" +
                        "3. 修改生厂商\n" +
                        "4. 修改商品种类\n" +
                        "5. 修改零售价\n" +
                        "6. 修改商品数目\n" +
                        "7. 修改商品日期\n" +
                        "0. 结束修改\n" +
                        "请选择修改项："
        );
        int num;
        String str;
        while (true) {
            try {
                num = Integer.parseInt(scanner.next());
                if (num == minPosition) break;
                else if (num > minPosition && num <= maxPosition) {
                    switch (num) {
                        case 1:
                            System.out.println("旧商品编号:" + this.ID);
                            System.out.print("新商品编号:");
                            this.ID = scanner.next();
                            System.out.print("修改完成，请再次选择修改项：");
                            break;
                        case 2:
                            System.out.println("旧商品名称:" + this.name);
                            System.out.print("新商品名称:");
                            this.name = scanner.next();
                            System.out.print("修改完成，请再次选择修改项：");
                            break;
                        case 3:
                            System.out.println("旧商品生产商:" + this.manufacturer);
                            System.out.print("新商品生产商:");
                            this.manufacturer = scanner.next();
                            System.out.print("修改完成，请再次选择修改项：");
                            break;
                        case 4:
                            System.out.println("旧商品种类:" + this.category);
                            System.out.print("新商品种类:");
                            this.category = scanner.next();
                            System.out.print("修改完成，请再次选择修改项：");
                            break;

                        case 5:
                        System.out.println("旧商品临售价:" + this.purchasePrice);
                        System.out.print("新商品临售价:");
                        while (true) {
                            try {
                                this.purchasePrice = Double.parseDouble(scanner.next());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("请合理输入价格");
                                System.out.print("新商品临售价:");
                            }
                        }
                        System.out.print("修改完成，请再次选择修改项：");
                        break;
                        case 6:
                            System.out.println("旧商品数量:" + this.quantity);
                            System.out.print("新商品数量:");
                            while (true) {
                                try {
                                    this.quantity = Integer.parseInt(scanner.next());
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("请合理输入商品数量");
                                    System.out.print("新商品数量:");
                                }
                            }
                            System.out.print("修改完成，请再次选择修改项：");
                            break;
                        case 7:
                            System.out.println("旧商品生产日期:" + this.productionDate);
                            correctProductionDate_Year();
                            correctProductionDate_Month();
                            correctProductionDate_Date();
                            System.out.println("新商品生产日期:" + this.productionDate);
                            System.out.print("修改完成，请再次选择修改项：");
                            break;

                    }
                } else {
                    System.out.println("请输入0-7的整数");
                    System.out.print("请选择修改项：");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("请输入0-7的数");
                System.out.print("请选择修改项：");
            }
        }
        System.out.println("修改后商品信息");
        this.show();

    }
    private void correctProductionDate_Year() {
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            System.out.print("是否修改生产年份?  (y/n)");
            char ch = scanner.next().charAt(0);
            if (ch == 'n') return;
            else if (ch == 'y') {
                while (true) {
                    try {
                        System.out.print("请输入生产年份:");
                        num = Integer.parseInt(scanner.next());
                        if (num > LocalDateTime.now().getYear()) {
                            System.out.println("请合理输入生产年份");
                        } else {
                            this.productionDate = LocalDate.of(num, this.productionDate.getMonthValue(), this.productionDate.getDayOfMonth());
                            return;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("请合理输入生产年份");
                    }
                }
            } else {
                System.out.println("请重新输入");
            }

        }


    }

    private void correctProductionDate_Month() {
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            System.out.print("是否修改生产月份?  (y/n)");
            char ch = scanner.next().charAt(0);
            if (ch == 'n') return;
            else if (ch == 'y') {
                while (true) {
                    try {
                        System.out.print("请输入生产月份:");
                        num = Integer.parseInt(scanner.next());
                        if (num > Calendar.DECEMBER + 1 || num < Calendar.JANUARY + 1) {
                            System.out.println("请合理输入生产月份");
                        } else {
                            this.productionDate = LocalDate.of(this.productionDate.getYear(), num, this.productionDate.getDayOfMonth());
                            return;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("请合理输入生产年份");
                    }
                }
            } else {
                System.out.println("请重新输入");
            }
        }
    }

    private void correctProductionDate_Date() {
        Scanner scanner = new Scanner(System.in);
        int num;
        final int MIN_DAY_IN_MONTH = 1;
        int MAX_DAY_IN_MONTH = getDayInMonth(this.productionDate.getMonthValue());
        while (true) {
            System.out.print("是否修改生产日?(1~" + MAX_DAY_IN_MONTH+ "天)   (y/n)");
            char ch = scanner.next().charAt(0);
            if (ch == 'n') return;
            else if (ch == 'y') {
                while (true) {
                    try {
                        System.out.print("请输入生产日 (1~" + MAX_DAY_IN_MONTH+ "天):");
                        num = Integer.parseInt(scanner.next());
                        if (num < MIN_DAY_IN_MONTH || num > MAX_DAY_IN_MONTH) {
                            System.out.println("请合理输入生产日");
                        } else {
                            this.productionDate = LocalDate.of(this.productionDate.getYear(),this.productionDate.getMonth(),num);
                            return;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("请合理输入生产日");
                    }
                }
            } else {
                System.out.println("请重新输入");
            }
        }
    }

    private int getDayInMonth(int Month) {
        switch (Month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return  31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return 28;
            default:
                return -1;
        }


    }



    public void increase() {
        this.quantity++;
    }

    public boolean decrease() {
        if (this.quantity > 0) {
            this.quantity--;
            return true;
        } else {
            return false;
        }
    }

    public void setID() {
    }

    public void setName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("商品名:");
        this.name = scanner.next();
    }

    public void setManufacturer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("生厂商:");
        this.manufacturer = scanner.next();
    }

    public void setCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("商品种类:");
        this.category = scanner.next();
    }

    public void setPurchasePrice() {
        Scanner scanner = new Scanner(System.in);
        double num = 0;
        while (true) {
            try {
                System.out.print("商品成本:");
                num = Double.parseDouble(scanner.next());
                if (num >= 0) {
                    this.purchasePrice = num;
                    break;
                } else {
                    System.out.println("请合理输入数字");
                }
            } catch (NumberFormatException e) {
                System.out.println("请合理输入数字");
            }
        }
    }

    public void setRetailPrice() {
        Scanner scanner = new Scanner(System.in);
        double num = 0;
        while (true) {
            try {
                System.out.print("商品价格:");
                num = Double.parseDouble(scanner.next());
                if (num >= 0) {
                    this.retailPrice = num;
                    break;
                } else {
                    System.out.println("请合理输入");
                }
            } catch (NumberFormatException e) {
                System.out.println("请合理输入");
            }
        }
    }

    public void setQuantity() {
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            try {
                System.out.print("商品数量:");
                num = Integer.parseInt(scanner.next());
                if (num >= 0) {
                    this.quantity = num;
                    break;
                } else {
                    System.out.println("请合理输入");
                }

            } catch (NumberFormatException e) {
                System.out.println("请合理输入");
            }
        }
    }

    public void setProductionDate() {
        this.productionDate = LocalDate.now();
    }

    public void set() {
        setID();
        setName();
        setManufacturer();
        setCategory();
        setProductionDate();
        setPurchasePrice();
        setRetailPrice();
        setQuantity();

    }

    public static void main(String[] args) {
        Product.getAProduct().show();
    }
}
