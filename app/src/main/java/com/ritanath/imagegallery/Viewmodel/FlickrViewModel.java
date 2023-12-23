package com.ritanath.imagegallery.Viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ritanath.imagegallery.MainActivity;
import com.ritanath.imagegallery.Model.Photo;
import com.ritanath.imagegallery.Repository.FlickrRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;


@HiltViewModel
public class FlickrViewModel extends ViewModel {
    private LiveData<List<Photo>> recentPhotosLiveData;

    private FlickrRepository flickrRepository;

    private MainActivity mainActivity;

    public FlickrViewModel(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Inject
    public FlickrViewModel(FlickrRepository flickrRepository) {
        this.flickrRepository = flickrRepository;
    }

    public LiveData<List<Photo>> getRecentPhotos(String apiKey) {
       Log.d("Status",apiKey);
        if (recentPhotosLiveData == null) {
            recentPhotosLiveData = flickrRepository.getRecentPhotos(apiKey);
        }
        return recentPhotosLiveData;
    }
}
