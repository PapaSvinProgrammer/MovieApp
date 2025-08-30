package com.mordva.movieapp.di

import android.content.Context
import com.mordva.account.di.AccountDependency
import com.mordva.awardlist.di.AwardListDependency
import com.mordva.base_view_models.movie_list.MovieListDependency
import com.mordva.base_view_models.person_list.PersonListDependency
import com.mordva.collectionlist.di.CollectionDependency
import com.mordva.data.di.DataModule
import com.mordva.home.di.HomeDependency
import com.mordva.login.di.LoginDependency
import com.mordva.movieScreen.di.MovieDependency
import com.mordva.movieapp.main.MainActivity
import com.mordva.network.internal.di.NetworkModule
import com.mordva.otpscreen.di.OtpDependency
import com.mordva.personscreen.di.PersonDependency
import com.mordva.room.internal.di.RoomModule
import com.mordva.search.presentation.searchScreen.di.SearchDependency
import com.mordva.security.internal.di.SecurityModule
import com.mordva.settings.di.SettingsDependency
import com.mordva.util.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        NetworkModule::class,
        RoomModule::class,
        SecurityModule::class
    ]
)
@ApplicationScope
interface AppComponent :
    CollectionDependency,
    HomeDependency,
    AwardListDependency,
    MovieListDependency,
    MovieDependency,
    OtpDependency,
    PersonDependency,
    PersonListDependency,
    SearchDependency,
    SettingsDependency,
    LoginDependency,
    AccountDependency {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}