package com.cuiwei.share.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexExampleTest {

    Pattern patternBlankTag = Pattern.compile("<([a-zA-Z]+)></\\1\\>");

    // data[-_]xxx去除
    @Test
    public void test0() {
        String body = "<div data-id=\"123\" id=\"123\">";
        Matcher matcherData = Pattern.compile(" data[-_][^=]*=\"[^\"]*\"").matcher(body);
        System.out.println(matcherData.replaceAll(""));
    }

    // 空标签去除
    @Test
    public void test1() {
        String body = "<a><d></d></a><B><c>123</c></B>";
        // 多次使用建议预编译 阿里推荐不要放在方法里
        Matcher matcherBlankTag = patternBlankTag.matcher(body);
        while (matcherBlankTag.reset(body).find()) {
            body = matcherBlankTag.replaceAll("");
        }
        System.out.println(body);
    }

    // 提取视频ID
    @Test
    public void test2() {
        String body = "<iframe id=11111 vid=22222>vid=33333</iframe>vid=44444";
        Matcher matcherVideo = Pattern.compile("<iframe[^>]+vid=([0-9a-zA-Z]+)[^<]+</iframe>").matcher(body);
        while (matcherVideo.find()) {
            System.out.println(matcherVideo.group(1));
        }
        System.out.println(matcherVideo.replaceAll("<video data-id=$1></video>"));
    }

    // IP地址
    @Test
    public void test3() {
        String ip = "192.168.1.1";
        Matcher matcher1 = Pattern.compile("([0-9]{1,3}\\.){3}[0-9]{1,3}").matcher(ip);
        System.out.println(matcher1.matches());

        Matcher matcher2 =
                Pattern.compile("(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])").matcher(ip);
        System.out.println(matcher2.matches());
    }

    // http url
    @Test
    public void test4() {
        String ip = "http://192.168.1.1:8080/index.jsp";
        // http://passport.mtime.com/findpwd
        Matcher matcher = Pattern.compile("http://([^:]+)(:\\d+)?(/.*)").matcher(ip);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2) == null ? ":80" : matcher.group(2));
            System.out.println(matcher.group(3));
        }
    }

    // 中文 [\u4e00-\u9fa5]
    @Test
    public void test5() {
        String chinese = "1一2二3三四4";
        Matcher matcher = Pattern.compile("[\u4e00-\u9fa5]+").matcher(chinese);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
