package com.aptoide.data.app.remote

import com.aptoide.data.app.model.AppModelReponse
import retrofit2.Response
import retrofit2.http.GET


interface ServiceApi {

    @GET("api_list/listApps")
    suspend fun listAllApps(): Response<AppModelReponse>

}

