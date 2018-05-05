package com.skyfree.prettywallpaper.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.skyfree.prettywallpaper.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mListData;

    public ViewPagerAdapter(Context mContext, List<String> mListData) {
        this.mContext = mContext;
        this.mListData = mListData;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_pager, container, false);


        PhotoView photoView = itemView.findViewById(R.id.image_photo_view);
        int imageId = this.getMipmapResIdByName(mListData.get(position));
        Picasso.get().load(imageId).into(photoView);

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public int getMipmapResIdByName(String resName)  {
        String pkgName = mContext.getPackageName();
        int resID = mContext.getResources().getIdentifier(resName , "mipmap", pkgName);
        return resID;
    }
}
