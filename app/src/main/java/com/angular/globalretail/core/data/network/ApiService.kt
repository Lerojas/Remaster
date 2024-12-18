package com.angular.globalretail.core.data.network

import com.angular.globalretail.core.data.model.SynchronizerData
import com.angular.globalretail.core.data.model.TableData
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/aplicacion/BusinessLogic/sincronizar/action_sincronizacion.php")
    suspend fun getTables(
        @Body sendUser: SynchronizerData?
    ) : TableData
}
