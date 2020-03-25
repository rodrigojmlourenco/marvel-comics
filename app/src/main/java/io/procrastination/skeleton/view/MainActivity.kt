package io.procrastination.skeleton.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.procrastination.skeleton.R
import io.procrastination.skeleton.view.home.HomeFragment
import io.procrastination.skeleton.view.splash.SplashFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = injector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @dagger.Module
    abstract class Module {
        @ContributesAndroidInjector(
            modules = [
                SplashFragment.Module::class,
                HomeFragment.Module::class
            ]
        )
        abstract fun bindMainActivity(): MainActivity
    }
}
