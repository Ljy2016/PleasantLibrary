package com.azadljy.pleasantlibrary.utils;

import java.io.File;

public class FileUtil {


    public static File CreateNewFile(String filePath,String fileName){
        if(filePath == null || filePath.length() == 0
                || fileName == null || fileName.length() == 0){
            return null;
        }
        try {
            //判断目录是否存在，如果不存在，递归创建目录
            File dir = new File(filePath);
            if(!dir.exists()){
                dir.mkdirs();
            }

            //组织文件路径
            StringBuilder sbFile = new StringBuilder(filePath);
            if(!filePath.endsWith("/")){
                sbFile.append("/");
            }
            sbFile.append(fileName);

            //创建文件并返回文件对象
            File file = new File(sbFile.toString());
            if(!file.exists()){
                file.createNewFile();
            }
            return file;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
