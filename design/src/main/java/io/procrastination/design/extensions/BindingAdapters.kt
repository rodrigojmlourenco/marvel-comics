package io.procrastination.design.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

object BindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["drawableUrl", "placeholder", "centerCrop"], requireAll = false)
    fun setImageDrawableUrl(
        imageView: ImageView,
        url: String?,
        placeholder: Drawable?,
        centerCrop: Boolean = false
    ) {

        url?.let {
            Picasso.get()
                .load(url)
                .setPlaceholder(placeholder)
                .fitThenCenterCropIf(centerCrop)
                .into(imageView)
        }
    }

    private fun RequestCreator.setPlaceholder(placeholder: Drawable?) = apply {
        if (placeholder != null) this.placeholder(placeholder)
    }

    private fun RequestCreator.fitThenCenterCropIf(isCenterCrop: Boolean) = apply {
        if (isCenterCrop) fit().centerCrop()
    }
}