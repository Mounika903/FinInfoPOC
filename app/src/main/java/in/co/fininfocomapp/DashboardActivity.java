package in.co.fininfocomapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import in.co.fininfocomapp.utils.SharedPreferenceUtil;

public class DashboardActivity extends AppCompatActivity {

    private ListItemAdapter listItemAdapter;
    RecyclerView recyclerView;
    ArrayList<DataModal> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        arrayList = new ArrayList<>();
        arrayList.add(new DataModal("Raj", 62, "Karnataka"));
        arrayList.add(new DataModal("Ramu", 43, "Kerala"));
        arrayList.add(new DataModal("Shiva", 44, "Gujarat"));
        arrayList.add(new DataModal("Geeta", 34, "Kashmir"));
        arrayList.add(new DataModal("Sam", 31, "Banglore"));
        arrayList.add(new DataModal("Hari", 7, "Goa"));
        arrayList.add(new DataModal("Venu", 5, "Vizag"));
        arrayList.add(new DataModal("Jack", 1, "Delhi"));
        arrayList.add(new DataModal("Harry", 2, "Chennai"));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listItemAdapter = new ListItemAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listItemAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        boolean isUserLoggedIn = SharedPreferenceUtil.getUserLoggedInStatus(DashboardActivity.this);
        if (!isUserLoggedIn) {
            Intent i = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Collections.sort(arrayList, Comparator.comparing(o -> o.name));
                    recyclerView.setAdapter(listItemAdapter);
                    listItemAdapter.notifyDataSetChanged();

                return true;
            case R.id.item2:
                Collections.sort(arrayList, Comparator.comparing(o -> o.age));
                recyclerView.setAdapter(listItemAdapter);
                listItemAdapter.notifyDataSetChanged();

                return true;
            case R.id.item3:
                Collections.sort(arrayList, Comparator.comparing(o -> o.city));
                recyclerView.setAdapter(listItemAdapter);
                listItemAdapter.notifyDataSetChanged();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
