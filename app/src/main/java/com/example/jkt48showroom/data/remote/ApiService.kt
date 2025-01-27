import com.example.jkt48showroom.data.model.Member
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/api/rooms/profile/allmember")
    fun getAllMember(): Call<List<Member>>

    @GET("api/rooms/profile/alltrainee")
    fun getAllTrainee(): Call<List<Member>>

    @GET("/api/rooms/profile/{main_name}")
    fun getMember(@Path("main_name") mainName: String): Call<Member>
}
