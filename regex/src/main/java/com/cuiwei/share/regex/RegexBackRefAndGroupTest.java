package com.cuiwei.share.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexBackRefAndGroupTest {

    // 反向引用,匹配与表达式先前部分(括号内的内容)匹配的同样文本
    @Test
    public void test1() {
        String str = "catas";
        Pattern pattern = Pattern.compile("c([a-z])t\\1");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test2() {
        String str = "catbs";
        Pattern pattern = Pattern.compile("c([a-z])t\\1");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test3() {
        String str = "cat1cat2";
        Pattern pattern = Pattern.compile("([a-z]+).\\1");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test4() {
        String str = "c1ac2a";
        Pattern pattern = Pattern.compile("([a-z]).([a-z])\\1.\\2");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    // 分组
    @Test
    public void test5() {
        String str = "a1b2c3";
        Pattern pattern = Pattern.compile("([a-z])([0-9])");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(
                    matcher.group() + "-" + matcher.group(0) + "-" + matcher.group(1) + "-" + matcher.group(2));
            System.out.println(
                    matcher.start() + "-" + matcher.start(0) + "-" + matcher.start(1) + "-" + matcher.start(2));
            System.out.println(matcher.end() + "-" + matcher.end(0) + "-" + matcher.end(1) + "-" + matcher.end(2));
        }
    }
}
