package com.makeevrserg.feature_intents

import AndroidExtensions.launchActivity
import AndroidExtensions.launchActivityForResult
import BaseActivity
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.makeevrserg.feature_intents.databinding.ActivityImagePickerBinding

class ImagePickerActivity :
    BaseActivity<ActivityImagePickerBinding>(ActivityImagePickerBinding::inflate) {
    companion object {
        const val GET_IMAGE_CODE = 1
    }

    override fun setupObservables(binding: ActivityImagePickerBinding) {
        binding.bPickImage.setOnClickListener {
            launchActivityForResult(Intent.ACTION_GET_CONTENT, GET_IMAGE_CODE) {
                type = "image/*"
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) return
        when (requestCode) {
            GET_IMAGE_CODE -> {
                data?.data?.let { binding?.ivImage?.setImageURI(it) }
            }
        }
    }
}