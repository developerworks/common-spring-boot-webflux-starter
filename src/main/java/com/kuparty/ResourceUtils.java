package com.kuparty;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResourceUtils {

    /**
     * 从 resources 目录中读取文件内容作为文本返回, 例如:
     *
     * <pre>
     * {@code ResourceUtils#getResource("application.yaml");}
     * </pre>
     *
     * @param path 相对于 resources 目录的相对路径
     * @return 文件内容
     */
    public static String getResource(String path) {
        String data = "";
        ClassPathResource cpr = new ClassPathResource(path);
        try {
            byte[] bytes = FileCopyUtils.copyToByteArray(cpr.getInputStream());
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
