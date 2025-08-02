package xyz.test.domain;

import xyz.test.constance.LocalPathHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class WindowsProxyDomainExclude {

    public static String fileSourceName = "domainReRuleList.txt"; //文件名
    public static String fileReleaseName = "domainRuleListExclude.txt"; //输出文件名

    public static List<String> readOrInitSourceFile() throws URISyntaxException, IOException {
        final Path path = LocalPathHelper.projectOutPutPath();
        final Path proxyRules = path.resolve("proxyRules");
        if (Files.notExists(proxyRules)) {
            Files.createDirectory(proxyRules);
        }
        final Path resolve = proxyRules.resolve(fileSourceName);
        if (Files.notExists(resolve)) {
            Files.createFile(resolve);
        }
        return Files.lines(resolve).toList();
    }

    public static void addDomains(String... domain) throws URISyntaxException, IOException {
        final List<String> strings = readOrInitSourceFile();



    }

    public static void main(String[] args) {
        final String defaults ="localhost;127.*;10.*;172.16.*;172.17.*;172.18.*;172.19.*;172.20.*;172.21.*;172.22.*;172.23.*;172.24.*;172.25.*;172.26.*;172.27.*;172.28.*;172.29.*;172.30.*;172.31.*;192.168.*";
        final String s1 = """
                *.baidu.com;*.bdstatic.com;*.bing.com;*.cn;*.cn.bing.net;*.qq.com;*.taobao.com;*.jd.com;*gtimg.com;*.douyin.com;*.douyinstatic.com;*.csdn.net;*.mmstat.com;*.cibntv.net;*.ykimg.com;*.127.net;*.163.com;*.126.net;*.gitee.com;*.apifox.com;*.zhihu.com;zhimg.com;
                """;
        String s2 = """
        *.bytetos.com;*.qq.com;*.163.com;*.126.net;*.baidu.com;*.bdstatic.com;*.cn;*.taobao.com;*cdn.*;*.mmstat.com;*.tmall.*;*.jd.com;*.360buyimg.com;*.douyin.com;*.douyinstatic.com;*.zijieapi.com;*.douyinpic.com;*.douyinvod.com;*.ndcpp.com;*.bytefcdnrd.com;*.smtcdns.*;
        *.huoshanstatic.com;*.bytegoofy.com;*.bytescm.com;*.byteimg.com;*.bytedance.com;
        """;
        var s = s1+";" + s2;
        final String[] split = s.split(";");
        final List<String> list = Arrays.stream(split).distinct().sorted().filter(x -> x != null && x.trim().length() > 3).map(x->x.trim()).toList();

        list.forEach(System.out::println);
//        for (String string :( s1+s2).split(";")) {
//            System.out.println(string);
//        }

        String last = defaults;
        System.out.print(defaults);
        for (String string : list) {
            last+=";"+string;
            System.out.print(";"+string);
        }
        System.out.println();
        System.out.println("总计："+last.length()+"");
    }

}
