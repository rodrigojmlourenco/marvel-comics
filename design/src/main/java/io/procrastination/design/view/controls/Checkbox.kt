package io.procrastination.design.view.controls

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.checkbox.MaterialCheckBox
import io.procrastination.design.R

class Checkbox : MaterialCheckBox {

    constructor(context: Context?) : super(context, null, R.attr.checkboxStyle)

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs,
        R.attr.checkboxStyle
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}