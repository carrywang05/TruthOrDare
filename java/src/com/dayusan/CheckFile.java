package com.dayusan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.ByteBuffer;

/**
 * 检查某些文件的敏感信息问题
 * @author carry
 */
public class CheckFile {
    private static final Pattern IP_PATTERN = Pattern.compile("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");
    private static final Pattern PASSWORD_KEY_PATTERN = Pattern.compile("(password|key)\\s*=\\s*\"[^\"]+\"");
    // NOCC:AvoidEscapedUnicodeCharacters(设计如此:)
    private static final Pattern CHINESE_GARBLED_CHARACTERS_PATTERN = Pattern.compile("[^\u4e00-\u9fa5\\w]+");

    /**
     * 入口函数
     * @param args
     */
    public static void main(String[] args) {
        // 指定的文件路径
        String filePath = "";

        //读取用户指定的文件路径
        Scanner input = new Scanner(System.in);
        System.out.println("请指定待检查的文件路径：");
        filePath = input.nextLine();

        // 从命令行获取指定的文件路径
        if (filePath.isEmpty()) {
            System.out.println("未指定待检查的文件路径，程序结束。");
            return;
        }

        // 读取指定的文件
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("读取文件时出错：" + e.getMessage());
            return;
        }

        // 让用户选择要检查的信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择要检查的信息：");
        System.out.println("1. IP");
        System.out.println("2. 密码密钥");
        System.out.println("3. 中文乱码");
        int choice = scanner.nextInt();
        scanner.close();

        // 检查指定的信息
        switch (choice) {
            case 1:
                checkIP(lines);
                break;
            case 2:
                checkPasswordKeys(lines);
                break;
            case 3:
                checkChineseGarbledCharacters(lines);
                break;
            default:
                System.out.println("无效的选择。");
                break;
        }
    }

    // 检查IP
    private static void checkIP(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Matcher matcher = IP_PATTERN.matcher(line);
            while (matcher.find()) {
                System.out.println("在第 " + (i + 1) + " 行发现 IP：" + matcher.group());
            }
        }
    }

    // 检查密码密钥
    private static void checkPasswordKeys(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Matcher matcher = PASSWORD_KEY_PATTERN.matcher(line);
            while (matcher.find()) {
                System.out.println("在第 " + (i + 1) + " 行发现密码密钥：" + matcher.group());
            }
        }
    }

    // 检查中文乱码
    private static void checkChineseGarbledCharacters(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String garbledCharacters = checkChineseGarbledCharacters3(line);
            while (garbledCharacters.isEmpty() == false) {
                System.out.println("在第 " + (i + 1) + " 行发现中文乱码：" + garbledCharacters);
            }
        }
    }

    // 检查中文乱码字符
    private static String checkChineseGarbledCharacters2(String content) {
        StringBuilder sb = new StringBuilder();
        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPORT);
        decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        try {
            ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(content);
            CharBuffer charBuffer = decoder.decode(byteBuffer);
        } catch (CharacterCodingException e) {
            String message = e.getMessage();
            int index = message.indexOf("at position");
            if (index != -1) {
                message = message.substring(0, index);
            }
            sb.append(message);
        }
        return sb.toString();
    }

    // 检查中文乱码字符
    private static String checkChineseGarbledCharacters3(String content) {
        StringBuilder sb = new StringBuilder();
        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPORT);
        decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        try {
            ByteBuffer byteBuffer = ByteBuffer.wrap(content.getBytes("UTF-8"));
            CharBuffer charBuffer = decoder.decode(byteBuffer);
        } catch (CharacterCodingException e) {
            String message = e.getMessage();
            int index = message.indexOf("at position");
            if (index != -1) {
                message = message.substring(0, index);
            }
            sb.append(message);
        } catch (IOException e) {
            System.out.println("编码时出错：" + e.getMessage());
            return "";
        }
        return sb.toString();
    }
}