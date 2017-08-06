package com.bwie.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyView.OnMyClickListener{
    MyView myView;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView  = (MyView)findViewById(R.id.myView);
        myView.setListener(this);
        toast = Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            quitToast();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void quitToast() {
        if (null == toast.getView().getParent()) {
            toast.show();
        } else {
            finish();
//            System.exit(0);
        }
    }

    @Override
    public void onCircleInnerClick(View var1) {
        Toast.makeText(this,"圆内",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCircleOuterClick(View var1) {
        Toast.makeText(this,"圆外角",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWriteClick(View var1) {
        Toast.makeText(this,"空白区域",Toast.LENGTH_SHORT).show();
    }
}
