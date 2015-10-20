# SwipeRefresh
这是 Google自己的下拉刷新组件SwipeRefreshLayout，简单实现

#如下为简答的实现效果
　　　图片可能无法显示
 
#需要完成的设计：
　　　1.布局文件的设计 如下文件名为 swipe_view

<android.support.v4.widget.SwipeRefreshLayout        	 xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/swipe_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >
    <ListView
        android:id="@+id/listview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="16dp" />

</android.support.v4.widget.SwipeRefreshLayout>

　　　2.对应Activity的代码实现

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

#PS：setColorScheme()已经弃用，使用setColorSchemeResources()来设置颜色。