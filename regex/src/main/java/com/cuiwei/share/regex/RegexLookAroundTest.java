package com.cuiwei.share.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexLookAroundTest {

    // 环视(断言)，不匹配任何字符，只匹配文本中的位置
    // 肯定顺序环视(?=...)
    
    @Test
    public void test0() {
        String str = "cat";
        Pattern pattern = Pattern.compile("ca(?=t)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test1() {
        String str = "cat";
        Pattern pattern = Pattern.compile("ca(?=t)t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // 肯定逆序环视(?<=...)
    @Test
    public void test2() {
        String str = "http://www.mtime.com";
        Pattern pattern = Pattern.compile("(?<=http://)www.[\\w.]+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    @Test
    public void test3() {
        String str = "12cat";
        Pattern pattern = Pattern.compile("(?<=\\d{2})cat");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    @Test
    public void test4() {
        String str = "1cat";
        Pattern pattern = Pattern.compile("(?<=\\d{2})cat");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // 否定顺序环视(?!...)
    // 否定逆序环视(?<!...)

}
