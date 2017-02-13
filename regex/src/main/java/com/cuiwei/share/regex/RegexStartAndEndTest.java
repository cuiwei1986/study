package com.cuiwei.share.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexStartAndEndTest {

    // 一行中，^匹配开始 $匹配结束。java默认模式下匹配输入字符串开始/结束(或结尾的换行符)的位置

    @Test
    public void test0() {
        String str = "cat\r\n";
        Pattern pattern = Pattern.compile("^cat$");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test1() {
        String str = "cat" + "\r\n" + "cat1";
        Pattern pattern = Pattern.compile("^cat");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    // 如果设置了 Pattern.MULTILINE属性，还会与"\n","\r"或"\r\n"的位置匹配。

    @Test
    public void test2() {
        String str = "cat" + "\r\n" + "cat1";
        Pattern pattern = Pattern.compile("^cat", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    @Test
    public void test3() {
        String str = "cat" + "\r\n" + "cat1";
        Pattern pattern = Pattern.compile("(?m)^cat");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    // 如果添加Pattern.UNIX_LINES只会和"\n"的位置匹配。
    
    @Test
    public void test4() {
        String str = "cat" + "\n" + "cat1";
        Pattern pattern = Pattern.compile("^cat", Pattern.MULTILINE | Pattern.UNIX_LINES);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test5() {
        String str = "cat" + "\r" + "cat1";
        Pattern pattern = Pattern.compile("^cat", Pattern.MULTILINE | Pattern.UNIX_LINES);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // \A 与^类似,但总是相当于普通模式
    @Test
    public void test6() {
        String str = "cat" + "\r\n" + "cat1";
        Pattern pattern = Pattern.compile("\\Acat", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // \Z(匹配结束或换行符) \z(只匹配结束)与$类似 ，但总是相当于普通模式
}
