package com.target.targetcasestudy.repo

import com.target.targetcasestudy.data.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun fetchProducts() = remoteDataSource.fetchProducts()
    suspend fun fetchProduct(id:Int) = remoteDataSource.fetchProduct(id)
}