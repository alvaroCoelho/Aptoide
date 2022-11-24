package com.aptoide.ui.useCase

import com.aptoide.data.app.model.*
import com.aptoide.repository.AppRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response


internal class GetListAppsUseCaseTest {

    @MockK
    lateinit var repository: AppRepository

    private val dispatcher = StandardTestDispatcher()

    private lateinit var getListAppsUseCase: GetListAppsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getListAppsUseCase = GetListAppsUseCase(repository,dispatcher)

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke should return a Response with AppModelReponse`() {
        runTest(dispatcher) {

        coEvery { repository.list()  }  returns createAppModelReponse()

            val getListAppsUseCase = getListAppsUseCase.invoke()

            assertNotNull(getListAppsUseCase)

        }
    }

}

fun createAppModelReponse():Response<AppModelReponse> {
    val listApps = arrayListOf(
        AppModel(
            1, "name", "appPackageName", 1,
            "StoreName", "verName", 1, "md5",
            1, 1, 1, "added", "modified",
            "update", 1.1, "icon", "graphic", "uptime"
        ),
        AppModel(
            2, "name", "appPackageName", 2,
            "StoreName", "verName", 2, "md5",
            2, 2, 2, "added", "modified",
            "update", 2.1, "icon", "graphic", "uptime"
        )
    )

    val appModelListApps =
        AppModelReponse(ListApps(DataSet(AppModelAll(Data(AppModelList(listApps))))))

    return Response.success(appModelListApps)
}
