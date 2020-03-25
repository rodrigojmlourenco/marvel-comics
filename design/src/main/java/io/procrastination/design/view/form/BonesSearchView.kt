package io.procrastination.design.view.form

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.SearchView
import androidx.core.content.ContextCompat
import io.procrastination.design.extensions.setThrottledClickListener
import io.procrastination.design.R
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class BonesSearchView : FrameLayout {

    private var searchView: SearchView
    private var onQuerySubmitListener: (() -> Unit)? = null
    private val queryPublisher: PublishSubject<String> = PublishSubject.create()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.view_search_bar, this, true)

        searchView = view.findViewById(R.id.input_search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                queryPublisher.onNext(query ?: "")
                onQuerySubmitListener?.invoke()
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                queryPublisher.onNext(query ?: "")
                return true
            }
        })
    }

    override fun requestFocus(direction: Int, previouslyFocusedRect: Rect?): Boolean {
        return searchView.requestFocus(direction, previouslyFocusedRect)
    }

    override fun clearFocus() {
        searchView.clearFocus()
    }

    fun setClickOnly(action: () -> Unit) {
        findViewById<View>(R.id.view_focus_override).let {
            it.visibility = View.VISIBLE
            it.setThrottledClickListener { action() }
        }
    }

    fun onQuery(listener: (String) -> Unit): Disposable {
        return queryPublisher
            .distinctUntilChanged()
            .debounce(UX_DELAY, TimeUnit.MILLISECONDS)
            .subscribeBy(onNext = { listener(it) })
    }

    fun onQuerySubmitted(listener: () -> Unit) {
        this.onQuerySubmitListener = listener
    }

    fun setQuery(query: CharSequence) {
        this.searchView.setQuery(query, false)
    }

    companion object {
        private const val UX_DELAY = 350L
    }
}