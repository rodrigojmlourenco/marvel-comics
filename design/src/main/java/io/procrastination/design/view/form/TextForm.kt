package io.procrastination.design.view.form

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import io.procrastination.design.extensions.handleAttributeSet
import io.procrastination.design.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * [Material Components - Text Fields](https://material.io/components/text-fields/)
 * [Android Material Components - Text Fields](https://material.io/develop/android/components/text-input-layout/)
 */
class TextForm : FrameLayout {

    private val valuePublisher: PublishSubject<String> = PublishSubject.create()

    private var isPassword: Boolean = false

    private val editText by lazy { findViewById<TextInputEditText>(R.id.text_input) }
    private val editTextParent by lazy { findViewById<TextInputLayout>(R.id.layout_text_input) }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        handleAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        handleAttributes(attrs)
    }

    var text: Editable?
        set(value) {
            editText.text = value
        }
        get() = editText.text

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.view_text_form, this, true)

        editText.addTextChangedListener(object : SimpleTextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                valuePublisher.onNext(s.toString())
            }
        })

        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                valuePublisher.onNext(editText.text.toString())
            }

            true
        }
    }

    fun setValue(value: CharSequence?) {
        this.editText.setText(value)
    }

    fun getValue(): String? {
        return this.text?.toString()
    }

    fun setError(error: CharSequence?) {
        editTextParent.error = error
    }

    fun clearState() {
        editTextParent.error = null
        editTextParent.errorIconDrawable = null
    }

    fun setSuccess() {
        editTextParent.isEndIconVisible = true
        editTextParent.endIconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_check_green)
    }

    fun observe(listener: Consumer<String>): Disposable {
        return valuePublisher
            .debounce(UX_DELAY, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(listener)
    }

    fun observe(listener: (String)-> Unit): Disposable {
        return observe(Consumer(listener))
    }

    override fun setOnClickListener(l: OnClickListener?) {
        if (editText.inputType == InputType.TYPE_NULL) {
            editText.isFocusable = false
            editText.isClickable = false
            editText.isFocusable = true
            editText.isClickable = true
        }

        editText.setOnClickListener {
            l?.onClick(it)
        }
    }

    private fun setInputType(type: Int) {

        editText.inputType = type

        val flag = type xor InputType.TYPE_CLASS_TEXT

        if (flag == InputType.TYPE_TEXT_VARIATION_PASSWORD ||
            flag == InputType.TYPE_NUMBER_VARIATION_PASSWORD ||
            flag == InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD ||
            flag == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        ) {

            isPassword = true
            editTextParent.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        } else {
            editTextParent.endIconMode = TextInputLayout.END_ICON_NONE
        }
    }

    private fun handleAttributes(attrs: AttributeSet?) {

        if (attrs == null) return

        context.handleAttributeSet(attrs, R.styleable.TextForm) { array, i ->
            when (val id = array.getIndex(i)) {

                R.styleable.TextForm_android_hint -> {
                    editTextParent.hint = array.getString(id)
                }

                R.styleable.TextForm_android_inputType -> {
                    setInputType(array.getInt(id, InputType.TYPE_CLASS_TEXT))
                }

                R.styleable.TextForm_android_imeOptions -> {
                    editText.imeOptions = array.getInt(id, 0)
                }

                R.styleable.TextForm_error -> {
                    setError(array.getString(id))
                }

                R.styleable.TextForm_success -> {
                    if(array.getBoolean(id, false)) setSuccess()
                }
            }
        }
    }

    companion object {
        private const val UX_DELAY = 350L
    }
}