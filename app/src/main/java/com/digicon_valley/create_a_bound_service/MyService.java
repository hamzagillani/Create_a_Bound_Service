package com.digicon_valley.create_a_bound_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    private final IBinder iBinder=new LocalService();


    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
    public class LocalService extends Binder{

        MyService getService(){

        return MyService.this;
        }


    }
    public String getFirstMessage(){


        return "Hello Welcome All";
    }

    public String getSecondMessage(){


        return "This is Bound Service Example ";
    }
}
