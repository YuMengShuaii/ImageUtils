package com.enation.javashop.imagepluin.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
     * @return
     */
    @WorkerThread
    public static Bitmap getLoacalBitmap(String paramString)
    {
        Bitmap localBitmap;
        localBitmap = BitmapFactory.decodeFile(paramString);
        return localBitmap;
    }

    /**
     * Bitmap本地化
     * @param paramBitmap  需要本地化的BitMap
     * @param paramString  需要序列化的路径
     * @throws IOException IO异常
     */
    @WorkerThread
    public static void bitmapToSdCard(Bitmap paramBitmap, String paramString,ImageListener listener)
            throws IOException
    {
        File localFile1 = new File(paramString);
        if (!(localFile1.exists()))
            localFile1.mkdirs();
        File localFile2 = new File(paramString, System.currentTimeMillis() + ".jpg");
        FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
        paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localFileOutputStream);
        localFileOutputStream.flush();
        if (localFileOutputStream != null)
            localFileOutputStream.close();
        listener.success(localFile2);
    }
    interface ImageListener{
        void success(File path);
    }
}
