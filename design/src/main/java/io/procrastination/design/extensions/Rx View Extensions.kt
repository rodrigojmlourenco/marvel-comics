package io.procrastination.design.extensions

import android.view.View
import io.procrastination.design.view.ThrottleClickListener

fun View.setThrottledClickListener(listener: (View) -> Unit) {
    setOnClickListener(ThrottleClickListener(listener))
}