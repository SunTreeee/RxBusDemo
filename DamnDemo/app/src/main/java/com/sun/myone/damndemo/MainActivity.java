package com.sun.myone.damndemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btn_send)
    Button mBtnSend;
    @BindView(R.id.btn_intent)
    Button mBtnIntent;
    @BindView(R.id.tv_receive)
    TextView mTvReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RxBus.getInstance().toObservable().subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                if (o instanceof TestEvent){
                    Toast.makeText(MainActivity.this, "发送了广播", Toast.LENGTH_SHORT).show();
                    if (((TestEvent) o).getTest() != null) {
                        mTvReceive.setText(((TestEvent) o).getTest());
                    }
                }
            }
        });
    }

    @OnClick({R.id.btn_send, R.id.btn_intent})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                RxBus.getInstance().send(new TestEvent());
                break;
            case R.id.btn_intent:
                NextActivity.newInstance(this);
                break;
        }
    }
}
