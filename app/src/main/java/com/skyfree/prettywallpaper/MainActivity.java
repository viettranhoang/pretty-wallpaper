package com.skyfree.prettywallpaper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.skyfree.prettywallpaper.adapter.GridAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private List<String> listData;

    private final int RECORD_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();


        listData = getListData();
        gridView = findViewById(R.id.main_grid_view);
        gridView.setAdapter(new GridAdapter(this, listData));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ShowImageActivity.class);
                intent.putExtra("imageID", position);
                startActivity(intent);
            }
        });
    }

    private List<String> getListData() {
        List<String> lst = new ArrayList<>();

        for(int i = 1; i < 85; i++) {
            lst.add("nen_img_" + String.valueOf(i));
        }

        return lst;
    }

    private void requestPermissions() {
        int permission_write_contacts = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CONTACTS);
        if (permission_write_contacts != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_CONTACTS}, RECORD_REQUEST_CODE);
        }
    }
}
