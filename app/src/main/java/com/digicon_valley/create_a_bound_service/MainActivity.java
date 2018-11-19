package com.digicon_valley.create_a_bound_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    MyService myService;
    boolean isBind=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text_view);
        Intent intent=new Intent(this,MyService.class);
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    public void getFirstServiceMessage(View view) {

        String message;
        message=myService.getFirstMessage();
        textView.setText(message);
    }

    public void getSecondServiceMessage(View view) {

        String message;
        message=myService.getSecondMessage();
        textView.setText(message);
        isBind=true;
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MyService.LocalService localService=(MyService.LocalService)service;
       myService=localService.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBind=false;

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if (isBind){
            unbindService(serviceConnection);
            isBind=false;
        }
    }
}
