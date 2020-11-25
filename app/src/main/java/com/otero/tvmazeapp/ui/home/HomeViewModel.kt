package com.otero.tvmazeapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otero.tvmazeapp.domain.usecase.GetShowByPageUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getShowByPage: GetShowByPageUseCase
) : ViewModel() {

    init {
        Log.d("HomeViewModel", "INIT")
        viewModelScope.launch {
            Log.d("HomeViewModel", getShowByPage(1).data.toString())
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}