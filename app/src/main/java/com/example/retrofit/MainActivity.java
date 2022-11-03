package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView textResult;
    private JsonPlaceholderApi jsonPlaceholderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.textResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);
        //to show Comments
        showComments();

        //to show post.
        //showPost();
    }

    private void showComments() {
        Call<List<Data>> call = jsonPlaceholderApi.getComments(1);

        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {

                if (!response.isSuccessful()){
                    textResult.setText(response.code());
                    return;
                }
                List<Data> datas = response.body();

                for (Data data : datas){
                    String content = "";

                    content += data.getId() + "\n";
                    content += data.getUserID() + "\n";
                    content += data.getTitle() + "\n";
                    content += data.getText() + "\n\n";

                    textResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                textResult.setText("error");
            }
        });
    }

    private void showPost() {
        Call<List<Data>> call = jsonPlaceholderApi.getPost();

        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {

                if (!response.isSuccessful()){
                    textResult.setText(response.code());
                    return;
                }
                List<Data> datas = response.body();

                for (Data data : datas){
                    String content = "";

                    content += data.getId() + "\n";
                    content += data.getUserID() + "\n";
                    content += data.getTitle() + "\n";
                    content += data.getText() + "\n\n";

                    textResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                textResult.setText("error");
            }
        });
    }
}