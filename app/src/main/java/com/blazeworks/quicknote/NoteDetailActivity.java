package com.blazeworks.quicknote;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteDetailActivity extends AppCompatActivity {

    public static final String NEW_NOTE_EXTRA = "NEW_NOTE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        createAndAddFragment();
    }
    private void createAndAddFragment(){
        /* get the type of fragment */
        Intent intent = getIntent();
        MainActivity.FragmentToLaunch fragmentToLaunch =
                (MainActivity.FragmentToLaunch) intent.getSerializableExtra(MainActivity.NOTE_FRAGMENT_TO_LAUNCH_EXTRA);

        /* Fragment transaction starts from here */
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        /* which fragment to load is decided here */
        switch (fragmentToLaunch){
            case CREATE:
                setTitle(R.string.note_create_title);
                NoteEditFragment noteCreateFragment = new NoteEditFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean(NEW_NOTE_EXTRA,true);
                noteCreateFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.note_container , noteCreateFragment , "NOTE_CREATE_FRAGMENT");
                break;
            case VIEW:
                setTitle(R.string.note_view_title);
                fragmentTransaction.add(R.id.note_container , new NoteViewFragment() , "NOTE_VIEW_FRAGMENT");
                break;
            case EDIT:
                setTitle(R.string.note_edit_title);
                fragmentTransaction.add(R.id.note_container , new NoteEditFragment() , "NOTE_EDIT_FRAGMENT");
                break;
        }
        fragmentTransaction.commit();
        /* fragment transaction ends here */


    }
}
