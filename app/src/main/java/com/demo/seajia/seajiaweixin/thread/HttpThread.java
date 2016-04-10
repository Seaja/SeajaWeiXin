package com.demo.seajia.seajiaweixin.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by S.F.S on 2016/4/10.
 */
public class HttpThread extends Thread {

    ImageView imageView;

    String urlStr;
    Handler handler;

    public HttpThread(ImageView imageView, String urlStr,Handler handler) {
        this.imageView = imageView;
        this.urlStr = urlStr;
        this.handler=handler;


    }

    @Override
    public void run() {
        try {
            FileOutputStream out = null;
            File file=null;
            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");

            InputStream in = httpURLConnection.getInputStream();

            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                File fileDir = Environment.getExternalStorageDirectory();

                String fileName = String.valueOf(System.currentTimeMillis());
                file = new File(fileDir, fileName);
                out = new FileOutputStream(file);

            }
            byte[] bt = new byte[2 * 1024];
            int length;
            while ((length = in.read(bt)) > 0) {
                out.write(bt, 0, length);
            }
            in.close();
            out.close();


            final Bitmap bit = BitmapFactory.decodeFile(file.getAbsolutePath());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bit);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
