package com.example.catcat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catcat.data.CatRepository
import com.example.catcat.model.CatImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {
    private val repository = CatRepository()
    private val _cats = MutableStateFlow<List<CatImage>>(emptyList())
    val cats: StateFlow<List<CatImage>> = _cats

    init {
        loadMoreCats()
    }

    fun loadMoreCats() {
        viewModelScope.launch {
            _cats.value += repository.fetchCats()
        }
    }
}
