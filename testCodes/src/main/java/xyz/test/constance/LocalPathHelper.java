package xyz.test.constance;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LocalPathHelper {

    public static Path projectRootPath() throws URISyntaxException {
        final URI uri = LocalPathHelper.class.getResource("/").toURI();
        /*
         上诉URI 应是指向 target/classes目录的 地址 。
         所以要得到项目的目录，则需要 获得其 上级 的上级目录
         */
        return Path.of(uri).getParent().getParent();
    }

    public static Path projectOutPutPath() throws URISyntaxException, IOException {
        final Path output = projectRootPath().resolve("output");
        if (!Files.exists(output)) {
            Files.createDirectory(output);
        }
        return output;
    }

//    public static Path projectResourcesPath() throws URISyntaxException, IOException {
//        final Path path = projectRootPath();
////        final Stream<String> lines = Files.lines(path);
////        lines.filter(line->line.equalsIgnoreCase("resources"))
////                .findFirst()
////                .orElseThrow(()->new IOException("resources directory not found"));
//        return path.resolve("resources");
//
//    }



    public static void main(String[] args) throws URISyntaxException, IOException {
        System.out.println("--------------------");
        final Path resourcePath = projectOutPutPath();
        System.out.println(resourcePath);
        if (!Files.exists(resourcePath)) {
            Files.createDirectory(resourcePath);
        }
        final Stream<Path> walk = Files.walk(resourcePath);

    }


}
