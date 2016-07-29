package themightykam.productivity.m_UI;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

import themightykam.productivity.R;

/**
 * Created by Kamoya on 7/20/16.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    Context c;
    ArrayList<String> userTasks;

    public TaskAdapter(Context c, ArrayList<String> userTasks) {
        this.c = c;
        this.userTasks = userTasks;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.model, parent, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder (TaskViewHolder holder, int position) {
        holder.taskTxt.setText(userTasks.get(position));
    }

    @Override
    public int getItemCount() {
        return userTasks.size();
    }

}
