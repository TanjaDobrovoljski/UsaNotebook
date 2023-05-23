package Sights;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.usanotebook.R;
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

    private ImageView imageView;
    private static final String SIGHT_NAME = "Statue of Liberty New York";
    private static final String API_KEY = "6d2c8722de764a277fe0e1de101e296e";
    private static final String ENDPOINT = "https://api.flickr.com/services/rest/";
    private static final String METHOD_GETRECENT = "flickr.photos.getRecent";
    private static final String METHOD_SEARCH = "flickr.photos.search";
    String imageUrl="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sight, container, false);

        imageView = rootView.findViewById(R.id.pictureImageView);

        // Perform the API request to download the image
        String imageUrl = "";
        buildImageUrl();
        //downloadImage(imageUrl);

        return rootView;
    }

    private void buildImageUrl() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    String encodedSightName = URLEncoder.encode(SIGHT_NAME, "UTF-8");
                   /* String apiUrl = "https://api.flickr.com/services/rest?" +
                            "method=flickr.photos.search" +
                            "&api_key=" + API_KEY +
                            "&text=" + encodedSightName +
                            "&per_page=1" +  // Limit the number of photos to 1
                            "&format=json" +
                            "&nojsoncallback=1";
*/
                    String apiUrl;

                    apiUrl = Uri.parse(ENDPOINT).buildUpon()
                                .appendQueryParameter("method", METHOD_SEARCH)
                                .appendQueryParameter("api_key", API_KEY)
                                .appendQueryParameter("format", "json")
                                .appendQueryParameter("nojsoncallback", "1")
                                .appendQueryParameter("text", SIGHT_NAME)
                                .appendQueryParameter("page", String.valueOf(1))
                                .build().toString();

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(apiUrl)
                            .build();

                    Response response = client.newCall(request).execute();
                    String json = response.body().string();

                    // Parse the JSON response
                    parseImageUrlFromResponse(json);

                    // Return the image URL
                    return imageUrl;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String imageUrl) {
                if (imageUrl != null) {
                    downloadImage(imageUrl);
                } else {
                    // Handle the error case
                }
            }
        }.execute();
    }

    private String parseImageUrlFromResponse(String responseJson) {
        try {
            JSONObject responseObj = new JSONObject(responseJson);

            // Check if "photos" field exists
            if (responseObj.has("photos")) {
                JSONObject photosObj = responseObj.getJSONObject("photos");

                // Check if "photo" array exists
                if (photosObj.has("photo")) {
                    JSONArray photoArray = photosObj.getJSONArray("photo");

                    // Check if the "photo" array is not empty
                    if (photoArray.length() > 0) {
                        JSONObject photoObj = photoArray.getJSONObject(0);

                        String farm = photoObj.getString("farm");
                        String server = photoObj.getString("server");
                        String id = photoObj.getString("id");
                        String secret = photoObj.getString("secret");


                        SightItem item = new SightItem(id, secret, server, farm);

                        imageUrl = "https://farm" + item.getFarm() + ".staticflickr.com/" +
                                item.getServer() + "/" + item.getId() + "_" + item.getSecret() + ".jpg";
                        System.out.println(imageUrl);
                    } else {
                        // Handle case when "photo" array is empty
                    }
                } else {
                    // Handle case when "photo" array is missing
                }
            } else {
                // Handle case when "photos" field is missing
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing error
        }

        return responseJson;
    }

    // ...


    private void downloadImage(String directImageUrl) {
        if (directImageUrl != null) {
            String imageUrl = directImageUrl.startsWith("http://") || directImageUrl.startsWith("https://")
                    ? directImageUrl : "http://" + directImageUrl;

            Picasso.get()
                    .load(imageUrl)
                    .resize(imageView.getWidth(), imageView.getHeight())
                    .centerCrop()
                    .error(R.drawable.error_image)
                    .into(imageView);
        } else {
            // Handle the case where directImageUrl is null or invalid
        }
    }
}
