package com.alibaba.easyexcel.test.method;


import com.alibaba.easyexcel.test.util.TestFileUtil;
import com.alibaba.excel.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

public class MethodTest {


    /**
     * CS304 (manually written) Issue link: https://github.com/alibaba/easyexcel/issues/1840
     * Test method writeToFileNotCloseStream(File file, InputStream inputStream)
     * @throws IOException
     */
    @Test
    public void testMethodWriteToFileNotCloseStream1() throws IOException {
        File readTempFile = FileUtils.createCacheTmpFile();
        File tempFile = new File(readTempFile.getPath(), UUID.randomUUID().toString() + ".xlsx");
        String filePth = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        InputStream inputStream = new FileInputStream(filePth);
        FileUtils.writeToFileNotCloseStream(tempFile, inputStream);
        inputStream.read();
    }

    /**
     * CS304 (manually written) Issue link: https://github.com/alibaba/easyexcel/issues/1840
     * Test method writeToFileNotCloseStream(File file, InputStream inputStream)
     * @throws IOException
     */
    @Test
    public void testMethodWriteToFileNotCloseStream2() throws IOException {
        File readTempFile = FileUtils.createCacheTmpFile();
        File tempFile = new File(readTempFile.getPath(), UUID.randomUUID().toString() + ".xlsx");
        String filePth = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        InputStream inputStream = new FileInputStream(filePth);
        FileUtils.writeToFileNotCloseStream(tempFile, inputStream);
        inputStream.close();

        int buffer = 65535;
        byte[] origin = new byte[buffer];
        byte[] target = new byte[buffer];
        InputStream contentStream = new FileInputStream(filePth);
        int len1 = contentStream.read(origin);
        contentStream.close();

        InputStream targetStream = new FileInputStream(tempFile);
        int len2 = targetStream.read(target);
        targetStream.close();
        assert(len1==len2);
        for(int i=0;i<len1;++i){
            assert(origin[i] == target[i]);
        }
    }

}
