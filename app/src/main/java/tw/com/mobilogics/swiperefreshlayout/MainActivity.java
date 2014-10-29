package tw.com.mobilogics.swiperefreshlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private ListView mListView;

  private static final List<String> mList = new ArrayList<String>();
  private ArrayAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initWidget();
  }

  private void initMapData() {
    for (int i = 0; i < 10; i++) {
      mList.add(i, String.valueOf(new Random().nextInt()));
    }
  }

  private void initWidget() {
    mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(
      android.R.color.holo_blue_bright,
      android.R.color.holo_red_dark,
      android.R.color.holo_green_dark,
      android.R.color.holo_purple
    );
    mListView = (ListView) findViewById(R.id.listView);
    mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
    mListView.setAdapter(mAdapter);
  }


  @Override
  public void onRefresh() {
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        if (mList.size() == 0) initMapData();
        else {
          int startIndex = mList.size();
          int lastIndex = startIndex + 5;
          for (int i = startIndex; i < lastIndex; i++) {
            mList.add(i, String.valueOf(new Random().nextFloat()));
          }
        }
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
      }
    }, 2500);
  }
}