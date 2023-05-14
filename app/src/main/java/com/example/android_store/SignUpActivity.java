package com.example.android_store;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    int SELECT_CROPPER = 300;
    Uri uri = null;
    ImageView IVPreviewImage;

    TextInputEditText txtFirtsname;
    TextInputEditText txtLastName;
    TextInputEditText txtCategoryDescription;

    TextInputLayout txtFieldCategoryName;
    TextInputLayout txtFieldCategoryPriority;
    TextInputLayout txtFieldCategoryDescription;
    TextView textImageError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        IVPreviewImage = findViewById(R.id.IVPreviewImage);

    }
    public void handleSelectImageClick(View view) {
        Intent intent = new Intent(this, ChangeImageActivity.class);
        startActivityForResult(intent, SELECT_CROPPER);
    }

}