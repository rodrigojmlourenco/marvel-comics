package io.procrastination.design.view.text

import android.content.Context
import android.util.AttributeSet
import io.procrastination.design.R

class TextH1 : BonesText {

    override val styleId: IntArray
        get() = R.styleable.h1

    override val textColorStyleId: Int
        get() = R.styleable.h1_android_textColor

    override val textSizeStyleId: Int
        get() = R.styleable.h1_android_textSize

    override val textBoldStyleId: Int
        get() = R.styleable.h1_bolden

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.h1Style
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}