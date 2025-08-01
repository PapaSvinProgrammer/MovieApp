package com.example.movieapp

import android.content.Context
import com.example.awardlist.di.AwardDependency
import com.example.collectionlist.di.CollectionDependency
import com.example.data.internal.di.DataModule
import com.example.home.di.HomeDependency
import com.example.movieScreen.di.MovieDependency
import com.example.movielistviewmodel.di.MovieListDependency
import com.example.network.internal.di.NetworkModule
import com.example.otpscreen.di.OtpDependency
import com.example.personlistviewmodel.di.PersonListDependency
import com.example.personscreen.di.PersonDependency
import com.example.room.internal.di.RoomModule
import com.example.search.searchScreen.di.SearchDependency
import com.example.security.internal.di.SecurityModule
import com.example.settings.di.SettingsDependency
import com.example.utils.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        NetworkModule::class,
        RoomModule::class,
        SecurityModule::class
    ]
)
@ApplicationScope
interface AppComponent :
    CollectionDependency,
    AwardDependency,
    HomeDependency,
    MovieListDependency,
    MovieDependency,
    OtpDependency,
    PersonDependency,
    PersonListDependency,
    SearchDependency,
    SettingsDependency {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
