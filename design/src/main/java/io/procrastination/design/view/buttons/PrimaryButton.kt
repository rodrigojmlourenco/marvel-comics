package io.procrastination.design.view.buttons

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import io.procrastination.design.R
import io.procrastination.design.view.ThrottleClickListener

class PrimaryButton : MaterialButton {

    constructor(context: Context) : super(context, null, R.attr.primaryButtonStyle)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, R.attr.primaryButtonStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(ThrottleClickListener { l?.onClick(it) })
    }
}