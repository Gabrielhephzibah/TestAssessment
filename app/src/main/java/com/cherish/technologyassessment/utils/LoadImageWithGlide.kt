package com.cherish.technologyassessment.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.ObjectKey
import com.cherish.technologyassessment.di.GlideApp

object LoadImageWithGlide {
     fun loadImage (image: ImageView, url : String?) {
        GlideApp.with(image)
            .asDrawable()
            .override(200, 200)
            .centerCrop()
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .signature(ObjectKey(System.currentTimeMillis() / (1000 * 60 * 60 * 24)))
            .into(image)


    }
}