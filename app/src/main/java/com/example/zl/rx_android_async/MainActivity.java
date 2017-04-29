package com.example.zl.rx_android_async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zl.rx_android_async.Utils.DownloadImageUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
//使用这种方式代替异步任务
public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;
    private String path = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493476673&di=18db1754c1cd31b971f5ad4d1a18d0d9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg4q.duitang.com%2Fuploads%2Fitem%2F201212%2F02%2F20121202152935_nQY5C.jpeg";
    private DownloadImageUtils downloadImageUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
        downloadImageUtils = new DownloadImageUtils();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable<byte[]> observable = downloadImageUtils.downloadImage(path);
                //使用http获取数据
                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<byte[]>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }

    public class MyTask extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            return null;
        }
    }
}
