package com.example.otpscreen.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [OtpModule::class],
    dependencies = [AppComponent::class]
)
@OtpScope
interface OtpComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): OtpComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}
