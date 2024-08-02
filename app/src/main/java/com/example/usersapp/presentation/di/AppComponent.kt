package com.example.usersapp.presentation.di

import android.content.Context
import com.example.usersapp.presentation.details.UserDetailsFragment
import com.example.usersapp.presentation.edit.UserEditFragment
import com.example.usersapp.presentation.list.UserListFragment
import com.example.usersapp.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DataModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: UserListFragment)
    fun inject(fragment: UserDetailsFragment)
    fun inject(fragment: UserEditFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun addContext(context: Context): Builder
        fun build(): AppComponent
    }
}