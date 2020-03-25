package io.procrastination.design.view.form

import android.text.Editable
import android.text.TextWatcher

interface SimpleTextWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        // Ignored
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // Ignored
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Ignored
    }
}