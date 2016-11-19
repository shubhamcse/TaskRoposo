package com.shubham.taskroposo;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Shubham Gupta on 16-11-2016.
 */
@EActivity(R.layout.activity_list)
public class MainListActivity extends AppCompatActivity implements ListAdapter.OnItemClicked {
@ViewById(R.id.recycler_view)
    RecyclerView recyclerView;

    List<StoryModel> stories = new ArrayList<>();
    LinearLayoutManager llm;
    ListAdapter listAdapter;

    @AfterViews
    void init() {

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        String json =  loadJSONFromAsset();


        try {
            JSONArray jsonArray = new JSONArray(json);
            HashMap<String,UserModel> users = new HashMap<>();


            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Gson gson = new Gson();

                if(jsonObject.isNull("type")){
                    UserModel userModel = gson.fromJson(jsonObject.toString(),UserModel.class);
                    users.put(userModel.getId(),userModel);
                }else{
                    StoryModel storyModel = gson.fromJson(jsonObject.toString(),StoryModel.class);
                    storyModel.setUser(users.get(storyModel.getDb()));
                    stories.add(storyModel);
                }
            }

            Log.i("see",""+users.size()+":"+stories.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listAdapter = new ListAdapter();
        listAdapter.setStories(stories);


        recyclerView.setAdapter(listAdapter);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("roposo.json");
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

    @Override
    public void onListItemClicked(int position, View view) {

    }

    @Override
    public void onFollowButtonClicked(int position) {
        stories.get(position).getUser().toggleFollowButton();
      //  Log.i("see",""+stories.get(position).getUser().getIsFollowing());
        listAdapter.setStories(stories);
        listAdapter.notifyDataSetChanged();
       // listAdapter.notifyItemRangeChanged(llm.findFirstVisibleItemPosition(),llm.findLastVisibleItemPosition()-llm.findFirstVisibleItemPosition()+1);


    }
}
