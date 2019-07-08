package com.study.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.study.binder.aidl.MyService;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";
    private IMyAidlInterface myInterface;
    private boolean isConnected;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnected = true;
            myInterface = IMyAidlInterface.Stub.asInterface(service);

            Log.i(TAG, "连接Service 成功");
            try {
                String s = myInterface.getInfor("我是Activity传来的字符串");
                Log.i(TAG, "从Service得到的字符串：" + s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "连接Service失败");
            isConnected = false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startAndBindService();
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected){
                    startAndBindService();
                    return;
                }
                if(myInterface == null){
                    return;
                }
                String s = null;
                try {
                    s = myInterface.getInfor("我是Activity传来的字符串");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "从Service得到的字符串：" + s);
            }
        });
    }
    private void startAndBindService() {
        Intent service = new Intent(MainActivity.this, MyService.class);
        //startService(service);
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
