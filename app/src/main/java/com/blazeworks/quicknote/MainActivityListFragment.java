package com.blazeworks.quicknote;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

        /* To create our context menu we need to register it for a view
         * so we tell it we want to register it for ListView.
         * Secondly it tells that if the user long presses on the listView
         * or the view we registered it for, it should call onCreateContextMenu()
         * callback which in turn inflates our menu items.
         */
        registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu , View view , ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(contextMenu , view , menuInfo);

        /* This inflates our menu layout and creates a context menu
         * with the items specified in the layout file
         */
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_context_ment , contextMenu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.edit:
                Log.d("Context Edit" , "We pressed edit");
                return true;
        }
        return super.onContextItemSelected(menuItem);
    }

    @Override
    public void onListItemClick(ListView listView , View view , int position , long id){
        super.onListItemClick(listView , view , position ,id);
        launchNoteDetailActivity(position);
    }

    private void launchNoteDetailActivity(int position){
        /* Grabs information of the note item we clicked on */
        Note note = (Note) getListAdapter().getItem(position);

        Intent intent = new Intent(getActivity() , NoteDetailActivity.class);

        /* Pass along the information associated with the particular note
         * to the NoteDetailActivity using the keys defined in MainActivity
         */
        intent.putExtra(MainActivity.NOTE_ID_EXTRA , note.getNoteId());
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA , note.getTitle());
        intent.putExtra(MainActivity.NOTE_BODY_EXTRA , note.getBody());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA , note.getCategory());

        startActivity(intent);
    }
}
