package rs.raf.projekatjun.ognjen_boskovic_rn9518.data.repositories;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.datasources.remote.WorldClockApi;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.models.remote.EasternStandardTimeModel;

public class WorldClockRepository {

    private static WorldClockRepository sInstance;
    private static final Object sInstanceLock = new Object();

    private WorldClockApi worldClockApi;

    private static final String BASE_URL = "https://worldtimeapi.org";

    public static WorldClockRepository getInstance() {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new WorldClockRepository();
            }
            return sInstance;
        }
    }

    public WorldClockRepository() {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(logging);

        OkHttpClient okHttpClient = clientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        worldClockApi = retrofit.create(WorldClockApi.class);
    }

    public Call<EasternStandardTimeModel> invokeCityService(String region, String city) {

        Call<EasternStandardTimeModel> call = worldClockApi.getEasternStandardTimeForRegionAndCity(region, city);

        return call;
    }
}
