package io.procrastination.design.view.toolbar

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import io.procrastination.design.extensions.setThrottledClickListener
import io.procrastination.design.R

class BonesToolbar : Toolbar {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) { handleAttributes(attrs) }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { handleAttributes(attrs) }

    private val txtTitle: TextView
    private val btnAction: ImageView

    init {
        setContentInsetsRelative(0, 0)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.view_toolbar, this, true)

        txtTitle = view.findViewById(R.id.txt_title)
        btnAction = view.findViewById(R.id.btn_back)
    }

    private fun handleAttributes(attrs: AttributeSet?) {
        if (attrs == null) return

        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.BonesToolbar,
                0,
                0
        ).apply {

            try {

                getString(R.styleable.BonesToolbar_headerTitle)?.let { txtTitle.text = it }
            } finally {
                recycle()
            }
        }
    }

    override fun setTitle(resId: Int) {
        txtTitle.text = context.getString(resId)
    }

    override fun setTitle(title: CharSequence?) {
        txtTitle.text = title
    }

    override fun setNavigationIcon(icon: Drawable?) {
        btnAction.setImageDrawable(icon)
    }

    override fun setNavigationIcon(resId: Int) {
        btnAction.setImageDrawable(context.getDrawable(resId))
    }

    override fun setNavigationOnClickListener(listener: OnClickListener?) {
        btnAction.setThrottledClickListener { listener?.onClick(it) }
    }

    companion object {

        @JvmStatic
        @BindingAdapter(value = ["title"])
        fun setTitle(view: BonesToolbar, title: String?) {
            view.title = title ?: ""
        }
    }
}