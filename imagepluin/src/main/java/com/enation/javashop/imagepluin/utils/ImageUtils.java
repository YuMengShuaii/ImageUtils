package com.enation.javashop.imagepluin.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by LDD on 17/3/2.
 */

public class ImageUtils {

    /**
     * 获取本地图片Bitmap
     * @param paramString
     */
    public static void getLoacalBitmap(final String paramString, final ImageListener<Bitmap> listener)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap localBitmap = BitmapFactory.decodeFile(paramString);
                if (localBitmap == null){
                    listener.failed();
                }else{
                    listener.success(localBitmap);
                }
            }
        }).start();
    }

    /**
     * Bitmap本地化
     * @param paramBitmap  需要本地化的BitMap
     * @param paramString  需要序列化的路径
     */
    public static void bitmapToSdCard(final Bitmap paramBitmap, final String paramString, final ImageListener<File> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File localFile1 = new File(paramString);
                    if (!(localFile1.exists())) localFile1.mkdirs();
                    File localFile2 = new File(paramString, System.currentTimeMillis() + ".jpg");
                    FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
                    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localFileOutputStream);
                    localFileOutputStream.flush();
                    localFileOutputStream.close();
                    if (localFileOutputStream != null){
                        listener.success(localFile2);
                    }else{
                        listener.failed();
                    }
                } catch (IOException e) {
                    listener.failed();
                }
            }
        }).start();
    }
    interface ImageListener<T>{
        void success(T result);
        void failed();
    }
}
