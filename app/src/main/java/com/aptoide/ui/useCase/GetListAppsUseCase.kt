package com.aptoide.ui.useCase

import com.aptoide.data.app.model.AppModelReponse
import com.aptoide.di.UseCaseDispatcher
import com.aptoide.repository.AppRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class GetListAppsUseCase @Inject constructor(
    private val appRepository: AppRepository,
    @UseCaseDispatcher private val dispatcher: CoroutineDispatcher

) {
    suspend operator fun invoke(): Response<AppModelReponse> = withContext(dispatcher){
        return@withContext appRepository.list()

    }

}
