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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_store.BaseActivity;
import com.example.android_store.ChangeImageActivity;
import com.example.android_store.R;
import com.example.android_store.application.HomeApplication;
import com.example.android_store.constans.Urls;
import com.example.android_store.dtos.category.CategoryItemDTO;
import com.example.android_store.dtos.category.CategoryUpdateDTO;
import com.example.android_store.services.category.ApplicationNetwork;
import com.example.android_store.utils.CommonUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryUpdateActivity extends BaseActivity {

    int id = 0;
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
        setContentView(R.layout.activity_update);

        IVPreviewImage = findViewById(R.id.IVPreviewImage);

        txtCategoryName = findViewById(R.id.txtCategoryName);
        txtCategoryPriority = findViewById(R.id.txtCategoryPriority);
        txtCategoryDescription = findViewById(R.id.txtCategoryDescription);
        textImageError = findViewById(R.id.textImageError);

        txtFieldCategoryName = findViewById(R.id.txtFieldCategoryName);
        txtFieldCategoryPriority = findViewById(R.id.txtFieldCategoryPriority);
        txtFieldCategoryDescription = findViewById(R.id.txtFieldCategoryDescription);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            id = bundle.getInt("id");
        Toast.makeText(this, "Id:" + id, Toast.LENGTH_SHORT).show();
        initInput();
        setupError();
    }

    public void initInput() {
        CommonUtils.showLoading();
        ApplicationNetwork
                .getInstance()
                .getCategoryApi()
                .getById(id)
                .enqueue(new Callback<CategoryItemDTO>() {
                    @Override
                    public void onResponse(Call<CategoryItemDTO> call, Response<CategoryItemDTO> response) {
                        CommonUtils.hideLoading();
                        CategoryItemDTO categoryItemDTO = response.body();
                        txtCategoryName.setText(categoryItemDTO.getName());
                        txtCategoryPriority.setText(Integer.toString(categoryItemDTO.getPriority()));
                        txtCategoryDescription.setText(categoryItemDTO.getDescription());
                        String url = Urls.BASE + categoryItemDTO.getImage();
                        Glide.with(HomeApplication.getAppContext())
                                .load(url)
                                .apply(new RequestOptions().override(600))
                                .into(IVPreviewImage);
                    }

                    @Override
                    public void onFailure(Call<CategoryItemDTO> call, Throwable t) {

                    }
                });
    }

    public void handleUpdateCategoryClick(View view) {
        if (!validationForm()) {
            Toast.makeText(this, "You have to write all data !", Toast.LENGTH_LONG).show();
            return;
        }

        CategoryUpdateDTO model = new CategoryUpdateDTO();
        model.setId(id);
        model.setName(txtCategoryName.getText().toString());
        model.setPriority(Integer.parseInt(txtCategoryPriority.getText().toString()));
        model.setDescription(txtCategoryDescription.getText().toString());

        if (uri != null)
            model.setImageBase64(uriGetBase64(uri));

        ApplicationNetwork
                .getInstance()
                .getCategoryApi()
                .update(model)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent = new Intent(CategoryUpdateActivity.this, CatalogActivity.class);
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
            IVPreviewImage.setImageURI(uri);
        }
    }
}