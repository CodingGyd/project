package com.codinggyd.jsch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPUtil {
	
	 /**
     * 连接ftp/sftp服务器
     * @param SFTP类
     */
    public static void getConnect(SFTP s,String host,Integer port,String username,String password) throws Exception {

                /** 密钥的密码  */ 
//      String privateKey ="key";
//        /** 密钥文件路径  */ 
//      String passphrase ="path";
        Session session = null;   
        Channel channel = null;   
        ChannelSftp sftp = null;// sftp操作类  

        JSch jsch = new JSch();   


        //设置密钥和密码  
        //支持密钥的方式登陆，只需在jsch.getSession之前设置一下密钥的相关信息就可以了  
//      if (privateKey != null && !"".equals(privateKey)) {  
//             if (passphrase != null && "".equals(passphrase)) {  
//              //设置带口令的密钥  
//                 jsch.addIdentity(privateKey, passphrase);  
//             } else {  
//              //设置不带口令的密钥  
//                 jsch.addIdentity(privateKey);  
//             }  
//      }  
        session = jsch.getSession(username, host, port);   
        session.setPassword(password);   
        Properties config = new Properties();   
        config.put("StrictHostKeyChecking", "no"); // 不验证 HostKey    
        session.setConfig(config);   
        try {
            session.connect();   
        } catch (Exception e) {
            if (session.isConnected())   
                session.disconnect();   
            throw new RuntimeException("连接服务器失败,请检查主机[" + host + "],端口[" + port   
                    + "],用户名[" + username + "],端口[" + port   
                    + "]是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");   
        }
        channel = session.openChannel("sftp");   
        try {
            channel.connect();   
        } catch (Exception e) {   
            if (channel.isConnected())   
                channel.disconnect();   
            throw new RuntimeException("连接服务器失败,请检查主机[" + host + "],端口[" + port   
                    + "],用户名[" + username + "],密码是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");   
        }
        sftp = (ChannelSftp) channel;   

        s.setChannel(channel);
        s.setSession(session);
        s.setSftp(sftp);

    }
    
    /**
     * 断开连接
     * 
     */
    public static void disConn(Session session,Channel channel,ChannelSftp sftp)throws Exception{
        if(null != sftp){
            sftp.disconnect();
            sftp.exit();
            sftp = null;
        }
        if(null != channel){
            channel.disconnect();
            channel = null;
        }
        if(null != session){
            session.disconnect();
            session = null;
        }
    }
    
    /**
     * 上传文件
     * @param directory 上传的目录-相对于SFPT设置的用户访问目录，
     * 为空则在SFTP设置的根目录进行创建文件（除设置了服务器全磁盘访问）
     * @param uploadFile 要上传的文件全路径
     */
    
    public static String upload(String fileSpeartor,String host,Integer port,String username,String password,String rootDirectory,String dateDirectory,String uploadFile) throws Exception {
    	if (StringUtils.isEmpty(fileSpeartor)) {
    		fileSpeartor = File.separator;
    	}
        SFTP s=new SFTP();
        getConnect(s,host,port,username,password);//建立连接
        Session session = s.getSession();   
        Channel channel = s.getChannel();   
        ChannelSftp sftp = s.getSftp();// sftp操作类  
        try {
            try{
                 sftp.cd(rootDirectory+"/"+dateDirectory); //进入目录
            }catch(SftpException sException){
                 if(sftp.SSH_FX_NO_SUCH_FILE == sException.id){ //指定上传路径不存在
                     sftp.mkdir(rootDirectory+fileSpeartor+dateDirectory);//创建目录
                     sftp.cd(rootDirectory+fileSpeartor+dateDirectory);  //进入目录
                 }
            }

            File file = new File(uploadFile);
            String uploadFileName = UUID.randomUUID().toString().replaceAll("-", "")+file.getName().substring(file.getName().lastIndexOf("."),file.getName().length());
            InputStream in= new FileInputStream(file);
            sftp.put(in, uploadFileName);
            in.close();
            return dateDirectory+fileSpeartor+uploadFileName;
        } catch (Exception e) {
            throw new Exception(e.getMessage(),e); 
        } finally {
            disConn(session,channel,sftp);
        }
    }
    
    /**
     * 上传文件
     * @param directory 上传的目录-相对于SFPT设置的用户访问目录，
     * 为空则在SFTP设置的根目录进行创建文件（除设置了服务器全磁盘访问）
     * @param uploadFile 要上传的文件对象
     */
    
    public static String upload2(String fileSpeartor,String host,Integer port,String username,String password,String rootDirectory,String dateDirectory,InputStream in,String filename) throws Exception {
    	if (StringUtils.isEmpty(fileSpeartor)) {
    		fileSpeartor = File.separator;
    	}
        SFTP s=new SFTP();
        getConnect(s,host,port,username,password);//建立连接
        Session session = s.getSession();   
        Channel channel = s.getChannel();   
        ChannelSftp sftp = s.getSftp();// sftp操作类  
        try {
            try{
                 sftp.cd(rootDirectory+"/"+dateDirectory); //进入目录
            }catch(SftpException sException){
                 if(sftp.SSH_FX_NO_SUCH_FILE == sException.id){ //指定上传路径不存在
                     sftp.mkdir(rootDirectory+fileSpeartor+dateDirectory);//创建目录
                     sftp.cd(rootDirectory+fileSpeartor+dateDirectory);  //进入目录
                 }
            }

            String uploadFileName = UUID.randomUUID().toString().replaceAll("-", "")+filename.substring(filename.lastIndexOf("."),filename.length());
            sftp.put(in, uploadFileName);
            in.close();
            return dateDirectory+fileSpeartor+uploadFileName;
        } catch (Exception e) {
            throw new Exception(e.getMessage(),e); 
        } finally {
            disConn(session,channel,sftp);
        }
    }
    
    /**
     * 下载文件
     * @param directory 下载目录 根据SFTP设置的根目录来进行传入
     * @param downloadFile  下载的文件 
     * @param saveFile  存在本地的路径 
     */
    public static void download(String host,Integer port,String username,String password,String directory, String downloadFile,String saveFile) throws Exception {
        SFTP s=new SFTP();
        getConnect(s, host, port, username, password);//建立连接
        Session session = s.getSession();   
        Channel channel = s.getChannel();   
        ChannelSftp sftp = s.getSftp();// sftp操作类  
        try {

            sftp.cd(directory); //进入目录
            File file = new File(saveFile);
            boolean bFile;
            bFile = false;
            bFile = file.exists();
            if (!bFile) {
                bFile = file.mkdirs();//创建目录
            }
            OutputStream out=new FileOutputStream(new File(saveFile,downloadFile));

            sftp.get(downloadFile, out);

            out.flush();
            out.close();

        } catch (Exception e) {
            throw new Exception(e.getMessage(),e); 
        } finally {
            disConn(session,channel,sftp);
        }
    }
    
    /**
     * 删除文件
     * @param directory 要删除文件所在目录 
     * @param deleteFile 要删除的文件
     */
    public static void delete(String host,Integer port,String username,String password,String directory, String deleteFile) throws Exception {
        SFTP s=new SFTP();
        getConnect(s, host, port, username, password);//建立连接
        Session session = s.getSession();   
        Channel channel = s.getChannel();   
        ChannelSftp sftp = s.getSftp();// sftp操作类  
        try {
            sftp.cd(directory); //进入的目录应该是要删除的目录的上一级
            sftp.rm(deleteFile);//删除目录
        } catch (Exception e) {
            throw new Exception(e.getMessage(),e); 
        } finally {
            disConn(session,channel,sftp);
        }
    }
    
    /** 
     * 列出目录下的文件 
     * @param directory  要列出的目录            
     * @return list 文件名列表 
     * @throws Exception 
     */ 
     public static List<String> listFiles(String host,Integer port,String username,String password,String directory) throws Exception { 
         SFTP s=new SFTP();
         getConnect(s, host, port, username, password);//建立连接
         Session session = s.getSession();   
         Channel channel = s.getChannel();   
         ChannelSftp sftp = s.getSftp();// sftp操作类  
         Vector fileList=null; 
         List<String> fileNameList = new ArrayList<String>(); 
         fileList = sftp.ls(directory); //返回目录下所有文件名称
         disConn(session,channel,sftp);

         Iterator it = fileList.iterator(); 

         while(it.hasNext()) { 

             String fileName = ((LsEntry)it.next()).getFilename(); 
             if(".".equals(fileName) || "..".equals(fileName)){ 
                 continue; 
              } 
             fileNameList.add(fileName); 

         } 

         return fileNameList; 
     } 
     
     /**
      * 删除目录下所有文件
      * @param directory 要删除文件所在目录 
      */
     public static void deleteAllFile(String host,Integer port,String username,String password,String directory) throws Exception{
         SFTP s=new SFTP();
         getConnect(s, host, port, username, password);//建立连接
         Session session = s.getSession();   
         Channel channel = s.getChannel();   
         ChannelSftp sftp = s.getSftp();// sftp操作类     
         try {
             List <String> files=listFiles(host,port,username,password,directory);//返回目录下所有文件名称
             sftp.cd(directory); //进入目录

             for (String deleteFile : files) {
                 sftp.rm(deleteFile);//循环一次删除目录下的文件
             }
         } catch (Exception e) {
             throw new Exception(e.getMessage(),e); 
         } finally {
             disConn(session,channel,sftp);
         }

     }
     
     /**
      * 删除目录 (删除的目录必须为空)
      * @param deleteDir 要删除的目录 
      */
     public static void deleteDir(String host,Integer port,String username,String password,String deleteDir) throws Exception {
         SFTP s=new SFTP();
         getConnect(s, host, port, username, password);//建立连接
         Session session = s.getSession();   
         Channel channel = s.getChannel();   
         ChannelSftp sftp = s.getSftp();// sftp操作类   
         try {

             sftp.rmdir(deleteDir);

         } catch (Exception e) {
             throw new Exception(e.getMessage(),e); 
         } finally {
             disConn(session,channel,sftp);
         }
     }
     
     /**
      * 创建目录 
      * @param directory 要创建的目录 位置
      * @param dir 要创建的目录 
      */
        public static void creatDir(String host,Integer port,String username,String password,String directory,String dir) throws Exception {
            SFTP s=new SFTP();
            getConnect(s, host, port, username, password);//建立连接
            Session session = s.getSession();   
            Channel channel = s.getChannel();   
            ChannelSftp sftp = s.getSftp();// sftp操作类  
            try {
                sftp.cd(directory); 
                sftp.mkdir(dir);
            } catch (Exception e) {
                throw new Exception(e.getMessage(),e); 
            } finally {
                disConn(session,channel,sftp);
            }
       }
        /** 
         * 更改文件名 
         * @param directory  文件所在目录 
         * @param oldFileNm  原文件名 
         * @param newFileNm 新文件名       
         * @throws Exception      
         */ 
         public static void rename(String host,Integer port,String username,String password,String directory, String oldFileNm, String newFileNm) throws Exception { 
             SFTP s=new SFTP();
             getConnect(s, host, port, username, password);//建立连接
             Session session = s.getSession();   
             Channel channel = s.getChannel();   
             ChannelSftp sftp = s.getSftp();// sftp操作类    
             try {
                 sftp.cd(directory); 
                 sftp.rename(oldFileNm, newFileNm); 
             } catch (Exception e) {
                 throw new Exception(e.getMessage(),e); 
             } finally {
                 disConn(session,channel,sftp);
             }
         } 
         
         /**
          * 进入目录
          * @param directory
          * @throws Exception
          */
         public static void cd(String host,Integer port,String username,String password,String directory)throws Exception { 

             SFTP s=new SFTP();
             getConnect(s, host, port, username, password);//建立连接
             Session session = s.getSession();   
             Channel channel = s.getChannel();   
             ChannelSftp sftp = s.getSftp();// sftp操作类   
             try {
                 sftp.cd(directory); //目录要一级一级进
             } catch (Exception e) {
                 throw new Exception(e.getMessage(),e); 
             } finally {
                 disConn(session,channel,sftp);
             }
         } 
         
         public static void main(String[] args) throws Exception {
     		try {  
      	        String name = SFTPUtil.upload("/","47.111.1.180",22,"guoyd","guoyd","/home/guoyd/blog","20190224","E:\\personal_workspace\\git_code\\project\\mine-client\\mine-client\\target\\classes\\static\\images\\my.jpg");  
      	        System.out.println(name);
     		} catch (FileNotFoundException e) {  
     	        e.printStackTrace();  
     	    }  
     	}
}
