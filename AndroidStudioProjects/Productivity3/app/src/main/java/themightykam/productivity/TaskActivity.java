package themightykam.productivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

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

/**
 * Created by Kamoya on 7/16/16.
 */
public class TaskActivity extends AppCompatActivity{
    DatabaseReference db;
    FirebaseHelper helper;
    EditText taskEditText;
    TaskAdapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.);
        // setSupportActionBar(toolbar);

        // Setup rv
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // Setup fb
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        // Adapter
        adapter = new TaskAdapter(this, helper.retrieve());
        rv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
            }
        });
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
                        adapter = new TaskAdapter(TaskActivity.this, helper.retrieve());
                        rv.setAdapter(adapter);
                    }
                } else {
                        Toast.makeText(TaskActivity.this, "Task Cannot Be Nothing", Toast.LENGTH_SHORT).show();
                    }
                }
        });
        d.show();
    }

}






































        /*// this setting improves performance since changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        //mAdapter = new TaskAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

    }
}


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private String[] mDataset;

    // provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView card_view;
        TextView user_task;
        TextView task_category;
        ImageView letter_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            card_view = (CardView)itemView.findViewById(R.id.card_view);
            user_task = (TextView)itemView.findViewById(R.id.user_task);
            task_category = (TextView)itemView.findViewById(R.id.task_category);
            letter_icon = (ImageView)itemView.findViewById(R.id.letter_icon);
        }
    }

    List<Task>taskList;
    TaskAdapter(List<Task>taskList) {
        this.taskList = taskList;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return taskList.size();
    }
    // Create views (invoked by the layout manager)
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        viewHolder.user_task.setText(taskList.get(i).taskName);
        viewHolder.task_category.setText(taskList.get(i).category);
        viewHolder.letter_icon.setImageResource(taskList.get(i).letterIcon);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    TaskAdapter adapter = new TaskAdapter(taskList);
    mRecyclerView.setAdapter(adapter);

    // Provide a suitable constructor (depends on the kind of dataset)
    public TaskAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create views (invoked by the layout manager)
    *//*@Override
    public TaskAdapter.ViewHolder onCreateViewHolder (ViewGroup parent,
                                                    int viewType) {
        // Create a new view
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.content_main, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ...
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }*//*

}

class Task{
    String taskName;
    String category;
    int letterIcon;

    Task(String taskName, String category, int letterIcon) {
        this.taskName = taskName;
        this.category = category;
        this.letterIcon = letterIcon;
    }
}
private List<Task>taskList;

private void initializeData() {
    taskList = new ArrayList<>();
}*/