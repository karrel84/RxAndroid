package com.example.rxandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.rxandroid.databinding.ActivityMain2Binding;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;

public class Main2Activity extends Activity {

    ActivityMain2Binding mDataBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
//        mDataBind = DataBindingUtil.setContentView((Activity) this, R.layout.activity_main2);

        // 한번에 처리하는 컴바인 컴바인이 머지?
        testRxAndroid1();
    }

    /**
     * 한번에 처리하는 컴바인
     */
    private void testRxAndroid1() {
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        EditText editText1 = (EditText) findViewById(R.id.editText1);

        Observable<Boolean> checks1 = RxCompoundButton.checkedChanges(checkBox1);
        checks1.subscribe(check -> editText1.setEnabled(check));

//        Observable.combineLatest()
    }

    public static boolean isEmpty(CharSequence sequence) {
        return sequence.length() != 0;
    }
}
