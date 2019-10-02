package com.example.easyinvestmvvmtest.ui.Simulate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyinvestmvvmtest.data.model.CustomResponse
import com.example.easyinvestmvvmtest.data.model.Investment
import com.example.easyinvestmvvmtest.data.model.SimulationResult
import com.example.easyinvestmvvmtest.data.repository.Calculator.ICalculatorRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SimulateViewModel(private val calculatorRepository: ICalculatorRepository) : ViewModel(),
    CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val calculatorResponse = MutableLiveData<SimulationResult>()
    private val error = MutableLiveData<String>()
    private val success = MutableLiveData<Boolean>()
    private val showLoading = MutableLiveData<Boolean>()

    fun calculator() = calculatorResponse as LiveData<SimulationResult>
    fun error() = error as LiveData<String>
    fun success() = success as LiveData<Boolean>
    fun loading() = showLoading as LiveData<Boolean>

    fun simulation(investment: Investment) {
        showLoading.value = true

        launch {
            val result =
                withContext(Dispatchers.IO) { calculatorRepository.getSimulation(investment) }
            showLoading.value = false
            when (result) {
                is CustomResponse.Success -> {
                    calculatorResponse.value = result.data
                    success.value = true
                }
                is CustomResponse.Error -> {
                    success.value = false
                    error.value = result.exception.message
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}