package com.mordva.account.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.account.data.repository.UserAccountRepositoryImpl
import com.mordva.account.data.service.external.RefreshTokenService
import com.mordva.account.data.service.external.UserAccountService
import com.mordva.account.data.service.internal.RefreshTokenServiceImpl
import com.mordva.account.data.service.internal.UserAccountServiceImpl
import com.mordva.account.domain.repository.UserAccountRepository
import com.mordva.account.presentation.AccountViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface AccountModule {
    @Binds
    @AccountScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @AccountScope
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    fun bindsAccountViewModel(viewModel: AccountViewModel): ViewModel

    @Binds
    @AccountScope
    fun bindsUserAccountRepositoryImpl(repository: UserAccountRepositoryImpl): UserAccountRepository

    @Binds
    @AccountScope
    fun bindsUserAccountServiceImpl(service: UserAccountServiceImpl): UserAccountService

    @Binds
    @AccountScope
    fun bindsRefreshTokenServiceImpl(service: RefreshTokenServiceImpl): RefreshTokenService
}