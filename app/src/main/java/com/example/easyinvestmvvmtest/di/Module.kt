package com.example.easyinvestmvvmtest.di

import com.example.easyinvestmvvmtest.data.repository.Calculator.CalculatorRepository
import com.example.easyinvestmvvmtest.data.repository.CalculatorSimulateApi
import com.example.easyinvestmvvmtest.data.repository.Calculator.ICalculatorRepository
import com.example.easyinvestmvvmtest.ui.Simulate.SimulateViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val CALCULATOR_SIMULATE_BASE_URL = "https://api-simulator-calc.easynvest.com.br/"

val appModules = module {
    single {
        createWebService<CalculatorSimulateApi>(
            okHttpClient = createHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = CALCULATOR_SIMULATE_BASE_URL
        )
    }
    factory<ICalculatorRepository> {
        CalculatorRepository(
            api = get()
        )
    }
    viewModel { SimulateViewModel(calculatorRepository = get()) }

}

fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient, baseUrl: String, factory: CallAdapter.Factory
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}
