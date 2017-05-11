package lyp.bawei.com.retrofit1;



import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IUserBiz {
    /**
     * 无参get请求
     */
    @GET("?city_id=14&lat=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109&_chat_id=0&qtime=20160411091603")
    Call<Bean> getUsers();
    /**
     * 有参 get请求
     * http://v.juhe.cn/toutiao/index?type=shehui&key=32b9973df2e6ee0c2bf094b61c7d7844
     */
    @GET("toutiao/{index}?")
    Call<Bean2> getArgumentUsere(@Path("index") String index, @Query("type") String type,@Query("key") String key);
/**
 * http://169.254.217.5:8080/"bullking1/login

 * post方式实现登录
 */
@POST("login")
@FormUrlEncoded
Call<Bean3> login(@Field("name") String name, @Field("pwd") String pwd);
    /**
     * http://192.168.0.104:8080/08web/FileUploadServlet
     * post
     * 方式实现上传头像
     */
    @Multipart
    @POST("08web/FileUploadServlet")
    Call<Bean> upload(@Part("") MultipartBody.Part file);
}
