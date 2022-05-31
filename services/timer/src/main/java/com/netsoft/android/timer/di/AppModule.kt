package com.netsoft.android.timer.di

import android.content.Context
import com.ericktijerou.utils.common.DataManager
import com.ericktijerou.utils.common.PreferenceManager
import com.netsoft.android.timer.countdown.IntermittentTimerManager
import com.netsoft.android.timer.countdown.IntermittentTimerManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context) = PreferenceManager(context)

    @Singleton
    @Provides
    fun provideTimerManager(): IntermittentTimerManager {
        return IntermittentTimerManagerImpl()
    }


    @Singleton
    @Provides
    fun provideDataManager() = DataManager
}