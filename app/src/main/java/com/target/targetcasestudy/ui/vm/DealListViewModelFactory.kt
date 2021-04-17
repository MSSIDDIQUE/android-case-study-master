package com.target.targetcasestudy.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.repo.Repository

@Suppress("UNCHECKED_CAST")
class DealListViewModelFactory(
    private val repo: Repository
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DealListViewModel(repo) as T
    }
}