package com.example.easyinvestmvvmtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SimulateViewModel(private val calculatorRepository: ICalculatorRepository) : ViewModel(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val calculatorResponse = MutableLiveData<Calculate>()
    val error = MutableLiveData<String>()
    val showLoading = MutableLiveData<Boolean>()

    fun simulation(investment: Investment){
        showLoading.value = true

        launch {
            val result = withContext(Dispatchers.IO) { calculatorRepository.getSimulation(investment) }
            // Hide progressBar once the operation is done on the MAIN (default) thread
            showLoading.value = false
            when (result) {
//                is Result.Success -> calculatorResponse.value = result.data
                is Result.Error -> error.value = result.exception.message
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}