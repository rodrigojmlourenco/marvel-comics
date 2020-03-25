@file:Suppress("unused")

package io.procrastination.design.extensions

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StyleableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.use

object ViewExtensions

fun Drawable.toBipmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)

    return bitmap
}

val Int.dp
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

@ColorInt
fun Context.getColorCompat(@ColorRes colorId: Int): Int {
    return ContextCompat.getColor(this, colorId)
}

fun Context.handleAttributeSet(attrs: AttributeSet?, @StyleableRes style: IntArray, consumer: (TypedArray, Int) -> Unit) {
    theme.obtainStyledAttributes(attrs, style, 0, 0).use { array ->
        for (i in 0..array.indexCount)
            consumer(array, i)
    }
}