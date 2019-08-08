package com.example.rxjavatest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          test3();
          // 我的新提交
    }

    public void test3(){

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "accept: thread "+Thread.currentThread().getName() );
                Log.w(TAG, "accept: length"+integer );
            }
        };

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                 e.onNext(getResponse());
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String string) throws Exception {
                return string.length()+10;
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    public void test2() {
//      Observable<String>  observable = Observable.create(new ObservableOnSubscribe<String>() {
//          @Override
//          public void subscribe(ObservableEmitter<String> e) throws Exception {
//              Log.w(TAG, "subscribe: thread"+Thread.currentThread().getName() );
//              e.onNext(getResponse());
//          }
//      });

        Consumer consumer = o -> Log.w(TAG, "accept: thread" + Thread.currentThread().getName());
//        observable.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(consumer);

        Observable.create((ObservableOnSubscribe<String>) e -> {
            Log.w(TAG, "subscribe: thread" + Thread.currentThread().getName());
            e.onNext(getResponse());
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);


        Observable.just(1)
                .subscribeOn(Schedulers.io())
                .subscribe(integer->{
                    Log.w("TAG", "value = " + integer);
                    Log.w("TAG", "value = " + integer);
                }, throwable -> throwable.printStackTrace());

    }

    //使用okhttp访问网上提供的接口，由于是同步get请求，需要在子线程进行
    private String getResponse() {
        return "enene";
    }


    public void test1() {
        Observable<String> observable = Observable.create(e -> {
            e.onNext("我是");
            e.onNext("RxJava");
            e.onNext("简单示例");
            e.onError(new Throwable("error"));
            e.onComplete();
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.w(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.w(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.w(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.w(TAG, "onComplete: ");
            }
        };

        observable.subscribe(observer);
    }
}

