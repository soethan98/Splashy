package com.example.soe_than.splashy.ui.ui.activity.photopreview

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
import android.support.v4.content.ContextCompat
import android.support.v7.preference.PreferenceManager
import android.widget.Toast
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.data.Vo.Urls
import com.example.soe_than.splashy.ui.ui.PhotoDetail.DetailFragment
import com.example.soe_than.splashy.ui.ui.activity.main.MainActivity


class PhotoPreview : AppCompatActivity() {

    lateinit var binding: ActivityPreviewBinding

//     var photo: Photo? = null

    lateinit var photoViewAttacher: PhotoViewAttacher
    var loadQual: String? = null
    var photoId:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_preview)

        val photoUrl = intent.getStringExtra("PHOTO_URL")
         photoId = intent.getStringExtra("PHOTO_ID")

        loadPhoto(photoUrl)
        binding.btnInfo.setOnClickListener {
            addFragment()
        }

        binding.btnExitPreview.setOnClickListener {
            this@PhotoPreview.finish()

        }


    }

    fun loadPhoto(urls: String){
        photoViewAttacher = PhotoViewAttacher(binding.previewImage)

        Glide.with(this)
                .load(urls)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        binding.previewProgress.visibility = View.GONE
                        binding.btnInfo.visibility = View.VISIBLE

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

    private fun addFragment() {

        val bottomSheetFragment = DetailFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)

        val bundle = Bundle()
        bundle.putString("photo_id", photoId)
        bottomSheetFragment.setArguments(bundle)
    }


}
