package lyp.bawei.com.retrofit1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;


import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private TextView text;
    private OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);

       //无参get请求
       // retrofitOfGet();
       //有参get请求
       // youcanOfGet();
       //post请求
        //ofPost();
        //上传图片
        ofPostPic();
            }

    private void ofPostPic() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("").client(client).addConverterFactory(GsonConverterFactory.create()).build();
        //这里采用的是Java的动态代理模式
        IUserBiz iUserBiz = retrofit.create(IUserBiz.class);
        //拿到图片 file
        File file = new File(Environment.getExternalStorageDirectory(), "/Pictures/Screenshots/a.jpg");
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("application/otcet-stream"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        final Call<Bean> upload = iUserBiz.upload(body);
        upload.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                Bean userInfo = response.body();


            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

            }
        });
    }

    private void ofPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://169.254.217.5:8080/bullking1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IUserBiz userBiz = retrofit.create(IUserBiz.class);
        Call<Bean3> call = userBiz.login("111","111");
        call.enqueue(new Callback<Bean3>() {
            @Override
            public void onResponse(Call<Bean3> call, Response<Bean3> response) {
                text.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Bean3> call, Throwable t) {

            }
        });
    }

    private void youcanOfGet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://v.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IUserBiz userBiz = retrofit.create(IUserBiz.class);
        Call<Bean2> call = userBiz.getArgumentUsere("index","shehui","32b9973df2e6ee0c2bf094b61c7d7844");
        call.enqueue(new Callback<Bean2>() {
            @Override
            public void onResponse(Call<Bean2> call, Response<Bean2> response) {
                text.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Bean2> call, Throwable t) {

            }
        });
    }

    public void retrofitOfGet(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IUserBiz userBiz = retrofit.create(IUserBiz.class);
        Call<Bean> call = userBiz.getUsers();
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                text.setText(response.body().getStatus());
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

            }
        });
}
}
