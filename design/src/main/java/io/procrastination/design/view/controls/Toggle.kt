package io.procrastination.design.view.controls

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.switchmaterial.SwitchMaterial
import io.procrastination.design.R

class Toggle : SwitchMaterial {

    constructor(context: Context) : super(context, null, R.attr.toggleStyle)

    constructor(context: Context, attrs: AttributeSet?) : super(
        context,
        attrs,
        R.attr.toggleStyle
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {

        thumbTintList = ContextCompat.getColorStateList(
            context,
            R.color.selector_switch_thumb_tint
        )

        trackTintList = ContextCompat.getColorStateList(
            context,
            R.color.selector_switch_track_tint
        )
    }
}