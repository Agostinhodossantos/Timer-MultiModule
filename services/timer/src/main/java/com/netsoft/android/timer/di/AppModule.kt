package com.netsoft.android.timer.di

import com.netsoft.android.timer.countdown.IntermittentTimerManager
import com.netsoft.android.timer.countdown.IntermittentTimerManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideTimerManager(): IntermittentTimerManager {
        return IntermittentTimerManagerImpl()
    }
}