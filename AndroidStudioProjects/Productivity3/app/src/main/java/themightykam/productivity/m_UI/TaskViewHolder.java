package themightykam.productivity.m_UI;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import themightykam.productivity.R;

/**
 * Created by Kamoya on 7/20/16.
 */
public class TaskViewHolder extends RecyclerView.ViewHolder {
    TextView taskTxt;

    public TaskViewHolder(View itemView) {
        super(itemView);

        taskTxt = (TextView) itemView.findViewById(R.id.taskTxt);
    }
}
