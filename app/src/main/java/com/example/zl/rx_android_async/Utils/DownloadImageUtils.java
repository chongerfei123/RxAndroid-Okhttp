package com.example.zl.rx_android_async.Utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by zl on 2017/4/22.
 */

public class DownloadImageUtils {
    private OkHttpClient client;
    public Observable<byte[]> downloadImage(final String path){
        Observable<byte[]> observable = Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()){
                    client = new OkHttpClient();
                    Request request = new Request.Builder().url(path).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()){
                                byte[] data = response.body().bytes();
                                subscriber.onNext(data);
                            }
                            subscriber.onCompleted();
                        }
                    });
                }

            }
        });
        return observable;
    }
}
