package com.hai.jedi.stackymaswaliflow.Utils;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

public class CustomLifeCycleOwner implements LifecycleOwner {

    private LifecycleRegistry lifecycleRegistry;

    public CustomLifeCycleOwner(){
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    public void stopListening(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    public void startListening(){
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle(){
        Log.d("CustomLifeCycleOwner", "Returning registry");
        return lifecycleRegistry;
    }
}
