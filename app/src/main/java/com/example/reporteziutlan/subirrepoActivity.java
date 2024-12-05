package com.example.reporteziutlan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class subirrepoActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE_REPORTE = 1;
    private static final int REQUEST_IMAGE_CAPTURE_UBICACION = 2;
    private static final int REQUEST_PERMISSIONS = 100;
    private int currentRequestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subirrepo);
        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnImagenReporte = findViewById(R.id.btn_imagen_reporte);
        Button btnImagenUbicacion = findViewById(R.id.btn_imagen_ubicacion);

        btnImagenReporte.setOnClickListener(v -> checkCameraPermissionAndOpenCamera(REQUEST_IMAGE_CAPTURE_REPORTE));
        btnImagenUbicacion.setOnClickListener(v -> checkCameraPermissionAndOpenCamera(REQUEST_IMAGE_CAPTURE_UBICACION));
    }

    private void checkCameraPermissionAndOpenCamera(int requestCode) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            currentRequestCode = requestCode;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSIONS);
        } else {
            openCamera(requestCode);
        }
    }

    private void openCamera(int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Aquí puedes manejar la imagen tomada dependiendo del botón que la activó
            if (requestCode == REQUEST_IMAGE_CAPTURE_REPORTE) {
                // Manejar la imagen del reporte
            } else if (requestCode == REQUEST_IMAGE_CAPTURE_UBICACION) {
                // Manejar la imagen de la ubicación
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera(currentRequestCode);
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
