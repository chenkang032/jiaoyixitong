
import java.math.BigInteger;

import java.security.MessageDigest;


import java.util.Scanner;

public class Password {
    private String password;



    private Password() throws Exception{
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("请设置密码：");
                String password = scanner.next();
                set(password);
                System.out.println("设置成功");
                break;
            } catch (PasswordException e) {
                System.out.println("密码有误，请重新设置");
                System.out.println(e.getMessage());
            }
        }
    }
    public static Password creatPassword() throws Exception{
        return new Password();
    }



    private boolean check() {
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{9,}$");
    }

    public boolean checkPassword(String input) throws Exception {
        input = encryp(input);
        return input.equals(this.password);
    }

    public boolean set (String password) throws PasswordException, Exception{
        this.password = password;
        if (check()) {
            this.password = encryp(password);
            return true;
        }
        this.password = null;
        throw new PasswordException("密码须有9位及以上的，大小写字母，数字和标点符号");
    }



    public Password(int a) throws Exception{
        this.password = encryp("ynuadmin");

    }




        public static final String KEY_SHA = "SHA";

        public static  String  encryp(String inputStr) {
            BigInteger sha =null;

            byte[] inputData = inputStr.getBytes();
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
                messageDigest.update(inputData);
                sha = new BigInteger(messageDigest.digest());

            } catch (Exception e) {e.printStackTrace();}
            return sha.toString(32);
        }
}
