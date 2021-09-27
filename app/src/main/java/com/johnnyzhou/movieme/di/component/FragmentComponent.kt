package com.johnnyzhou.movieme.di.component

import android.content.Context
import com.johnnyzhou.movieme.di.module.FragmentBindingModule
import com.johnnyzhou.movieme.di.module.FragmentModule
import com.johnnyzhou.movieme.di.scope.PerFragment
import com.johnnyzhou.movieme.ui.movie.list.MovieListFragment
import com.johnnyzhou.movieme.ui.person.list.PersonListFragment
import dagger.BindsInstance
import dagger.Component

@PerFragment
@Component(
    dependencies = [AppComponent::class],
    modules = [FragmentModule::class, FragmentBindingModule::class]
)
interface FragmentComponent {
    fun inject(fragment: MovieListFragment?)

    fun inject(fragment: PersonListFragment?)

//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance appComponent: AppComponent): FragmentComponent
//    }
}