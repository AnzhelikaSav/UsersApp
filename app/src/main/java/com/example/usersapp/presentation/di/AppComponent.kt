package com.example.usersapp.presentation.di

import com.example.usersapp.presentation.list.UserListFragment
import com.example.usersapp.presentation.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: UserListFragment)
}