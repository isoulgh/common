package org.isoul.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    /**
     * 读取文本文件内容
     *
     * @param filePath 文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     */
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileUtil.readToBuffer(sb, filePath);
        return sb.toString();
    }

    /**
     * 覆盖写文本文件
     *
     * @param filePath
     * @param content
     * @return
     * @throws IOException
     */
    public static boolean overwriteFile(String filePath, String content) throws IOException {
        PrintStream stream = null;
        try {
            stream = new PrintStream(filePath);//写入的文件path
            stream.print(content);//写入的字符串
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将文本文件中的内容读入到buffer中
     *
     * @param buffer   buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     */
    private static void readToBuffer(StringBuffer buffer, String filePath) {
        InputStream is = null;
        try {
            is = Files.newInputStream(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            line = reader.readLine(); // 读取第一行
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            try {
                line = reader.readLine(); // 读取下一行
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
