package com.example.ec_geoowner;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;

import com.example.ec_geoowner.databinding.UpdatedialogBinding;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class ActivityScannerCam extends AppCompatActivity {

    private final int lensFacing = CameraSelector.LENS_FACING_BACK;
    private final int screenAspectRatio = AspectRatio.RATIO_4_3;
    private final CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(lensFacing).build();

    private String TAG = this.getClass().getSimpleName();
    private PreviewView previewView;
    private ProcessCameraProvider cameraProvider;
    private Preview previewUseCase;
    private ImageAnalysis analysisUseCase;
    private boolean scanComplete = false;


    public void setUpCamera() {
        new AppViewModel(getApplication())
                .getCameraProviderLiveData()
                .observe(
                        this,
                        processCameraProvider -> {
                            cameraProvider = processCameraProvider;
                            bindCameraUseCases();
                        }
                );
    }

    private void bindCameraUseCases() {
        bindPreviewUseCases();
        bindAnaylseUseCases();
    }

    private void bindPreviewUseCases() {
        if (previewUseCase != null) {
            cameraProvider.unbind(previewUseCase);
        }
        previewUseCase = new Preview.Builder()
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(previewView.getDisplay().getRotation())
                .build();
        previewUseCase
                .setSurfaceProvider(previewView.getSurfaceProvider());
        try {
            cameraProvider.bindToLifecycle(this, cameraSelector, previewUseCase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindAnaylseUseCases() {
        Log.d(TAG, "bindAnaylseUseCases called");
        if (analysisUseCase != null) {
            cameraProvider.unbind(analysisUseCase);
        }
        analysisUseCase = new ImageAnalysis.Builder()
                .setTargetAspectRatio(screenAspectRatio)
                .setTargetRotation(previewView.getDisplay().getRotation())
                .build();
        Log.d(TAG, "set analyzer called");
        if (analysisUseCase != null) {
            analysisUseCase.setAnalyzer(
                    Executors.newSingleThreadExecutor(),
                    image -> processImageProxy(BarcodeScanning.getClient(), image)
            );
        }
        try {
            cameraProvider.bindToLifecycle(this, cameraSelector, analysisUseCase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("UnsafeOptInUsageError")
    private void processImageProxy(BarcodeScanner client, ImageProxy image) {
        try {
            client.process(InputImage.fromMediaImage(Objects.requireNonNull(image.getImage()), image.getImageInfo().getRotationDegrees()))
                    .addOnSuccessListener(barcodes -> barcodes.forEach(new Consumer<Barcode>() {
                        @Override
                        public void accept(Barcode barcode) {
                            Log.d(TAG, "processImageProxy CALLED");
                            if (!scanComplete) {
                                try {
                                    Log.d(TAG, "VALUE RECEIVED - " + barcode.getRawValue());
                                    scanComplete = true;
                                    // TODO: 27-10-2022 extract values from number
                                    openDialog(barcode.getRawValue());
                                } catch (Exception e) {
                                    Log.e(TAG, e.getMessage() + "error occurred");
                                }
                            }

                        }
                    }))
                    .addOnFailureListener(e -> e.printStackTrace())
                    .addOnCompleteListener(task -> image.close());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openDialog(String rawValue) {
        Dialog dialog = new Dialog(this);
        UpdatedialogBinding dialogBinding = UpdatedialogBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
        dialogBinding.productName.setText(rawValue);
        dialogBinding.addBtn.setOnClickListener(view -> {
            // TODO: 28-10-2022 check if qty & discount is filled

            startActivity(new Intent(ActivityScannerCam.this, MainActivity.class));
        });
        dialogBinding.cancelBtn.setOnClickListener(view -> dialog.dismiss());

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_cam);
        previewView = findViewById(R.id.camera_preview);
        setUpCamera();
    }
}