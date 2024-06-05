package com.example.certificacionsense

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.certificacionsense.domain.MainUseCase
import com.example.certificacionsense.presentation.ui.detail.viewmodel.DetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var mainUseCase: MainUseCase

    @Mock
    private lateinit var context: Context

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp(){
        viewModel = DetailViewModel(mainUseCase)
    }

    @Test
    fun `getVideoGameById should update videoGameLiveData`() {

    }
}