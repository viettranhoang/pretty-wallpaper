package com.skyfree.prettywallpaper;

import android.app.WallpaperManager;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
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

import java.io.ByteArrayOutputStream;
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

        int imageResource = getMipmapResIdByName("img_" + (mPager.getCurrentItem() + 1));
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
        int imageResource = getMipmapResIdByName("nen_img_" + (mPager.getCurrentItem() + 1));
        setPhotoContact(imageResource);

    }


    private void setPhotoContact(int imageResource) { // add , update contact using intent
        byte[] byteArrayImage = convertImageToByte(imageResource);

        ArrayList<ContentValues> data = new ArrayList<>();

        ContentValues row = new ContentValues();
        row.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE);
        row.put(ContactsContract.CommonDataKinds.Photo.PHOTO, byteArrayImage);
        data.add(row);

        Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT, ContactsContract.Contacts.CONTENT_URI);
        intent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
        intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, data);
        startActivity(intent);
    }

    private byte[] convertImageToByte(int imageResource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), imageResource); // your image

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }
}
