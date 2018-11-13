package com.example.soe_than.splashy.ui.ui

import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.Nullable
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.request.RequestListener
import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.databinding.ActivityCollectionBinding
import com.example.soe_than.splashy.databinding.ActivityPreviewBinding
import com.github.chrisbanes.photoview.PhotoViewAttacher
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.target.Target


class PhotoPreview : AppCompatActivity() {

    var photoUrl: String? = null
    lateinit var binding: ActivityPreviewBinding

    lateinit var photoViewAttacher: PhotoViewAttacher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_preview)

        photoUrl = intent.getStringExtra("URL")


//        window.decorView.systemUiVisibility = (
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)



        photoViewAttacher = PhotoViewAttacher(binding.previewImage)

        Glide.with(this)
                .load(photoUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        binding.previewProgress.visibility = View.GONE
                        return false
                    }
                }
                )
                .thumbnail(0.1f)
                .into(binding.previewImage)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
