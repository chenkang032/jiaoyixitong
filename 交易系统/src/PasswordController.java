import java.security.Security;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import javax.mail.PasswordAuthentication;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class PasswordController {
    private static PasswordController instance = new PasswordController();


    void resetPassword(Password aPassword) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("新密码：");
                aPassword.set(scanner.next());
                break;
            } catch (Exception e) {
                System.out.println("密码须有5位及以上的，大小写字母，数字和标点符号");
            }
        }
    }

    void resetACustomerPassword(Customer aCustomer) {
        Scanner scanner = new Scanner(System.in);
        boolean again = true;
        while (again)
            try {
                aCustomer.password = Password.creatPassword();
                System.out.print("请确认密码：");
                String next = scanner.next();
                if (aCustomer.password.checkPassword(next)) {
                    again = false;
                } else {
                    System.out.println("请重设密码");
                }

            } catch (Exception e) {

            }
    }

    void setForgottingPassword(Customer aCustomer) {
        Scanner scanner = new Scanner(System.in);

        boolean again = true;
        while (again)
            try {
                String str1 = sendEmail(aCustomer);
                System.out.print("请确认密码：");
                String str2 = scanner.next();
                if (str1.equals(str2)) {
                    aCustomer.password.set(str2);
                    System.out.println("设置成功");
                    again = false;
                } else {
                    System.out.println("请合理输入，密码将再次发送");
                }

            } catch (Exception e) {

            }
    }

    private PasswordController() {
    }


    public static PasswordController getInstance() {
        return instance;
    }

    public String sendEmail(Customer customer) throws Exception{



        // 发件人邮箱、密码（如果是授权码，则填写授权码）
            String from = "ck18649791225@163.com";
            String password = "TEIXRPMQHIIBJLSI";

            // 收件人邮箱
            String to = customer.email;

            // 邮件服务器配置
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.163.com"); // SMTP服务器地址
            props.put("mail.smtp.port", "465"); // SMTP服务器端口
            props.put("mail.smtp.auth", "true"); // 是否需要身份验证
            props.put("mail.smtp.ssl.enable", "true"); // 启用TLS加密

            // 创建Session实例
            Session session = Session.getInstance(props, new Authenticator() {


                protected PasswordAuthentication getPasswordAuthentication() {


                    return new PasswordAuthentication(from, password);
                }
            });
            String str = null;
            try {


                // 创建邮件实例
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject("新的密码"); // 邮件主题
                str = randomPassword();
                message.setText(str); // 邮件正文
                //TEIXRPMQHIIBJLSI

                Transport.send(message);
                //System.out.println("Email sent successfully!");
            } catch (MessagingException e) {


                throw new RuntimeException(e);
            }
            return str;
        }





    private static String randomPassword() {
        StringBuilder ans = new StringBuilder();
        //[0-9A-Za-z,.?:;\"!]
        char[] s0 = new char[] {',', '.', '?', ':', ';',  '\"', '!'};
        int n = (int) (Math.random() * s0.length) + 1;
        int len = s0.length;
        char ch;
        for (int i = 0; i < n; i++) {
            ch = s0[(int) (Math.random() * len)];
                ans.append(ch);
            }
        int length = ((int) (Math.random()) + 1);
        for (int i = 0, time = 0; i <= 9 && time < length; i++) {
            if (Math.random() > 0.5) {
                time++;
                ans.append(i);
            }
        }

        length = 5 - length;
        char ch1 = 'a';
        char ch2 = 'A';
        for (int i = 0; i < length; i++ ) {
            if (Math.random() > 0.5) {
                ans.append((char) (ch1 + (int) (Math.random() * 26)));
            } else {
                ans.append((char) (ch2 + (int) (Math.random() * 26)));
            }

        }
        ans.append((char) (ch1 + (int) (Math.random() * 26)));
        ans.append((char) (ch2 + (int) (Math.random() * 26)));
        return ans.toString();
    }

    public  void managePassword(String aCustomerName) {
        PasswordBoundary menu = new PasswordBoundary();
        Scanner scanner = new Scanner(System.in);
        CustomersController cc = CustomersController.getInstance();
        int num = 0;
        while (true) {
            try {
                menu.showMenu();
                num = Integer.parseInt(scanner.next());
                if (num == 1) {
                    setForgottingPassword(cc.get(aCustomerName));

                } else if (num == 2){
                    resetACustomerPassword(cc.get(aCustomerName));

                } else if (num == 3){
                    break;
                } else {
                    System.out.println("请合理输入");
                }

            } catch (NumberFormatException e) {
                System.out.println("请合理输入");
            }
        }
    }

    public static void main(String[] args) throws Exception{
        PasswordController pc = PasswordController.getInstance();
        String s = pc.sendEmail(new Customer());
    }





}

