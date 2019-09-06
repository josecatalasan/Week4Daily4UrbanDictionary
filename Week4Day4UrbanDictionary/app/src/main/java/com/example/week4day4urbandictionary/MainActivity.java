package com.example.week4day4urbandictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import com.example.week4day4urbandictionary.model.datasource.remote.retrofit.RetrofitHelper;
import com.example.week4day4urbandictionary.model.datasource.remote.retrofit.UrbanDictObserver;
import com.example.week4day4urbandictionary.model.events.UrbanDictResponseEvent;
import com.example.week4day4urbandictionary.model.urbandictionary.ListItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvSearchResults;
    private UrbanDictAdapter adapter;
    private EditText etSearch;
    private ToggleButton togSort;
    private ProgressBar pbQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvSearchResults = findViewById(R.id.rvSearchResults);
        etSearch = findViewById(R.id.etSearch);
        togSort = findViewById(R.id.togSort);
        pbQuery = findViewById(R.id.pbQuery);
        togSort.setChecked(true);

        togSort.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(adapter != null) {
                    if (togSort.isChecked()) {
                        adapter.sort(true);
                    } else {
                        adapter.sort(false);
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void populateRecyclerView(List<ListItem> list){
        if(adapter == null){
            adapter = new UrbanDictAdapter(list);
            rvSearchResults.setLayoutManager(new LinearLayoutManager(this));
            rvSearchResults.setAdapter(adapter);
        } else {
            adapter.setDefList(list);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUrbanDictEvent(UrbanDictResponseEvent event){
        if(event.getUrbanDictionaryResponse() != null){
            List<ListItem> definitionList = event.getUrbanDictionaryResponse().getList();
            populateRecyclerView(definitionList);
            adapter.sort(togSort.isChecked());
            pbQuery.setVisibility(View.GONE);
        }
    }

    public void onGoClicked(View view) {
        //send off API call
        pbQuery.setVisibility(View.VISIBLE);
        RetrofitHelper helper = new RetrofitHelper();
        helper.getService().getDefinitions(etSearch.getText().toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new UrbanDictObserver());
    }
}
