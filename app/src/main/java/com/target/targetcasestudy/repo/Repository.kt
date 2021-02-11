package com.target.targetcasestudy.repo

import com.target.targetcasestudy.data.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun fetchProducts() = withContext(Dispatchers.IO){
        remoteDataSource.fetchProducts()
    }
    suspend fun fetchProduct(id:Int) = withContext(Dispatchers.IO){
        remoteDataSource.fetchProduct(id)
    }
}