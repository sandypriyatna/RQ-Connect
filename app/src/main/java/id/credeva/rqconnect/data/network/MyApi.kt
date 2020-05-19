package id.credeva.rqconnect.data.network

import id.credeva.rqconnect.BuildConfig
import id.credeva.rqconnect.RqconnectApplication
import id.credeva.rqconnect.data.network.responses.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.Headers
import java.util.concurrent.TimeUnit


interface MyApi {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("nis") nis: String,
        @Field("password") password: String
    ): Response<AuthResponse>

    @GET("article")
    suspend fun getArticle(): Response<ArticleResponse>

    @GET("bank")
    suspend fun getChosePayment(): Response<ChosePaymentResponse>

    @GET("progress/week")
    suspend fun getPekan(): Response<PekanResponse>

    @GET("progress/lajnah")
    suspend fun getLajnah(): Response<LajnahResponse>

    @GET("progress/quarter")
    suspend fun getTriwulan(): Response<TriwulanResponse>

    @GET("payment")
    suspend fun getPayment(): Response<PaymentResponse>

    @GET("album/image")
    suspend fun getGallery(): Response<GalleryResponse>

    @GET("gallery/image/{id}")
    suspend fun getDetailGallery(@Path("id") id: Int): Response<GalleryDetailResponse>

    @Multipart
    @POST("payment")
    suspend fun storePayment(
        @Part("id") id: RequestBody?,
        @Part evidence: MultipartBody.Part
    ): Response<ResponseBody>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl("https://rq-connect.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }

        private fun getOkHttpClient(): OkHttpClient {
            val timeOut = 60L
            return OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .addInterceptor(getInterceptor())
                .addInterceptor {
                    val req = it.request()
                        .newBuilder()
                        .addHeader(
                            "Authorization",
                            "Bearer " + RqconnectApplication.prefManager.spToken
                        )
                        .build()
                    return@addInterceptor it.proceed(req)
                }
                .build()
        }

        private fun getInterceptor(): Interceptor {
            return HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }
        }
    }
}