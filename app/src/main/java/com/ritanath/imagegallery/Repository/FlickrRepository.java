package com.ritanath.imagegallery.Repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ritanath.imagegallery.Model.FlickrResponse;
import com.ritanath.imagegallery.Model.Photo;
import com.ritanath.imagegallery.Model.Photos;
import com.ritanath.imagegallery.Retrofit.FlickrApiService;

import java.util.List;


import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlickrRepository {
    private FlickrApiService flickrApiService;

    @Inject
    public FlickrRepository(FlickrApiService flickrApiService) {
        this.flickrApiService = flickrApiService;
    }

    public LiveData<List<Photo>> getRecentPhotos(String apiKey) {
        MutableLiveData<List<Photo>> photosLiveData = new MutableLiveData<>();

        flickrApiService.getRecentPhotos().enqueue(new Callback<FlickrResponse>() {
            @Override
            public void onResponse(Call<FlickrResponse> call, Response<FlickrResponse> response) {

                if (response.isSuccessful()) {
                    Log.d("Status",response.body().toString());
                    Photos photos = response.body().getPhotos();
                    if (photos != null) {
                        photosLiveData.setValue(photos.getPhotoList());

                        Log.d("DATA",photosLiveData.toString());
                    }
                    else{
                        Log.d("DATA","Null occured");
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<FlickrResponse> call, Throwable t) {
                // Handle failure
                Log.d("Status",t.toString());
            }
        });

        return photosLiveData;
    }
}
