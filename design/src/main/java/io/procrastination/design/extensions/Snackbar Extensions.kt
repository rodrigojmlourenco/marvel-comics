@file:Suppress("unused")

package io.procrastination.design.extensions

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import io.procrastination.design.R
import timber.log.Timber

object SnackbarExtensions

fun Context.showSnackbar(view: View, message: String, @ColorInt color: Int? = null) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        .apply { if (color != null) this.view.setBackgroundColor(color) }
        .show()
}

fun Activity.createSnackbar(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT,
    @ColorInt color: Int? = null
): Snackbar {
    return Snackbar
        .make(findViewById<View>(android.R.id.content), message, length)
        .apply { if (color != null) this.view.setBackgroundColor(color) }
}

fun Fragment.createSnackbar(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT
) : Snackbar {
    return Snackbar.make(requireView(), message, length)
}

fun Snackbar.backgroundColor(@ColorRes color: Int): Snackbar {
    return apply { view.setBackgroundColor(view.context.getColorCompat(color)) }
}

fun Snackbar.asError(): Snackbar {
    return apply {
        setBackgroundTint(ContextCompat.getColor(context, R.color.colorError))
        setTextColor(ContextCompat.getColor(context, R.color.white))
    }
}

fun Snackbar.asSuccess(): Snackbar {
    return apply {
        setBackgroundTint(ContextCompat.getColor(context, R.color.colorPrimary))
        setTextColor(ContextCompat.getColor(context, R.color.white))
    }
}

fun Snackbar.elevation(elevation: Int): Snackbar {
    return apply { view.elevation = elevation.toFloat() }
}

fun Snackbar.action(description: String, @ColorRes color: Int? = null, action: () -> Unit): Snackbar {
    return apply {
        setAction(description) { action() }
        color?.let { setActionTextColor(view.context.getColorCompat(it)) }
    }
}

fun Snackbar.above(@IdRes id: Int): Snackbar {
    return apply {

        view.elevation = 0f

        (view.layoutParams as? CoordinatorLayout.LayoutParams)?.let {
            it.anchorId = id // Id for your bottomNavBar or TabLayout
            it.anchorGravity = Gravity.TOP
            it.gravity = Gravity.TOP
            view.layoutParams = it
        }
    }
}

fun Activity.showSnackbar(message: String, length: Int = Snackbar.LENGTH_SHORT, @ColorInt color: Int? = null) {
    try {
        Snackbar.make(findViewById<View>(android.R.id.content), message, length)
            .apply { if (color != null) this.view.setBackgroundColor(color) }
            .show()
    } catch (e: IllegalArgumentException) {
        Timber.e("Unable to render snackbar as view with id android.R.id.content is not suitable.")
    }
}