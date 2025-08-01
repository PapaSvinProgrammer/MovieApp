package com.example.otpscreen.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [OtpModule::class],
    dependencies = [OtpDependency::class]
)
@OtpScope
interface OtpComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: OtpDependency): OtpComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}
