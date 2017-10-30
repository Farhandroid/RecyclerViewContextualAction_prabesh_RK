package tanvir.testrecyclerviewcontextualaction;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  View.OnLongClickListener {


    boolean is_in_action_mode = false;
    TextView counterText;


    Toolbar toolbar;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<String> countryNames = new ArrayList<String>();
    ArrayList<String> selectionList = new ArrayList<String>();
    int counter = 0;


    String[] country = {"BD", "India", "Pakistan", "japan", "jjj", "jjjj", "hfdsk", "jkhf", "jdsfg", "jdsh", "jkdhs", "asdh", "jkh", "jdh", "kjah"};

    List<String> newList = Arrays.asList(country);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        counterText = (TextView) findViewById(R.id.counterText);
        counterText.setVisibility(View.GONE);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        ///counterTextView = (TextView) findViewById(R.id.counterText);
        ///counterTextView.setVisibility(View.GONE);


        countryNames.addAll(newList);
        adapter = new RecyclerAdapter(MainActivity.this, countryNames);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onLongClick(View view) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_action_mode);
        counterText.setVisibility(View.VISIBLE);
        is_in_action_mode = true;
        adapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public void prepareSelection(View view, int position) {
        if (((CheckBox) view).isChecked()) {
            selectionList.add(countryNames.get(position));
            counter = counter + 1;

        } else {
            selectionList.remove(countryNames.get(position));
            counter = counter - 1;

        }

        updateCounter(counter);

    }

    public void updateCounter(int counter) {

        counterText.setText(counter + " item selected");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.item_delete) {
            is_in_action_mode = false;
            RecyclerAdapter recyclerAdapter = (RecyclerAdapter) adapter;
            ((RecyclerAdapter) adapter).updateAdapter(selectionList);
            clearActionMode();
        }
        if (menuItem.getItemId() == android.R.id.home) {

            clearActionMode();
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    public void clearActionMode()
    {
        is_in_action_mode=false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        counterText.setVisibility(View.GONE);
        counterText.setText("0 item selected");
        counter=0;
        selectionList.clear();

    }

    @Override
    public void onBackPressed() {

        if (is_in_action_mode)
        {
            clearActionMode();
            adapter.notifyDataSetChanged();
        }
        else
        {
            super.onBackPressed();
        }

    }
}
