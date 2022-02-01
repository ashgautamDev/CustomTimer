package com.ashish.custometimer.di


import com.ashish.custometimer.persistance.CustomeTaskDao
import com.ashish.custometimer.ui.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        customeTaskDao: CustomeTaskDao
    ): MainRepository {
        return MainRepository(customeTaskDao)
    }
}