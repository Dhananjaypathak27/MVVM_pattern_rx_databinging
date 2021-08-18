package `in`.xparticle.mvvmpattern.network

import `in`.xparticle.mvvmpattern.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private val client:Retrofit
    get(){
        val interceptor = HttpLoggingInterceptor()
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = Int.MAX_VALUE
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(300L,TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .writeTimeout(300L,TimeUnit.SECONDS)
            .readTimeout(300L,TimeUnit.SECONDS).build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("https://abc.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val apiInterface: ApiInterface
    get() {
        return client.create(ApiInterface::class.java)
    }
}