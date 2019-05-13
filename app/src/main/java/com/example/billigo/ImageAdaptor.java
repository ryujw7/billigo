package com.example.billigo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

    public class ImageAdaptor extends BaseAdapter {
        Context context = null;
        int[] btnimage = null;

        public ImageAdaptor(Context context, int[] btnimage) {
            this.context = context;
            this.btnimage = btnimage;
        }

    public int getCount() {
        return (null != btnimage) ? btnimage.length : 0;
    }


    public Object getItem(int position) {
        return (null != btnimage) ? btnimage[position] : 0;
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imgbutton = null;
        if (null != convertView) {
            imgbutton = (ImageView)convertView;
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),btnimage[position]);
            bitmap = Bitmap.createScaledBitmap(bitmap, 85, 85, false);
            //------------------------------------------------------------
            // 버튼을 생성하고 그것의 이름을 정합니다.
            imgbutton = new ImageView(context);
            imgbutton.setAdjustViewBounds(true);
            imgbutton.setImageBitmap(bitmap);
            //------------------------------------------------------------
            // 버튼 클릭에 대한 처리는 추후 구현 예정입니다.
        }
        return imgbutton;
    }

}
