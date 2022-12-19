package com.example.numberfactstesttask.screens.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.GeneralResponse
import com.example.common.model.NumberData
import com.example.common.model.NumberFactsData
import com.example.common.util.EmptyFieldException
import com.example.common.util.Event
import com.example.common.util.NotNumberException
import com.example.domain.ApiFactsRepository
import com.example.domain.DatabaseFactsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val apiFactsRepository: ApiFactsRepository,
    private val databaseFactsRepository: DatabaseFactsRepository
) : ViewModel() {

    private val _getInfoEvent = MutableLiveData<Event<NumberFactsData>>()
    val getInfoEvent get() = _getInfoEvent

    private val _messageShowEvent = MutableLiveData<Event<String?>>()
    val messageShowEvent get() = _messageShowEvent

    fun addNumberFacts(numberFactsData: NumberFactsData) = viewModelScope.launch {
        databaseFactsRepository.addNumberFacts(numberFactsData)
    }

    fun getFactsByNumber(number: Int) = databaseFactsRepository.getFactsByNumber(number)

    fun getAllNumberFacts() = databaseFactsRepository.getAllNumberFacts()

    fun fetchFactsByNumber(numberData: NumberData) = viewModelScope.launch {


        try {
            when (val response = apiFactsRepository.fetchFactsByNumber(numberData)) {
                is GeneralResponse.Success -> {
                    val info = NumberFactsData(
                        id = 0,
                        number = numberData.number.toInt(),
                        fact = response.data!!
                    )
                    setInfo(info)
                }
                is GeneralResponse.Error -> {
                    showMessage(response.errorMessage)
                }
            }
        } catch (e: EmptyFieldException) {
            showMessage("Field is empty!")
        } catch (e: NotNumberException) {
            showMessage("Enter an integer number!")
        }
    }

    fun fetchFactsByRandomNumber() = viewModelScope.launch {
        when (val response = apiFactsRepository.fetchFactsByRandomNumber()) {
            is GeneralResponse.Success -> {

                val info = NumberFactsData(
                    id = 0,
                    number = getNumberFacts(response.data!!),
                    fact = response.data!!
                )

                setInfo(info)
            }
            is GeneralResponse.Error -> {
                showMessage(response.errorMessage)
            }
        }
    }

    private fun getNumberFacts(str: String): Int {
        var number = ""

        for (char in str.indices) {
            if (str[char] != ' ') {
                number += str[char]
            } else {
                break
            }
        }

        return number.toInt()
    }

    private fun showMessage(message: String?) {
        _messageShowEvent.value = Event(message)
    }

    private fun setInfo(info: NumberFactsData) {
        _getInfoEvent.value = Event(info)
    }
}