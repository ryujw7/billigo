package com.example.billigo;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CustomAdaptor extends PagerAdapter {
    LayoutInflater inflater;

    public CustomAdaptor(LayoutInflater inflater) {
        this.inflater = inflater;
    }
    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;

        view = inflater.inflate(R.layout.fragment_image,null);
        ImageView img = (ImageView)view.findViewById(R.id.imageView);

        img.setImageResource(R.drawable.drone_main_banner1+position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
