package com.example.hotel.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class FileUtil {
    public String fileupload(MultipartFile file){
        InputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream fos = null;
        BufferedOutputStream bos = null;
        int temp = 0;
        try {
            fis = file.getInputStream();
            String fileName = UUID.randomUUID()+file.getOriginalFilename();
            String des= "D:/BaiduYunDownload/springmvc/SpringMVC-01/代码/hotel/src/main/resources/static/uploads/"+fileName;
            fos = new FileOutputStream(des);
            //使用缓冲字节流包装文件字节流，增加缓冲功能，提高效率
            //缓存区的大小（缓存数组的长度）默认是8192，也可以自己指定大小
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            while ((temp = bis.read()) != -1) {
                bos.write(temp);

            }
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //注意：增加处理流后，注意流的关闭顺序！“后开的先关闭！”
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "上传失败";
    }
}
