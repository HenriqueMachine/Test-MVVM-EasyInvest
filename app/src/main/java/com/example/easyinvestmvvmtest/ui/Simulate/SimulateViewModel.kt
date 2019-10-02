package com.example.easyinvestmvvmtest.ui.Simulate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyinvestmvvmtest.data.repository.Calculator.ICalculatorRepository
import com.example.easyinvestmvvmtest.data.model.Result
import com.example.easyinvestmvvmtest.data.model.Investment
import com.example.easyinvestmvvmtest.data.model.SimulationResult
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SimulateViewModel(private val calculatorRepository: ICalculatorRepository) : ViewModel(),
    CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val calculatorResponse = MutableLiveData<SimulationResult>()
    private val error = MutableLiveData<String>()
    private val showLoading = MutableLiveData<Boolean>()

    fun calculator() = calculatorResponse as LiveData<SimulationResult>
    fun error() = error as LiveData<String>
    fun loading() = showLoading as LiveData<Boolean>


    fun simulation(investment: Investment) {
        showLoading.value = true

        launch {
            val result =
                withContext(Dispatchers.IO) { calculatorRepository.getSimulation(investment) }
            showLoading.value = false
            when (result) {
                is Result.Success -> calculatorResponse.value = result.data
                is Result.Error -> error.value = result.exception.message
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}