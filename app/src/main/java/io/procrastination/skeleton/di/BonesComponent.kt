package io.procrastination.skeleton.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import io.procrastination.skeleton.di.modules.ApplicationModule
import io.procrastination.skeleton.di.modules.NetworkModule
import io.procrastination.skeleton.view.MainActivity
import io.procrastination.skeleton.view.BonesApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        MainActivity.Module::class
    ]
)
abstract class BonesComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setApp(app: BonesApplication): Builder

        fun build(): BonesComponent
    }

    abstract fun inject(app: BonesApplication)
}