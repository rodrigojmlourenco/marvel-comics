package io.procrastination.design.view

import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

class ThrottleClickListener(onClickAction: (View) -> Unit) : View.OnClickListener {

    private val onClickPublisher: Subject<View> = PublishSubject.create()

    private val container = CompositeDisposable()

    init {
        onClickPublisher
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribeBy(
                onNext = onClickAction,
                onError = { throw it }
            )
            .addTo(container)
    }

    override fun onClick(v: View) {
        onClickPublisher.onNext(v)
    }
}