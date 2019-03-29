package com.tachyon.pranjalchaudhari.spit;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

        Map<String,Object> defaultvalue = new HashMap<>();
        defaultvalue.put(UpdateHelper.KEY_UPDATE_ENABLE,false);
        defaultvalue.put(UpdateHelper.KEY_UPDATE_VERSION,"1.0.1");
        defaultvalue.put(UpdateHelper.KEY_UPDATE_URL,"https://drive.google.com/open?id=1_S1BkQvbi8LagdKr2ZABaWA0i49UN5ia");

        remoteConfig.setDefaults(defaultvalue);
        remoteConfig.fetch(5).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    remoteConfig.activateFetched();
                }
            }
        });
    }
}
