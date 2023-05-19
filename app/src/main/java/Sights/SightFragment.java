package Sights;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.usanotebook.NewsActivity;
import com.example.usanotebook.NewsArticle;
import com.example.usanotebook.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SightFragment extends Fragment {

    private SightApi sightApi;
    private ImageView sightImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sight, container, false);


        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("sightName")) {
            String sightName = arguments.getString("sightName");

            // Create an instance of SightApi
            sightApi = new SightApi();

            // Call the searchSightImage method
            sightApi.searchSightImage(sightName, sightImageView);
        }
        return rootView;


    }



    private void loadImageFromUrl(String imageUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(imageUrl).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle image loading failure
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    InputStream inputStream = response.body().byteStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sightImageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                } else {
                    // Handle image loading failure
                }
            }
        });
    }
}
