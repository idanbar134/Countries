package com.crazyworld.countries.common

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.crazyworld.countries.common.svg.SvgSoftwareLayerSetter


fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.load(url: String) =
        Glide.with(this)
                 .`as`(PictureDrawable::class.java)
                .apply(RequestOptions().centerCrop())
                .transition(withCrossFade())
                .listener(SvgSoftwareLayerSetter())
                .load(Uri.parse(url))
                .into(this)


