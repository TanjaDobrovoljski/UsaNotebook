package Sights;
import android.widget.ImageView;

import com.example.usanotebook.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class SightApi {
    // Replace "YOUR_API_KEY" with the actual API key obtained from Flickr
    private static final String apiKey = "6d2c8722de764a277fe0e1de101e296e";

    // Set the request parameters, including the API key
    public void searchSightImage(String sightName, ImageView imageView) {
        OkHttpClient client = new OkHttpClient();
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        // Build the API request URL
        HttpUrl url = HttpUrl.parse("https://api.flickr.com/services/rest").newBuilder()
                .addQueryParameter("method", "flickr.photos.search")
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("text", sightName)
                .addQueryParameter("format", "json")
                .addQueryParameter("nojsoncallback", "1")
                .build();
        System.out.println("API Request URL: " + url);
        // Create the API request
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Make the API request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle request failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseJson = response.body().string();

                    // Extract the image URL from the API response
                    String imageUrl = extractImageUrlFromApiResponse(responseJson);

                    // Load the image into the ImageView using Picasso
                    Picasso.get()
                            .load(imageUrl)
                            .error(R.drawable.error_image) // Error image resource
                            .into(imageView);
                } else {
                    // Handle request failure
                    System.out.println("Request failed with code: " + response.code());
                }
            }
        });
    }

    private String extractImageUrlFromApiResponse(String responseJson) {
        try {
            // Parse the JSON response
            JSONObject responseObj = new JSONObject(responseJson);

            // Check if the API request was successful
            String stat = responseObj.optString("stat");
            if (stat.equalsIgnoreCase("ok")) {
                // Extract the first photo object from the response
                JSONObject photoObj = responseObj.getJSONObject("photos").getJSONArray("photo").optJSONObject(0);
                if (photoObj != null) {
                    // Extract the necessary photo details
                    String farm = photoObj.optString("farm");
                    String server = photoObj.optString("server");
                    String id = photoObj.optString("id");
                    String secret = photoObj.optString("secret");

                    // Construct the image URL using the extracted details
                    String imageUrl = "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg";

                    return imageUrl;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
