package com.gabcletourneau.vapor.di

import android.app.Application
import com.gabcletourneau.vapor.data.RelativeHumiditySensor
import com.gabcletourneau.vapor.data.LightSensor
import com.gabcletourneau.vapor.data.ProximitySensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorsModule {
    @Provides
    @Singleton

    fun provideLightSensor(app: Application): MeasurableSensor {
        return LightSensor(app)
    }

    fun provideProximitySensor(app: Application): MeasurableSensor {
        return ProximitySensor(app)
    }

    fun provideRelativeHumiditySensor(app: Application): MeasurableSensor {
        return RelativeHumiditySensor(app)
    }
}

