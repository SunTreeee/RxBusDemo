package com.sun.myone.damndemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sun
 * on 2017/3/6.
 */

public class NextActivity extends AppCompatActivity {
    @BindView(R.id.et_next)
    EditText mEtNext;
    @BindView(R.id.btn_return)
    Button mBtnReturn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_return)
    public void onClick() {
        if (mEtNext != null) {
            RxBus.getInstance().send(new TestEvent(mEtNext.getText().toString()));
            finish();
        }

    }
    public static void newInstance(Context context){
        context.startActivity(new Intent(context,NextActivity.class));
    }

}
