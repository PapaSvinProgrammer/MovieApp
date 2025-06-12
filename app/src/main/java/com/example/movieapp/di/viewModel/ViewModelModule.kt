package com.example.movieapp.di.viewModel

import androidx.lifecycle.ViewModel
import com.example.movieapp.MainViewModel
import com.example.movieapp.viewModels.AwardPersonListViewModel
import com.example.movieapp.viewModels.CollectionListViewModel
import com.example.movieapp.viewModels.EntryViewModel
import com.example.movieapp.viewModels.HomeViewModel
import com.example.movieapp.viewModels.MovieListViewModel
import com.example.movieapp.viewModels.PersonListViewModel
import com.example.movieapp.viewModels.PersonViewModel
import com.example.movieapp.viewModels.SearchResultViewModel
import com.example.movieapp.viewModels.SearchSettingsViewModel
import com.example.movieapp.viewModels.SearchViewModel
import com.example.movieapp.viewModels.SettingsViewModel
import com.example.movieapp.viewModels.StartViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EntryViewModel::class)
    fun bindEntryViewModel(viewModel: EntryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    fun bindStartViewModel(viewModel: StartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchSettingsViewModel::class)
    fun bindSearchSettingsViewModel(viewModel: SearchSettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchResultViewModel::class)
    fun bindSearchResultViewModel(viewModel: SearchResultViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CollectionListViewModel::class)
    fun bindCollectionLIstViewModel(viewModel: CollectionListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PersonListViewModel::class)
    fun bindPersonListViewModel(viewModel: PersonListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PersonViewModel::class)
    fun bindPersonViewModel(viewModel: PersonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AwardPersonListViewModel::class)
    fun bindAwardPersonListViewModel(viewModel: AwardPersonListViewModel): ViewModel
}