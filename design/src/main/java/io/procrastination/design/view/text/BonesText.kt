package io.procrastination.design.view.text

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.StyleableRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.getColorOrThrow
import io.procrastination.design.view.ThrottleClickListener

abstract class BonesText : AppCompatTextView {

    @get:StyleableRes
    protected abstract val styleId: IntArray

    @get:StyleableRes
    protected abstract val textColorStyleId: Int

    @get:StyleableRes
    protected abstract val textBoldStyleId: Int

    @get:StyleableRes
    protected abstract val textSizeStyleId: Int

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    @Suppress("DEPRECATION")
    private fun init(attrs: AttributeSet?) {

        if (attrs == null) return

        context.theme.obtainStyledAttributes(
            attrs,
            styleId,
            0,
            0
        ).apply {

            try {
                updateColor(this@BonesText)
                updatePixelSize()
            } finally {
                recycle()
            }
        }
    }

    private fun TypedArray.updateColor(target: BonesText) {
        try {
            val color = getColorOrThrow(textColorStyleId)
            target.setTextColor(color)
        } catch (e: IllegalArgumentException) {
            // ignored
        }
    }

    private fun TypedArray.updatePixelSize() {
        val size = getDimensionPixelSize(textSizeStyleId, -1)
        if (size > 0) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, size.toFloat())
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(ThrottleClickListener { l?.onClick(it) })
    }
}