package io.procrastination.design.view.text

import android.content.Context
import android.util.AttributeSet
import io.procrastination.design.R

class TextH4 : BonesText {

    override val styleId: IntArray
        get() = R.styleable.h4

    override val textColorStyleId: Int
        get() = R.styleable.h4_android_textColor

    override val textSizeStyleId: Int
        get() = R.styleable.h4_android_textSize

    override val textBoldStyleId: Int
        get() = R.styleable.h4_bolden

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.h4Style
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}