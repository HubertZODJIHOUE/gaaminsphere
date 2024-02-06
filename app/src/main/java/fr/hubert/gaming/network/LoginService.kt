package fr.hubert.gaming.network


import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


    interface LoginService {
        @FormUrlEncoded
        @POST("v1/user/login")
        suspend fun login(@Field("username") username: String, @Field("password") password: String): Response<ResponseBody>
    }
