package com.example.tina.underthebutton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> list;

    public void onOpenMap(View v) {

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("info.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void onOpenSort(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = (TextView) findViewById(R.id.FoodTruck);
        final ListView list = (ListView) findViewById(R.id.listView);

        try {
            String jsonFileContent = loadJSONFromAsset();
            JSONArray jsonArray = new JSONArray(jsonFileContent);

            Log.e("PERMISSION %s", "f");

            ArrayList<FoodTruck> arrayList = new ArrayList<>();
            final double[] lat_arr = new double[((JSONObject) jsonArray.get(0)).length()];
            final double[] lon_arr = new double[((JSONObject) jsonArray.get(0)).length()];

            for (Iterator<String> it = ((JSONObject) jsonArray.get(0)).keys(); it.hasNext(); ) {
                Object key = it.next();

                String name = (String) ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get((String) key)).get(0)).get("name");
                double rating = (double) ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get((String) key)).get(0)).get("rating");

                double lat = (double) ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get((String) key)).get(0)).get("lat");
                double lon = (double) ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get((String) key)).get(0)).get("long");

                FoodTruck one = new FoodTruck(name, Double.toString(rating), arrayList.size(), lat, lon);
                arrayList.add(one);

                lat_arr[arrayList.size() - 1] = lat;
                lon_arr[arrayList.size() - 1] = lon;
            }

            PersonListAdapter adapter = new PersonListAdapter(this, R.layout.adapter_view_layout, arrayList);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int itemPosition = position;
                    FoodTruck ft = (FoodTruck) list.getItemAtPosition(position);

                    Intent intent = new Intent(MainActivity.this, SingleTruckView.class);

                    intent.putExtra("name", ft.getName());
                    intent.putExtra("lat", ft.getLat());
                    intent.putExtra("lon", ft.getLon());
                    intent.putExtra("group", false);

                    MainActivity.this.startActivity(intent);
                }
            });

            ((Button) getWindow().getDecorView().findViewById(R.id.map_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SingleTruckView.class);

                    intent.putExtra("lat_arr", lat_arr);
                    intent.putExtra("lon_arr", lon_arr);
                    intent.putExtra("group", true);

                    MainActivity.this.startActivity(intent);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
