package com.aptoide.repository

import com.aptoide.data.app.model.AppModelReponse
import com.aptoide.data.app.remote.ServiceApi
import retrofit2.Response
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val api: ServiceApi
) {
    suspend fun list(): Response<AppModelReponse> {
        return  api.listAllApps() }
    }

