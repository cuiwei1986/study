package com.cuiwei.share.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexDotOrTest {
    
    // .匹配任意字符
    @Test
    public void test1() {
        String str = "cat我";
        Pattern pattern = Pattern.compile("c.t.");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    // |多选结构。匹配任意子表达式,一般和括号结合限制范围
    // 和字符组[]区别,字符组只能匹配单个字符，多选结构可以匹配任意长度的文本
    @Test
    public void test2() {
        String str = "cat";
        Pattern pattern = Pattern.compile("c(a|e)t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    @Test
    public void test3() {
        String str = "cat";
        Pattern pattern = Pattern.compile("(ca|ce)t");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

}
