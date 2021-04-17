package com.target.targetcasestudy.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DealListViewModel(
    private val repo: Repository
):ViewModel() {
    val products by lazy {
        liveData(Dispatchers.IO) {
            viewModelScope.launch(Dispatchers.IO) {

            }
            emit(repo.fetchProducts())
        }
    }
    suspend fun fetchProduct(id:Int) = repo.fetchProduct(id)
}