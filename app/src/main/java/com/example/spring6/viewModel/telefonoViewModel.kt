package com.example.spring6.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.spring6.models.Results
import com.example.spring6.models.telefono
import com.example.spring6.repository.telefonoRepository
import com.example.spring6.repository.telefonoRepositoryImp
import com.example.spring6.state.telefonoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class telefonoViewModel @Inject constructor(
    private val fonoRepo: telefonoRepository
): ViewModel() {

    var state by mutableStateOf(telefonoState())
        private set

    val fonos: Flow<List<telefono>> by lazy {
        fonoRepo.getProductsDb()
    }

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    fonoRepo.getAllProducts()

                }
            }
        }


    }
    fun getProductById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = fonoRepo.getProduct(id)
                state = state.copy(
                    telefono = telefono(result.id, result.name, result.price, result.image, result.description, result.lastPrice, result.credit)
                )
            }
        }
    }
    fun deleteFono(toDelete: telefono) {
        viewModelScope.launch(Dispatchers.IO) {
            fonoRepo.deleteTelefono(toDelete)
        }
    }
    fun clean() {
        state = state.copy(
            telefono = telefono()
        )
    }
}