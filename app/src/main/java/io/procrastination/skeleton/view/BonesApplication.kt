package io.procrastination.skeleton.view

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.procrastination.skeleton.di.DaggerBonesComponent
import timber.log.Timber
import javax.inject.Inject

class BonesApplication : android.app.Application(), HasAndroidInjector {

    @Inject
    lateinit var injector : DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = injector

    override fun onCreate() {
        super.onCreate()

        DaggerBonesComponent
            .builder()
            .setApp(this)
            .build()
            .inject(this)

        Timber.plant(Timber.DebugTree())
    }
}