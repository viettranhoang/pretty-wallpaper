package com.skyfree.prettywallpaper;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
}
