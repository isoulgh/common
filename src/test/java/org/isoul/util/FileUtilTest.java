package org.isoul.util;

import org.junit.Test;

import java.io.IOException;

public class FileUtilTest {

//    @Test
    public void aTest() throws IOException {
        String s = FileUtil.readFile("test.txt");
        System.out.println(s);
    }

//    @Test
    public void bTest() throws IOException {
        String s = "nihao";
        String file = "test.txt";
        FileUtil.overwriteFile(file, s);
    }
}
