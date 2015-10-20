package com.finddreams.runningman;

import java.util.ArrayList;

import com.example.runningman.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MySwipeView extends Activity implements SwipeRefreshLayout.OnRefreshListener{


	private SwipeRefreshLayout mSwipeLayout;
	private ListView mListView;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayAdapter<String> myAdapter;
	private int list_count = 0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去掉标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.swipe_view);

		mListView = (ListView) findViewById(R.id.listview);
		myAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				getData());
		//数据绑定
		mListView.setAdapter(myAdapter);
		//mSwipeLayout 相关属性设置
		mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		mSwipeLayout.setOnRefreshListener(this);
		mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light, android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
	}

	private ArrayList<String> getData() {
		list.add("小翠");
		list.add("小陶");
		list.add("小三");
		return list;
	}

	/**
	 * 刷新功能代码
	 */
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mSwipeLayout.setRefreshing(false);
				Toast.makeText(getApplicationContext(), "Yes!", Toast.LENGTH_SHORT).show();
				
				list.add("小逗"+list_count);
				list_count++;
				myAdapter.notifyDataSetChanged();
			}
		}, 3000);
	}
}
