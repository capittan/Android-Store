package com.example.android_store;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_store.account.LoginActivity;
import com.example.android_store.account.RegisterActivity;
import com.example.android_store.catalog.AddNewCategory;
import com.example.android_store.catalog.CatalogActivity;
import com.example.android_store.user.UserActivity;
import com.example.android_store.utils.CommonUtils;

public class BaseActivity extends AppCompatActivity {

    public BaseActivity(){
        CommonUtils.setContext(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.m_home:
                try {
                    intent = new Intent(BaseActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                break;

            case R.id.m_logIn:
                try {
                    intent = new Intent(BaseActivity.this, LoginActivity.class);
                    startActivity(intent);
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                break;

            case R.id.m_signUp:
                try {
                    intent = new Intent(BaseActivity.this, RegisterActivity.class);
                    startActivity(intent);
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                break;

            case R.id.m_catalog:
                try {
                    intent = new Intent(BaseActivity.this, CatalogActivity.class);
                    startActivity(intent);
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                break;

            case R.id.m_create:
                try {
                    intent = new Intent(BaseActivity.this, AddNewCategory.class);
                    startActivity(intent);
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                break;

            case R.id.m_user:
                try {
                    intent = new Intent(BaseActivity.this, UserActivity.class);
                    startActivity(intent);
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}