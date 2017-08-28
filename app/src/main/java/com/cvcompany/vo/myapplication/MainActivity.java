package com.cvcompany.vo.myapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.cvcompany.vo.myapplication.View.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName() ;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    View viewFooter;
    private List<String> list;
    private static boolean isLoading=false;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        viewFooter  =  inflater.inflate(R.layout.view_footer, null);
        initList();
        adapter = new MyAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!isLoading && manager.getItemCount()-5== manager.findLastVisibleItemPosition()){
                    load();
                    isLoading = true;
                }
            }
        });




    }

    private void initList() {
        list = new ArrayList<String>();
        for(int i = 0 ; i< 90; i++){
            list.add(i+"a");
        }




    }

    private void load() {
     for(int i = 0 ; i< 30; i++){
         list.add(i+"");
     }
        adapter.notifyDataSetChanged();
    }

    }
