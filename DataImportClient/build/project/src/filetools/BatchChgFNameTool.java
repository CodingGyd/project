package filetools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 
 * 类名:  BatchChgFNameTool.java
 * 包名:  filetools
 * 描述:  修改文件名称
 * 
 * 作者:  guoyd
 * 日期:  2018年3月26日
 *
 * Copyright @ 2017 Corpration Name
 */
public class BatchChgFNameTool {
	
	/**
	 * 
	 * @param replaceContent 待替换的文件名称中的内容
	 * @param newReplaceName  替换后的新内容
	 * @param oldFileDir  资源文件夹
	 * @param newFileDir 目标文件夹
	 * @throws Exception
	 */
	public static void chgFileName(String replaceContent,String newReplaceName,String oldFileDir,String newFileDir) throws Exception{
		File curFile = new File(oldFileDir);
		if (!curFile.exists()){
			throw new FileNotFoundException("找不到资源文件"+oldFileDir);
		}
		
		//创建目标文件
		File targetFile = new File(newFileDir);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		DirectoryStream<Path> directoryStream;
		try {
		       directoryStream = Files.newDirectoryStream(curFile.toPath()); //returning a DirectoryStream to iterate over* all entries in the directory.
		       directoryStream.forEach(path -> {
 		               try {
		                   Files.copy(path, targetFile.toPath().resolve(path.getFileName().toString().replace(replaceContent, newReplaceName)), StandardCopyOption.REPLACE_EXISTING); // 重命名并复制到目标文件夹
		               } catch (IOException e) { // 因为在lambda表达式内，所以要包裹try catch
		                   e.printStackTrace();
		               }
		       });
		   } catch (IOException e) {
		       e.printStackTrace();
		   }
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			chgFileName("千象磐石26号私募证券投资基金委托资产", "FOF子基金7号", "C:\\Users\\guoyd\\Desktop\\估值表\\6-千象磐石26号", "E:\\6-千象磐石26号");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println((System.currentTimeMillis()-start )+"ms");
	}
	
}
