package Sights;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FirstFragmentViewModel extends ViewModel {
    private MutableLiveData<String> title = new MutableLiveData<>();
    private MutableLiveData<String> description = new MutableLiveData<>();
    private MutableLiveData<String> imageUrl = new MutableLiveData<>();
    private MutableLiveData<String> videoUrl = new MutableLiveData<>();

    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description.setValue(description);
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl.setValue(imageUrl);
    }

    public MutableLiveData<String> getImageUrl() {
        return imageUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl.setValue(videoUrl);
    }

    public MutableLiveData<String> getVideoUrl() {
        return videoUrl;
    }
}
