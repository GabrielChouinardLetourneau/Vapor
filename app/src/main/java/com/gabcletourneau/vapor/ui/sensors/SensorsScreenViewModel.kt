package com.gabcletourneau.vapor.ui.sensors

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gabcletourneau.vapor.di.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SensorsScreenViewModel @Inject constructor(
    private val lightSensor: MeasurableSensor,
    private val relativeHumiditySensor: MeasurableSensor,
    private val proximitySensor: MeasurableSensor,
    private val temperatureSensor: MeasurableSensor
): ViewModel() {
    var isDark by mutableStateOf(false)
    var currentLux by mutableFloatStateOf(0f)
    var proximity by mutableFloatStateOf(0f)
    var relativeHumidity by mutableFloatStateOf(0f)
    var temperature by mutableFloatStateOf(0f)

    init {
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            val lux = values[0]
            currentLux = lux
            isDark = lux < 60f
        }

        proximitySensor.startListening()
        proximitySensor.setOnSensorValuesChangedListener { values ->
            proximity = values[0]
        }

        relativeHumiditySensor.stopListening()
        relativeHumiditySensor.setOnSensorValuesChangedListener { values ->
            relativeHumidity = values[0]
        }

        temperatureSensor.stopListening()
        temperatureSensor.setOnSensorValuesChangedListener { values ->
            temperature = values[0]
        }
    }

    override fun onCleared() {
        super.onCleared()
        lightSensor.stopListening()
        proximitySensor.stopListening()
        relativeHumiditySensor.stopListening()
        temperatureSensor.stopListening()
    }
}