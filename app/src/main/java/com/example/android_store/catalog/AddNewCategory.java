package com.example.android_store.catalog;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_store.BaseActivity;
import com.example.android_store.ChangeImageActivity;
import com.example.android_store.R;
import com.example.android_store.dtos.category.CategoryCreateDTO;
import com.example.android_store.services.category.CategoryNetwork;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewCategory extends BaseActivity {

    int SELECT_CROPPER = 300;
    Uri uri = null;
    ImageView IVPreviewImage;

    TextInputEditText txtCategoryName;
    TextInputEditText txtCategoryPriority;
    TextInputEditText txtCategoryDescription;

    TextInputLayout txtFieldCategoryName;
    TextInputLayout txtFieldCategoryPriority;
    TextInputLayout txtFieldCategoryDescription;
    TextView textImageError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);

        IVPreviewImage = findViewById(R.id.IVPreviewImage);

        txtCategoryName = findViewById(R.id.txtCategoryName);
        txtCategoryPriority = findViewById(R.id.txtCategoryPriority);
        txtCategoryDescription = findViewById(R.id.txtCategoryDescription);
        textImageError = findViewById(R.id.textImageError);

        txtFieldCategoryName = findViewById(R.id.txtFieldCategoryName);
        txtFieldCategoryPriority = findViewById(R.id.txtFieldCategoryPriority);
        txtFieldCategoryDescription = findViewById(R.id.txtFieldCategoryDescription);

        setupError();
    }

    public void handleCreateCategoryClick(View view) {
        if (!validationForm()) {
            Toast.makeText(this, "You have to write all data !", Toast.LENGTH_LONG).show();
            return;
        }

        CategoryCreateDTO model = new CategoryCreateDTO();
        model.setName(txtCategoryName.getText().toString());
        model.setPriority(Integer.parseInt(txtCategoryPriority.getText().toString()));
        model.setDescription(txtCategoryDescription.getText().toString());
        model.setImagebase64(uriGetBase64(uri));

        CategoryNetwork
                .getInstance()
                .getJsonApi()
                .create(model)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent = new Intent(AddNewCategory.this, CatalogActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
    }

    private void setupError() {
        txtCategoryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty() || charSequence.length() <= 2) {
                    txtFieldCategoryName.setError(getString(R.string.category_name_required));
                    txtFieldCategoryName.setErrorEnabled(true);
                } else {
                    txtFieldCategoryName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtCategoryDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty() || charSequence.length() <= 2) {
                    txtFieldCategoryDescription.setError(getString(R.string.category_description_required));
                    txtFieldCategoryDescription.setErrorEnabled(true);
                } else {
                    txtFieldCategoryDescription.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtCategoryPriority.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int number = 0;
                try {
                    number = Integer.parseInt(txtCategoryPriority.getText().toString());
                } catch (Exception ex) {
                }

                if (number <= 0) {
                    txtFieldCategoryPriority.setError(getString(R.string.category_priority_required));
                    txtFieldCategoryPriority.setErrorEnabled(true);
                } else {
                    txtFieldCategoryPriority.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean validationForm() {
        boolean isValid = true;

        String name = txtCategoryName.getText().toString();
        if (name.isEmpty() || name.length() <= 2) {
            txtFieldCategoryName.setError(getString(R.string.category_name_required));
            isValid = false;
        }

        String description = txtCategoryDescription.getText().toString();
        if (description.isEmpty() || description.length() <= 2) {
            txtFieldCategoryDescription.setError(getString(R.string.category_description_required));
            isValid = false;
        }
        int number = 0;
        try {
            number = Integer.parseInt(txtCategoryPriority.getText().toString());
        } catch (Exception ex) {
        }

        if (number <= 0) {
            txtFieldCategoryPriority.setError(getString(R.string.category_priority_required));
            isValid = false;
        }
        if (uri == null) {
            textImageError.setVisibility(View.VISIBLE);
            isValid = false;
        }

        return isValid;
    }

    public void handleSelectImageClick(View view) {
        Intent intent = new Intent(this, ChangeImageActivity.class);
        startActivityForResult(intent, SELECT_CROPPER);
    }

    private String uriGetBase64(Uri uri) {
        try {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            byte[] byteArr = bytes.toByteArray();
            return Base64.encodeToString(byteArr, Base64.DEFAULT);

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SELECT_CROPPER) {
            uri = (Uri) data.getParcelableExtra("croppedUri");
            textImageError.setVisibility(View.INVISIBLE);
            IVPreviewImage.setImageURI(uri);
        }
    }
}