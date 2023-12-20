package com.example.birthdaylistcompose

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

class PersonViewModel : ViewModel() {
    private val _personList = mutableStateListOf<Person>()
    var errorMessage: String by mutableStateOf("")
    val personList: List<Person>
        get() = _personList
    private val _person = mutableStateOf<Person?>(null)

    fun getPersonList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _personList.clear()
                _personList.addAll(apiService.getPersons())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    suspend fun getPersonById(id: String): Person? {
        return try {
            val apiService = APIService.getInstance()
            apiService.getPersonById(id.toInt())
        } catch (e: Exception) {
            // Handle the error (e.g., show an error message)
            Log.e("PersonViewModel", "Error getting person by ID", e)
            null
        }
    }

        fun addPerson(person: Person) {
            viewModelScope.launch {
                val apiService = APIService.getInstance()
                try {
                    val addedPerson = apiService.addPerson(person)
                    _personList.add(addedPerson)
                } catch (e: Exception) {
                    errorMessage = e.message.toString()
                }
            }
        }

    fun updatePerson(person: Person, id: Int) {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                apiService.updatePerson(id, person)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun deletePerson(id: Int) {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            //val person = apiService.getPersonById(id)
            try {
                apiService.deletePerson(id)
                //_personList.remove(person)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
    }