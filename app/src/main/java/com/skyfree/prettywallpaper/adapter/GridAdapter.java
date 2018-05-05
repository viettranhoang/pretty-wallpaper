package com.skyfree.prettywallpaper.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.skyfree.prettywallpaper.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 4/4/2018.
 */

public class GridAdapter extends BaseAdapter{

    private List<String> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public GridAdapter(Context aContext,  List<String> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_grid, null);
            holder = new ViewHolder();
            holder.img = convertView.findViewById(R.id.item_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String img = this.listData.get(position);
        int imageId = this.getMipmapResIdByName(img);
        Picasso.get().load(imageId).into(holder.img);
//        holder.img.setImageResource(imageId);

        return convertView;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView img;
    }
}
