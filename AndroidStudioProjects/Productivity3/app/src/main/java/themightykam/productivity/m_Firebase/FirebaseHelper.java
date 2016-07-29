package themightykam.productivity.m_Firebase;

import com.google.android.gms.common.api.BooleanResult;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import themightykam.productivity.m_Model.UserTasks;

/**
 * Created by Kamoya on 7/20/16.
 */
public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved=null;
    ArrayList<String> userTasks=new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    // Save
    public Boolean save(UserTasks userTask) {
        if (userTask == null) {
            saved = false;
        } else {
            try {
                db.child("UserTasks").push().setValue(userTask);
                saved = true;
            } catch (DatabaseException e) {
                e.printStackTrace();
                saved = false;
            }

        }
        return saved;
    }

    // Read
    public ArrayList<String> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return userTasks;
    }

    private void fetchData(DataSnapshot dataSnapshot) {
        userTasks.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String task = ds.getValue(UserTasks.class).getTask();
            userTasks.add(task);
        }
    }
}
