package kmitl.lab07.montita58070114.mylazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.lab07.montita58070114.mylazyinstagram.adapter.PostAdapter;
import kmitl.lab07.montita58070114.mylazyinstagram.api.MyLazyInstagramApi;
import kmitl.lab07.montita58070114.mylazyinstagram.model.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile("cartoon");

        Button btnGrid = findViewById(R.id.btnGrid);
        Button btnLinear = findViewById(R.id.btnLinear);

        btnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));

            }
        });

        btnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });


    }

    private void getUserProfile(String userName) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyLazyInstagramApi.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyLazyInstagramApi MyLazyInstagramApi = retrofit.create(MyLazyInstagramApi.class);
        Call<UserProfile> call = MyLazyInstagramApi.getProfile(userName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    UserProfile userProfile = response.body();
                    display(userProfile);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });



    }

    private void display(UserProfile userProfile) {
        TextView textUser = findViewById(R.id.textUser);
        textUser.setText("@" + userProfile.getUser());

        TextView textPost = findViewById(R.id.textPost);
        textPost.setText("Post\n" + userProfile.getPost());

        TextView textFollower = findViewById(R.id.textFollower);
        textFollower.setText("Follower\n" + userProfile.getFollower());

        TextView textFollwing = findViewById(R.id.textFollowing);
        textFollwing.setText("Following\n" + userProfile.getFollowing());

        TextView textBio = findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

        ImageView imageProfile = findViewById(R.id.imageProfile);
        Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);

        list = findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        PostAdapter adapter = new PostAdapter(MainActivity.this);
        adapter.setData(userProfile.getPosts());
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.android:
                getUserProfile("android");
                return true;
            case R.id.nature:
                getUserProfile("nature");
                return true;
            case R.id.cartoon:
                getUserProfile("cartoon");
                return true;
            default:
                return false;
        }
    }





}
