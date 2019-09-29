package com.example.easyinvestmvvmtest.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.easyinvestmvvmtest.data.model.BaseSimulation
import com.example.easyinvestmvvmtest.data.model.CustomResponse
import com.example.easyinvestmvvmtest.data.model.Investment
import com.example.easyinvestmvvmtest.data.model.SimulationResult
import com.example.easyinvestmvvmtest.data.repository.Calculator.ICalculatorRepository
import com.example.easyinvestmvvmtest.ui.Simulate.SimulateViewModel
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class CalculatorSimulateViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ICalculatorRepository
    @Mock
    private lateinit var simulateViewModel: SimulateViewModel

    private val calculatorResponse = mock<Observer<SimulationResult>>()

    private fun <T> anyObject(): T {
        return Mockito.anyObject<T>()
    }

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockitoAnnotations.initMocks(this)
        this.simulateViewModel = SimulateViewModel(this.repository)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should request limit with success`() {
        simulateViewModel.calculator().observeForever { calculatorResponse }

        runBlocking {
            `when`(repository.getSimulation(anyObject())).thenReturn(
                CustomResponse.Success(resultInvestMock)
            )
            simulateViewModel.simulation(investMock)
        }
        Assert.assertNotNull(simulateViewModel.calculator().value)
    }

}

val resultInvestMock = SimulationResult(
    BaseSimulation(
        0.0,
        0.0,
        0,
        0,
        "",
        0.0,
        false
    ),
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0
)

val investMock = Investment(
    1000.0,
    "CDI",
    123.0,
    false,
    "2023-03-03"
)