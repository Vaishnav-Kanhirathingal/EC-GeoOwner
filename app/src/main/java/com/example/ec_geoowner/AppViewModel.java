package com.example.ec_geoowner;

import android.app.Application;

import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.common.util.concurrent.ListenableFuture;

public class AppViewModel extends ViewModel {
    MutableLiveData<ProcessCameraProvider> cameraProviderLiveData;
    Application application;

    public AppViewModel(Application application) {
        this.application = application;
    }

    public MutableLiveData<ProcessCameraProvider> getCameraProviderLiveData() {
        if (cameraProviderLiveData == null) {
            cameraProviderLiveData = new MutableLiveData<>();
            ListenableFuture<ProcessCameraProvider> cameraProviderFurture = ProcessCameraProvider.getInstance(application);
            cameraProviderFurture.addListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        cameraProviderLiveData.setValue(cameraProviderFurture.get());
                    } catch (Exception e) {
                        System.out.println("Error-" + e);
                    }
                }
            }, ContextCompat.getMainExecutor(application));
        }
        return cameraProviderLiveData;
    }
}
