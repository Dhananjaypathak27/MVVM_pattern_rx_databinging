package `in`.xparticle.mvvmpattern.network

import `in`.xparticle.mvvmpattern.base.BaseResponse
import `in`.xparticle.mvvmpattern.model.ItemModel
import `in`.xparticle.mvvmpattern.model.MModel
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import kotlin.collections.HashMap

interface ApiInterface {

    @Headers("")
    @POST("")
    fun  getItem(
        @Header("") key:String,
        @Header("") auth:String,
        @Body body: HashMap<String,String>
    ):Observable<BaseResponse<ItemModel>>

    @Headers("")
    @POST("")
    fun getMM(
        @Body body: HashMap<String, String>
    ):Observable<MModel>
}