package com.rayushka.bookbarter;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
import android.view.MenuItem;
        import android.widget.SearchView;

        import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.FirebaseDatabase;

        import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationsActivity extends AppCompatActivity {

    RecyclerView recview;
    NotifAdapter adapter;
    FloatingActionButton fb;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        recview=(RecyclerView)findViewById(R.id.reqrecview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
//        mAuth = FirebaseAuth.getInstance();
//        if (mAuth.getCurrentUser() != null){
//            String EMAIL= mAuth.getCurrentUser().getEmail();



        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Books").orderByChild("email").startAt(email).endAt(email+"\uf8ff"), model.class)
                        .build();
        Log.v(email ,"Current user email obtained" );

        adapter=new NotifAdapter(options);
        recview.setAdapter(adapter);

//        }


//        fb=(FloatingActionButton)findViewById(R.id.fadd);
//        fb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),AddData.class));
//            }
//        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        android.widget.SearchView searchView=(android.widget.SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                displayuserbooks(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                displayuserbooks(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void displayuserbooks(String s)
    {
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Books").orderByChild("email").startAt(s).endAt(s+"\uf8ff"), model.class)
                        .build();

        adapter=new NotifAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }
}




