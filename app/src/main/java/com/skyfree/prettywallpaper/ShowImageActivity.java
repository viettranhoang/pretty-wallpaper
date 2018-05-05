package com.skyfree.prettywallpaper;

import android.app.WallpaperManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.skyfree.prettywallpaper.adapter.ViewPagerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowImageActivity extends AppCompatActivity {

    private List<String> mListData;
    private ViewPagerAdapter mPagerAdapter;

    @BindView(R.id.view_pager)
    ViewPager mPager;

    @BindView(R.id.image_wallpaper)
    ImageView mImageWallpaper;

    @BindView(R.id.image_contact)
    ImageView mImageContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_image);
        ButterKnife.bind(this);

        mListData = getListData();

        mPagerAdapter = new ViewPagerAdapter(this, mListData);
        mPager.setAdapter(mPagerAdapter);

        int id = getIntent().getIntExtra("imageID", 0);
        mPager.setCurrentItem(id);
    }

    public int getMipmapResIdByName(String resName)  {
        String pkgName = getApplicationContext().getPackageName();
        int resID = getApplicationContext().getResources().getIdentifier(resName , "mipmap", pkgName);
        return resID;
    }

    private List<String> getListData() {
        List<String> lst = new ArrayList<>();

        for(int i = 1; i < 85; i++) {
            lst.add("img_" + String.valueOf(i));
        }

        return lst;
    }

    @OnClick(R.id.image_wallpaper)
    void onClickSetWallpaper(View view) {

        int imageResource = getMipmapResIdByName("img_" + mPager.getCurrentItem() + 1);
        WallpaperManager myWallpaperManager
                = WallpaperManager.getInstance(getApplicationContext());
        try {
            myWallpaperManager.setResource(imageResource);
            Toast.makeText(this, "Set wallpaper successful", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Set wallpaper error", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.image_contact)
    void onClickSetContact(View view) {

    }
}
