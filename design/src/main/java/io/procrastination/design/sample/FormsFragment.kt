package io.procrastination.design.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.procrastination.design.R
import io.procrastination.design.extensions.createSnackbar
import io.procrastination.design.view.form.BonesSearchView
import io.procrastination.design.view.form.BoxedTextForm
import io.procrastination.design.view.form.TextForm
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class FormsFragment : Fragment() {

    private lateinit var inputBoxed: BoxedTextForm
    private lateinit var inputPasswordBoxed: BoxedTextForm
    private lateinit var inputForm: TextForm
    private lateinit var inputPasswordForm: TextForm
    private lateinit var searchInput: BonesSearchView

    private val container = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_material_forms, container, false).also { view ->
            inputBoxed = view.findViewById(R.id.input_boxed)
            inputPasswordBoxed = view.findViewById(R.id.input_boxed_password)
            inputForm = view.findViewById(R.id.input_form)
            inputPasswordForm = view.findViewById(R.id.input_form_password)
            searchInput = view.findViewById(R.id.input_bsearch)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputBoxed.observe { createSnackbar("'$it' in the boxed form.").show() }.addTo(container)
        inputPasswordBoxed.observe { createSnackbar("'$it' in the password boxed form.").show() }.addTo(container)
        inputForm.observe { createSnackbar("'$it' in the form.").show() }.addTo(container)
        inputForm.observe { createSnackbar("'$it' in the password form.").show() }.addTo(container)
        searchInput.onQuery { createSnackbar("Query changed to '$it'.").show() }.addTo(container)
        searchInput.onQuerySubmitted { createSnackbar("Query submitted").show() }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!container.isDisposed) container.dispose()
    }
}