package com.cuiwei.share.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexJavaTest {
    
    // 创建一个Pattern实例是昂贵的，因为它需要将正则表达式编译成一个有限状态机。
    //为了改善性能，我们可以将所需的正则表达式显式地编译进一个不可变的Pattern对象里
    private static final Pattern CA = Pattern.compile("ca");

    // matches 范围从开头到结束 相当于强制^...$
    @Test
    public void test1() {
        String str = "cat";
        System.out.println(str.matches("c[a-z]t"));
        // 等于
        Matcher matcher = Pattern.compile("^c[a-z]t$").matcher(str);
        System.out.println(matcher.find());
    }
    
    // 块转义:\Q \E \Q会抑制所有的元字符的含义 ,如果漏写了\E,那么从\Q开始之后的所有字符都将当作字面文本对待
    @Test
    public void test2() {
        System.out.println("*?".matches("\\Q*?\\E"));
        // 等于
        System.out.println("*?".matches("\\*\\?"));
    }

    // 查询匹配结果
    @Test
    public void test3() {
        String str = "catbca";
        Matcher matcher = CA.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.start());
            System.out.println(matcher.end());
        }
    }

    // 不区分大小写 Pattern.CASE_INSENSITIVE
    @Test
    public void test4() {
        String str = "catbCA";
        Matcher matcher = Pattern.compile("ca", Pattern.CASE_INSENSITIVE).matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test5() {
        String str = "catbCA";
        Matcher matcher = Pattern.compile("(?i)ca").matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // 替换 replaceAll replaceFirst
    @Test
    public void test6() {
        String str = "catcabcah";
        Matcher matcher = CA.matcher(str);
        System.out.println(matcher.replaceAll("re"));
        System.out.println(matcher.replaceFirst("re"));
    }

    @Test
    public void test7() {
        String str = "catbabcah";
        Matcher matcher = Pattern.compile("([a-z])a").matcher(str);
        System.out.println(matcher.replaceAll("$1e"));
        System.out.println(matcher.replaceFirst("$1e"));
    }

}
