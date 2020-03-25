package io.procrastination.design.view.text

import android.content.Context
import android.util.AttributeSet
import io.procrastination.design.R

class Paragraph : BonesText {

    override val styleId: IntArray
        get() = R.styleable.paragraph

    override val textColorStyleId: Int
        get() = R.styleable.paragraph_android_textColor

    override val textSizeStyleId: Int
        get() = R.styleable.paragraph_android_textSize

    override val textBoldStyleId: Int
        get() = R.styleable.paragraph_bolden

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.paragraphStyle
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}