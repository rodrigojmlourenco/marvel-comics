package io.procrastination.design.view.buttons

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import io.procrastination.design.view.ThrottleClickListener
import io.procrastination.design.R

class PhantomButton : MaterialButton {

    constructor(context: Context) : super(context, null, R.attr.phantomButtonStyle)

    constructor(context: Context, attrs: AttributeSet?) : super(
        context,
        attrs,
        R.attr.phantomButtonStyle
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(ThrottleClickListener { l?.onClick(it) })
    }
}