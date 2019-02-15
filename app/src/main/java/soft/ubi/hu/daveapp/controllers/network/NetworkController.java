package soft.ubi.hu.daveapp.controllers.network;

import android.util.Log;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import soft.ubi.hu.daveapp.BuildConfig;
import soft.ubi.hu.daveapp.controllers.network.dto.registration.RegistrationRequest;
import soft.ubi.hu.daveapp.controllers.network.dto.user.UserListResponse;
import soft.ubi.hu.daveapp.controllers.network.dto.user.UserResponse;
import soft.ubi.hu.daveapp.data.User;

public class NetworkController {
    private static final String TAG = "NETWORK_CONTROLLER";
    private static NetworkController instance = new NetworkController();
    public final TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    //
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    //
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
    };
    private Retrofit retrofit;
    private EndPoints endPoints;

    public NetworkController() {
        initRetrofit();
    }

    public static NetworkController getInstance() {
        return instance;
    }

    private void initRetrofit() {
        OkHttpClient.Builder okHttpClient;
        okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.readTimeout(60, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS);
        okHttpClient.retryOnConnectionFailure(true);
        final SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            okHttpClient.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            Log.d(TAG, "MESSAGE", e);
        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        okHttpClient.hostnameVerifier((hostname, session) -> true);
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.addInterceptor(logging).build())
                .build();
    }

    private Retrofit getRetrofit() {
        return retrofit;
    }

    private EndPoints getEndpoint() {
        if (endPoints == null) {
            endPoints = getRetrofit().create(EndPoints.class);
        }
        return endPoints;
    }

    public Call<UserResponse> registration(final User user) {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setName(user.getName());
        registrationRequest.setEmail(user.getEmail());
        registrationRequest.setPassword(user.getPassword());
        registrationRequest.setRoles(user.getRoles());
        return getEndpoint().postRegistration(registrationRequest);
    }

    public Call<UserListResponse> getAllUser(final String auth) {
        return getEndpoint().getAllUser(auth);
    }
}
