package com.uwjx.androidlauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 监听home键广播
        receiver = new HomeKeyEventBroadCastReceiver();
        registerReceiver(receiver, new IntentFilter(
                Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
    }

    // 监听HOME键
    HomeKeyEventBroadCastReceiver receiver;

    //拦截其他键
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        switch(keyCode){
            case KeyEvent.KEYCODE_HOME:
                Log.w("hugh" , "key code KEYCODE_HOME : 点击 HOME 键");
                return true;
            case KeyEvent.KEYCODE_BACK:
                Log.w("hugh" , "key code KEYCODE_BACK : 点击 KEYCODE_BACK 键");
                return true;
            case KeyEvent.KEYCODE_CALL:
                Log.w("hugh" , "key code KEYCODE_CALL : 点击 KEYCODE_CALL 键");
                return true;
            case KeyEvent.KEYCODE_SYM:
                Log.w("hugh" , "key code KEYCODE_SYM : 点击 KEYCODE_SYM 键");
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.w("hugh" , "key code KEYCODE_VOLUME_DOWN : 点击 KEYCODE_VOLUME_DOWN 键");
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                Log.w("hugh" , "key code KEYCODE_VOLUME_UP : 点击 KEYCODE_VOLUME_UP 键");
                return true;
            case KeyEvent.KEYCODE_STAR:
                Log.w("hugh" , "key code KEYCODE_STAR : 点击 KEYCODE_STAR 键");
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    class HomeKeyEventBroadCastReceiver extends BroadcastReceiver {
        static final String SYSTEM_REASON = "reason";
        static final String SYSTEM_HOME_KEY = "homekey";// home key
        static final String SYSTEM_RECENT_APPS = "recentapps";// long home key

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.w("hugh" , "onReceive action :  " + action);
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);

                if (reason != null) {
                    Log.w("hugh" , "onReceive reason :  " + reason);
                    if (reason.equals(SYSTEM_HOME_KEY)) {
                        // home key处理点
                        Log.e("homekey", "home键被点击");
                        Toast.makeText(MainActivity.this, "Home键被点击", Toast.LENGTH_SHORT).show();
                    } else if (reason.equals(SYSTEM_RECENT_APPS)) {
                        // long homekey处理点
                        Log.e("homekey", "长按home键");
                        Toast.makeText(MainActivity.this, "Home键长按", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.w("hugh" , "key code : " + keyCode);
        Log.w("hugh" , "key event : " + event.getAction());
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP){
            Log.w("hugh" , "key event : 点击返回键啦@@@@@@@@@@@@");
            return false;
        }
        return super.onKeyUp(keyCode, event);
    }
}