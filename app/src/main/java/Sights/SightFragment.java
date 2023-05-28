package Sights;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.usanotebook.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SightFragment extends Fragment {

    private FirstFragmentViewModel viewModel;
    private ImageView imageView;
    private PlayerView playerVieww;
    private Button googleMapsButton;
    private SimpleExoPlayer player;

    private static final String SIGHT_NAME = "Statue of Liberty - UNITED STATES OF AMERICA";
    private static final String API_KEY = "QWbBcpE6Sb1dsPpWTIt29e7puN3daasrXHBIucDx0qEGdgwxJijP5Mrl"; //6d2c8722de764a277fe0e1de101e296e
   // private static final String ENDPOINT = "https://api.flickr.com/services/rest/";
    //private static final String METHOD_GETRECENT = "flickr.photos.getRecent";
    //private static final String METHOD_SEARCH = "flickr.photos.search";
    String imageUrl="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FirstFragmentViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sight, container, false);
        String imageUrl = "";


        imageView = rootView.findViewById(R.id.pictureImageView);
        playerVieww=rootView.findViewById(R.id.sightVideo);
        player = new SimpleExoPlayer.Builder(getContext()).build();
        playerVieww.setPlayer(player);
        googleMapsButton=rootView.findViewById(R.id.showOnMapButton);
        Bundle args = getArguments();
        String title = "";

        if (args != null) {

            // Use the title as needed in the fragment
            // For example, set it as the toolbar title
             title = args.getString("title");
            buildImageUrl(title);
            retrieveVideoUrl(title);

        }


            // Observe the data variables from the ViewModel
            viewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String title) {
                    // Update the title view
                    TextView titleTextView = rootView.findViewById(R.id.titleTextView);
                    titleTextView.setText(title);

                }
            });

            viewModel.getDescription().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String description) {
                    // Update the description view
                    TextView descriptionTextView = rootView.findViewById(R.id.descriptionTextView);
                    descriptionTextView.setText(description);
                }
            });

            viewModel.getImageUrl().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String imageUrl) {
                    // Load and display the image using the provided imageUrl
                    ImageView imageView = rootView.findViewById(R.id.pictureImageView);
                    // Implement your image loading logic here
                }
            });

            viewModel.getVideoUrl().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String videoUrl) {
                    // Load and play the video using the provided videoUrl
                    PlayerView playerView = rootView.findViewById(R.id.sightVideo);
                    // Implement your video loading and playback logic here
                }
            });
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setPlayWhenReady(false);
                Fragment fragment = new MapsFragment(); // Replace with the appropriate fragment you want to open

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment); // Replace "R.id.fragment_container" with your actual container view ID
                fragmentTransaction.addToBackStack(null); // Optional: Add the transaction to the back stack
                fragmentTransaction.commit();
            }
        };

        googleMapsButton.setOnClickListener(buttonClickListener);

        return rootView;
    }




    private void buildImageUrl(String sightName) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    String sightName = params[0];
                    String encodedSightName = URLEncoder.encode(sightName, "UTF-8");

                    // Construct the API URL with the sight name parameter
                    String apiUrl = "https://api.pexels.com/v1/search?query=" + encodedSightName + "&per_page=1";

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(apiUrl)
                            .addHeader("Authorization", API_KEY)
                            .build();

                    Response response = client.newCall(request).execute();
                    String json = response.body().string();

                    // Parse the JSON response
                    return parseImageUrlFromResponse(json);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String imageUrl) {
                if (imageUrl != null) {
                    loadImageFromUrl(imageUrl);
                } else {
                    // Handle the error case
                }
            }
        }.execute(sightName);
    }


    private String parseImageUrlFromResponse(String responseJson) throws JSONException {
    JSONObject responseObj = new JSONObject(responseJson);
    JSONArray photosArray = responseObj.getJSONArray("photos");

    if (photosArray.length() > 0) {
        JSONObject photoObj = photosArray.getJSONObject(0);
        JSONObject srcObj = photoObj.getJSONObject("src");
        String imageUrl = srcObj.getString("large");

        return imageUrl;
    }

    return null;
}


    private void loadImageFromUrl(String imageUrl) {
        if (imageUrl != null) {
            Picasso.get()
                    .load(imageUrl)
                    .resize(imageView.getWidth(), imageView.getHeight())
                    .centerCrop()
                    .error(R.drawable.error_image)
                    .into(imageView);
        } else {
            // Handle the case where imageUrl is null or invalid
        }
    }
    private void retrieveVideoUrl(String query) {
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String apiUrl = "https://api.pexels.com/videos/search?query=" + params[0] + "&per_page=1";

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(apiUrl)
                        .addHeader("Authorization", API_KEY)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseJson) {
                if (responseJson != null) {
                    try {
                        String videoUrl = parseVideoUrlFromResponse(responseJson);
                        if (videoUrl != null) {
                            playVideo(videoUrl);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                } else {
                    // Handle the case where responseJson is null or the API request failed
                }
            }
        };

        task.execute(query);
    }




    private String parseVideoUrlFromResponse(String responseJson) throws JSONException {
        JSONObject responseObj = new JSONObject(responseJson);
        JSONArray videosArray = responseObj.getJSONArray("videos");

        if (videosArray.length() > 0) {
            JSONObject videoObj = videosArray.getJSONObject(0);
            JSONArray videoFilesArray = videoObj.getJSONArray("video_files");

            if (videoFilesArray.length() > 0) {
                JSONObject videoFileObj = videoFilesArray.getJSONObject(0);
                String videoUrl = videoFileObj.getString("link");

                return videoUrl;
            }
        }

        return null;
    }

    private void playVideo(String videoUrl) {
        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        player.setMediaItem(mediaItem);

        player.prepare();
        player.play();

    }
    public void handleBackPressed() {
        player.setPlayWhenReady(false);

    }

}
