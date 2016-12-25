package com.gyd.rookiepalmspace.modules.version;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckVersion {

    private Context context;

    public CheckVersion(Context context) {
        this.context = context;
    }


    public static File getFileFromServer(String path, ProgressDialog pd) {
        try {
            // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                // 获取到文件的大小
                pd.setMax(conn.getContentLength());
                InputStream is = conn.getInputStream();
                File file = new File(Environment.getExternalStorageDirectory()
                        + "/rookie/", "updata.apk");
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();

                FileOutputStream fos = new FileOutputStream(file);
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buffer = new byte[1024];
                int len;
                int total = 0;
                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    total += len;
                    // 获取当前下载量
                    if (pd.isShowing()) {
                        pd.setProgress(total);
                    } else {
                        //下载进度条被用户点击取消, 则取消下载,删除临时文件
                        if (file.exists()) {
                            file.delete();
                            file = null;
                        }
                        break;
                    }

                }

                fos.close();
                fos = null;

                bis.close();
                bis = null;

                is.close();
                is = null;

                return file;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
