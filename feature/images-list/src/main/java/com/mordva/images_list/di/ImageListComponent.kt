package com.mordva.images_list.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [ImageListModule::class],
    dependencies = [ImageListDependency::class]
)
@ImageListScope
internal interface ImageListComponent {
    @Component.Factory
    interface Factory {
        fun create(dependency: ImageListDependency): ImageListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}