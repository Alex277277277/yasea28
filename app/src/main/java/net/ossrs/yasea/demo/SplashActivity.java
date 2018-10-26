package net.ossrs.yasea.demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {


    private static final String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int RQUEST_CODE_CAMERA_PERMISSION = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!hasPermissions()) {
            requestPermissions();
        } else {
            routeToMainActivity();
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, permissions, RQUEST_CODE_CAMERA_PERMISSION);
    }

    private boolean hasPermissions() {
        for (int i = 0; i <= permissions.length - 1; i++) {
            if (ActivityCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                return false;
            };
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != RQUEST_CODE_CAMERA_PERMISSION) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (hasPermissions()) {
            routeToMainActivity();
        } else {
            Toast.makeText(this, "Required permissions haven't been granted", Toast.LENGTH_LONG).show();
            finish();
        }

    }

    private void routeToMainActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

}
