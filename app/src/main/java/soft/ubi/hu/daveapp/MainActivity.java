package soft.ubi.hu.daveapp;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import soft.ubi.hu.daveapp.controllers.network.NetworkController;
import soft.ubi.hu.daveapp.controllers.network.dto.user.UserListResponse;
import soft.ubi.hu.daveapp.controllers.network.dto.user.UserResponse;
import soft.ubi.hu.daveapp.data.User;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.registration_button)
    Button testButton;
    @BindView(R.id.name)
    EditText nameEditText;
    @BindView(R.id.email)
    EditText emailEditText;
    @BindView(R.id.password)
    EditText passwordEditText;

    NetworkController controller = NetworkController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.registration_button)
    public void buttonPressed() {
        User user = new User();
        user.setName(nameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());
        controller.registration(user).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(MainActivity.this, "Hurrá!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Nem hurrá!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.get_user_button)
    public void onGetUserPressed() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String res = Base64.encodeToString((name + ":" + password).getBytes(), Base64.NO_WRAP);
        Log.d(TAG, "onGetUserPressed: " + res);
        String auth = "Basic " + res;
        Log.d(TAG, "onGetUserPressed: " + auth);
        controller.getAllUser(auth).enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                Toast.makeText(MainActivity.this, "GetUser hurrá!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GetUser nem hurrá!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
