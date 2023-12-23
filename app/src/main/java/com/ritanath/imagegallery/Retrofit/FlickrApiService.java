package com.ritanath.imagegallery.Retrofit;

import com.ritanath.imagegallery.Model.FlickrResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlickrApiService {
    @GET("services/rest/?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s")
    Call<FlickrResponse> getRecentPhotos();

}
