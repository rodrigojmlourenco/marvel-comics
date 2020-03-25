package io.procrastination.design.view.controls

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.radiobutton.MaterialRadioButton
import io.procrastination.design.R

class RadioButton : MaterialRadioButton {

    constructor(context: Context) : super(context, null, R.attr.radioButtonStyle)

    constructor(context: Context, attrs: AttributeSet?) : super(
        context,
        attrs,
        R.attr.radioButtonStyle
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}