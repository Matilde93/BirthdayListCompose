package com.example.birthdaylistcompose


import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIService {

    @GET("persons")
    suspend fun getPersons(): List<Person>

    @POST("persons")
    suspend fun addPerson(@Body person: Person?): Person

    @DELETE("persons/{id}")
    suspend fun deletePerson(@Path("id") id: Int)

    @GET("persons/{id}")
    suspend fun getPersonById(@Path("id") id: Int) : Person

    @PUT("persons/{id}")
    suspend fun updatePerson(@Path("id") id:Int, @Body person: Person) : Person



    companion object{
        private const val BASE_URL = "https://birthdaysrest.azurewebsites.net/api/"
        private var apiService: APIService? = null
        fun getInstance(): APIService{
            if(apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}