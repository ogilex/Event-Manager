package rs.raf.projekatjun.ognjen_boskovic_rn9518.data.datasources.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.models.remote.EasternStandardTimeModel;

public interface WorldClockApi {

    @GET("/api/timezone/{region}/{city}")
    Call<EasternStandardTimeModel> getEasternStandardTimeForRegionAndCity(@Path(value = "region") String myRegion, @Path(value = "city") String myCity);
}
