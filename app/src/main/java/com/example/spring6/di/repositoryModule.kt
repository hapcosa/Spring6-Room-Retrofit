package com.example.spring6.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.spring6.repository.telefonoRepository
import com.example.spring6.repository.telefonoRepositoryImp
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun telefonoRepository(repositoryImp: telefonoRepositoryImp): telefonoRepository
}