package com.example.rxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);

        // rx android
        testRxAndroid();

        // rx android sample2
        testRxAndroid2();

        // rx android sample3
        testRxAndroid3();

        // rx android sample4
        testRxAndroid4();

        // 단일 데이터, 컬렉션을 옵저버블 생성을 위한 유틸리티
        testRxAndroid5();
    }

    /**
     * 단일 데이터, 컬렉션을 옵저버블 생성을 위한 유틸리티
     */
    private void testRxAndroid5() {
        Observable<String> simpleObservable = Observable.just("Hello RxAndroid");
        simpleObservable.map(text -> text.length())
                .subscribe(lenth -> mTextView.setText("length: " + lenth));
    }

    /**
     * rx android sample4
     */
    private void testRxAndroid4() {
        Observable<String> simpleObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("asdfasdfasdfasdfasdf");
            }
        });

        simpleObservable.map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.length();
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer length) {
                mTextView.setText("length: " + length);
            }
        });

    }

    /**
     * rx android sample3
     */
    private void testRxAndroid3() {
        Observable<String> simpleObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("asdfasdfasdfasdfasdf");
            }
        });

        simpleObservable.map(new Func1<String, String>() {
            @Override
            public String call(String text) {
                return text.toUpperCase();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String text) {
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(text);
            }
        });
    }

    /**
     * rx android sample2
     */
    private void testRxAndroid2() {
        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        });

        stringObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {

            }
        });
    }

    /**
     * rx android
     */
    private void testRxAndroid() {
        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("헬로 RX안드로이드");
                subscriber.onNext("헬로 RX안드로이드1");
                subscriber.onNext("헬로 RX안드로이드2");
                subscriber.onCompleted();
            }
        });

        stringObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("MainActivity", String.format("onNext(%s)", s));
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(s);
            }
        });
    }
}

