package com.dayusan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 入口函数
 * @author carry
 */
public class SortNumber {
    /**
     * 接受用户输入后对数字进行排序
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] numbers = new int[1123];

        // 获取用户输入的10个数字
        System.out.println("请输入1123个数字：");
        for (int i = 0; i < 1123; i++) {
            numbers[i] = input.nextInt();
        }

        // 对数字进行排序
        Arrays.sort(numbers);

        // 输出排序后的数字
        System.out.println("排序后的数字为：");
        for (int i = 0; i < 1122; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}