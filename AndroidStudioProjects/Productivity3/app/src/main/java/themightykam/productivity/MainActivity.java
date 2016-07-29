package themightykam.productivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import themightykam.productivity.m_Firebase.FirebaseHelper;
import themightykam.productivity.m_Model.UserTasks;
import themightykam.productivity.m_UI.TaskAdapter;


public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    DatabaseReference db;
    FirebaseHelper helper;
    EditText taskEditText;
    TaskAdapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //rv = (RecyclerView) findViewById(R.id.rv);
        //rv.setLayoutManager(new LinearLayoutManager(this));

        //db = FirebaseDatabase.getInstance().getReference();
        //helper = new FirebaseHelper(db);

        adapter = new TaskAdapter(this, helper.retrieve());
        //rv.setAdapter(adapter);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPageAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify it as a parent activity in the Manifest file.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if(id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * * A placeholder fragment container a simple view.
     */
    public static class PlaceholderFragment extends Fragment{
        DatabaseReference db;
        FirebaseHelper helper;
        EditText taskEditText;
        TaskAdapter adapter;
        //adapter = new TaskAdapter(this, helper.retrieve());
        //TaskAdapter(adapter, helper.retrieve());

        RecyclerView rv;
        LinearLayoutManager llm;
        /**
         * The fragment argument representing the section number for this argument
         */
        private static final String ARG_SECTION_NUMBER="section_number";

        public PlaceholderFragment() {
        }
        /**
         * Returns a new instance of this fragment for the given section number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format,
                    //getArguments().getInt(ARG_SECTION_NUMBER)));
            rv = (RecyclerView) rootView.findViewById(R.id.rv);
            rv.setLayoutManager(llm);
            // Firebase setup
            db = FirebaseDatabase.getInstance().getReference();
            helper = new FirebaseHelper(db);
            // Adapter
            rv.setAdapter(adapter);


            FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    displayInputDialog();
                }
            });
            return rootView;
        }
        private void displayInputDialog() {
            Dialog d = new Dialog(this);
            d.setTitle("Save to Firebase");
            d.setContentView(R.layout.input_dialog);

            taskEditText = (EditText) d.findViewById(R.id.taskEditText);
            Button saveBtn = (Button) d.findViewById(R.id.saveBtn);
            // Save
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get Data
                    String task = taskEditText.getText().toString();
                    // Set Data
                    UserTasks s = new UserTasks();
                    s.setTask(task);
                    // Validate
                    if (task.length() > 0) {
                        if (helper.save(s)) {
                            taskEditText.setText("");
                            adapter = new TaskAdapter(MainActivity.this, helper.retrieve());
                            rv.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Task Cannot Be Nothing", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            d.show();
        }
    }
}