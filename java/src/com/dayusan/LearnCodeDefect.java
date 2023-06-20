package com.dayusan;

import java.util.Scanner;

/**
 * 帮助用户学习代码缺陷案例
 * @author carry
 */
public class LearnCodeDefect {
    /**
     * 入口函数
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入要查询的代码缺陷类型（1-数组越界，2-空指针，3-资源泄露，4-使用未初始化的变量，5-弱密码，"
                    + "6-SQL注入，0-退出）：");
            int type = scanner.nextInt();

            switch (type) {
                case 1:
                    System.out.println("1、数组越界是指访问数组时，下标超出了数组的范围。这种错误通常会导致程序崩溃或者产生不可预知"
                            + "的结果。避免数组越界的方法是在访问数组元素之前，先检查数组的长度和下标是否合法。");
                    System.out.println("int[] arr = {1, 2, 3};");
                    System.out.println("for (int i = 0; i <= arr.length; i++) { // 数组越界");
                    System.out.println("    System.out.println(arr[i]);");
                    System.out.println("}");
                    break;
                case 2:
                    System.out.println("2、空指针是指在访问对象的属性或者调用对象的方法时，对象的引用为null。这种错误通常会导致程序崩溃或者产生不可预知的结果。避免空指针的方法是在访问对象之前，先检查对象的引用是否为null。");
                    System.out.println("String str = null;");
                    System.out.println("if (str.equals(\"hello\")) { // 空指针");
                    System.out.println("    System.out.println(\"str是hello\");");
                    System.out.println("}");
                    break;
                case 3:
                    System.out.println("3、资源泄露是指在使用完资源（如文件、数据库连接、网络连接等）后，没有正确地关闭资源，导致资源无法"
                            + "被其他程序使用。这种错误通常会导致系统性能下降或者崩溃。"
                            + "避免资源泄露的方法是在使用完资源后，及时关闭资源。");
                    System.out.println("FileInputStream fis = new FileInputStream(\"test.txt\");");
                    System.out.println("// 使用fis读取文件");
                    System.out.println("// ...");
                    System.out.println("// ...");
                    System.out.println("// ...");
                    System.out.println("// 忘记关闭fis");
                    break;
                case 4:
                    System.out.println("4、使用未初始化的变量是指在使用变量之前，没有对变量进行初始化。这种错误通常会导致程序产生不可预知"
                            + "的结果。避免使用未初始化的变量的方法是在使用变量之前，先对变量进行初始化。");
                    System.out.println("int x;");
                    System.out.println("int y = x + 1; // 使用未初始化的变量");
                    System.out.println("System.out.println(y);");
                    break;
                case 5:
                    System.out.println("5、弱密码是指密码过于简单，容易被猜测或者破解。这种错误通常会导致系统被攻击或者数据泄露。"
                            + "避免弱密码的方法是使用复杂的密码，包括大小写字母、数字和特殊字符，并定期更换密码。");
                    System.out.println("String password = \"123456\";");
                    System.out.println("if (password.equals(\"admin\")) { // 弱密码");
                    System.out.println("    System.out.println(\"密码正确\");");
                    System.out.println("}");
                    break;
                case 6:
                    System.out.println("6、SQL注入是指攻击者通过在输入框中输入恶意的SQL语句，从而获取或者修改数据库中的数据。"
                            + "这种错误通常会导致系统被攻击或者数据泄露。避免SQL注入的方法是使用参数化的SQL语句，"
                            + "或者对输入的数据进行过滤和验证。");
                    System.out.println("String username = \"admin\";");
                    System.out.println("String password = \"123456\";");
                    System.out.println("String sql = \"SELECT * FROM users WHERE username='\" + username + \"' "
                            + "AND password='\" + password + \"'\";");
                    System.out.println("// 执行sql语句");
                    break;
                case 0:
                    System.out.println("程序已退出。");
                    return;
                default:
                    System.out.println("输入的代码缺陷类型不正确，请重新输入。");
                    break;
            }
        }
    }
}
