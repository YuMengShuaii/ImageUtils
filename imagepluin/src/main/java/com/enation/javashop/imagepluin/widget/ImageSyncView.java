package com.enation.javashop.imagepluin.widget;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.enation.javashop.imagepluin.R;

/**
 *  拥有加载图片功能的ImageView
 */

public class ImageSyncView extends ImageView {

    public ImageSyncView(Context context) {
        super(context);
    }

    public void syncImage(String url){
        Glide.with(getContext())
                .load(url)
                .thumbnail(0.1f)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .into(this);
    }
}
