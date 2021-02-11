package com.target.targetcasestudy.data

import com.target.targetcasestudy.api.BaseDataSource
import com.target.targetcasestudy.api.Services


class RemoteDataSource(
    private val api: Services
) : BaseDataSource() {

    suspend fun fetchProducts() = getResult {
        api.getProducts()
    }

    suspend fun fetchProduct(id:Int) = getResult {
        api.getProduct(id)
    }

}