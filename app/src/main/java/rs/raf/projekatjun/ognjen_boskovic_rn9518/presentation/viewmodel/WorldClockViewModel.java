package rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.models.remote.EasternStandardTimeModel;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.repositories.WorldClockRepository;

public class WorldClockViewModel extends ViewModel {

    private MutableLiveData<EasternStandardTimeModel> easternStandardTime = new MutableLiveData<>();
    private MutableLiveData<List<String>> cities = new MutableLiveData<>();

    public void invokeCityService(String region, String city) {
        WorldClockRepository.getInstance().invokeCityService(region, city).enqueue(new Callback<EasternStandardTimeModel>() {

            @Override
            public void onResponse(
                    Call<EasternStandardTimeModel> call,
                    Response<EasternStandardTimeModel> response) {

                if (response.isSuccessful()) {
                    easternStandardTime.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                    Call<EasternStandardTimeModel> call,
                    Throwable t) {

            }
        });
    }

    public LiveData<EasternStandardTimeModel> getEasternStandardTime() {
        return easternStandardTime;
    }

    public LiveData<List<String>> getCities() {
        return cities;
    }
}
