package com.cuiwei.share.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexCharsTest {

    // []字符组,“某处”期望匹配的字符,例[abc][12]
    @Test
    public void test1() {
        String str = "cat";
        Pattern pattern = Pattern.compile("c[abc]t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    @Test
    public void test2() {
        String str = "cat";
        Pattern pattern = Pattern.compile("cat[12]");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // -字符组连接符,例[a-z][0-9]
    @Test
    public void test3() {
        String str = "cat";
        Pattern pattern = Pattern.compile("[a-z][a-z]t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    @Test
    public void test4() {
        String str = "cat1";
        Pattern pattern = Pattern.compile("cat[0-9]");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // [^]排除型支付组,例[^12]
    @Test
    public void test5() {
        String str = "cat";
        Pattern pattern = Pattern.compile("c[^a]t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    @Test
    public void test6() {
        String str = "c1t";
        Pattern pattern = Pattern.compile("c[^a-z]t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // \d 数字字符匹配等于[0-9]
    // \D 非数字字符匹配。等于[^0-9]
    @Test
    public void test7() {
        String str = "c1t";
        Pattern pattern = Pattern.compile("c\\dt");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // \w 匹配任何字类字符。等于[0-9a-zA-Z_]
    // \W 与任何非单词字符匹配。等于[^0-9a-zA-Z_]
    @Test
    public void test8() {
        String str = "cat我";
        Pattern pattern = Pattern.compile("c\\wt\\W");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // \f 换页符匹配。
    // \n 换行符匹配。
    // \r 回车符匹配。
    // \t 制表符匹配。
    // \v 垂直制表符匹配。
    // \s 匹配任何空白字符。等于[ \f\n\r\t\v]
    // \S 匹配任何非空白字符。等于[^ \f\n\r\t\v]
    @Test
    public void test9() {
        String str = "cat\r\n12cat 3";
        Pattern pattern = Pattern.compile("cat\\s");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // .  匹配除“\n”和"\r"之外的任何单个字符
    // [\\s\\S] 匹配包括“\n”和"\r"在内的任何字符
    @Test
    public void test10() {
        String str = "cat123\r\n\tabccat";
        Pattern pattern = Pattern.compile("cat.*cat");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        
        Pattern pattern2 = Pattern.compile("cat[\\s\\S]*cat");
        Matcher matcher2 = pattern2.matcher(str);
        while (matcher2.find()) {
            System.out.println(matcher2.group());
        }
    }
}
