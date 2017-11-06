package com.blazeworks.quicknote;


import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityListFragment extends ListFragment {

    ArrayList<Note> notes;
    NoteArrayAdapter noteArrayAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        notes = new ArrayList<>();
        notes.add(new Note("Demonetisation is a fucking conspiracy" , "Modi fucked with 1.3 billion people. Demonetisation is the biggest scam ever in the history of India or the world." , Note.Category.FINANCE));
        notes.add(new Note("Vegita still can not go SSJ3" , "I still can not believe vegita can't got SSJ3 but can go SSJB , this sucks." , Note.Category.PERSONAL));
        notes.add(new Note("Kill it before it lays egg" , "You gotta save the universe from the abomination right." , Note.Category.QUOTE));
        notes.add(new Note("Jio is a joke" , "4G in india can't even let you stream a 480p video smoothly. Fuck this jio bullshit." , Note.Category.TECHNICAL));

        noteArrayAdapter = new NoteArrayAdapter(getActivity(),notes);
        setListAdapter(noteArrayAdapter);
    }

    @Override
    public void onListItemClick(ListView listView , View view , int position , long id){
        super.onListItemClick(listView , view , position ,id);
    }

}
