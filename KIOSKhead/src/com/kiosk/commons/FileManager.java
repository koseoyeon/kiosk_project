package com.kiosk.commons;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class FileManager {
   public String getExt(String path) {
      int lastIndex=path.lastIndexOf(".");
      return path.substring(lastIndex+1, path.length());
   }
   public String getFilename(File file,String dir) {
      long time=System.currentTimeMillis();
      String filename=time+"."+getExt(file.getName());
      boolean result=file.renameTo(new File(dir+"/"+filename));
      System.out.println("FileManager result°ª"+result);
      if(result==false) {
         filename=null;
      }
      return filename;
   }
   
   public String separateFile(String file) {
      int lastIndex=file.lastIndexOf("/");
      return file.substring(lastIndex+1, file.length());
   }
}