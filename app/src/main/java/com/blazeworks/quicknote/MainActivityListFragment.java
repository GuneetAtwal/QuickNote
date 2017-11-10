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
import android.widget.AdapterView;
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

        /* Retrieve notes from our database */
        QuickNoteDbAdapter noteDb = new QuickNoteDbAdapter(getActivity().getBaseContext());
        noteDb.openDatabase();
        notes = noteDb.getAllNotes();
        noteDb.closeDatabase();

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

        /* gives us the position of whatever the note item we long pressed on */
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        int rowPosition = adapterContextMenuInfo.position;

        switch (menuItem.getItemId()){
            case R.id.edit:
                launchNoteDetailActivity(MainActivity.FragmentToLaunch.EDIT , rowPosition);
                return true;
        }

        return super.onContextItemSelected(menuItem);
    }

    @Override
    public void onListItemClick(ListView listView , View view , int position , long id){
        super.onListItemClick(listView , view , position ,id);
        launchNoteDetailActivity(MainActivity.FragmentToLaunch.VIEW , position);
    }

    private void launchNoteDetailActivity(MainActivity.FragmentToLaunch fragmentToLaunch ,int position){
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

        /* Switch case to switch between which extra's to send on the basis of ENUM type */
        switch (fragmentToLaunch){
            case VIEW:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LAUNCH_EXTRA , MainActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LAUNCH_EXTRA , MainActivity.FragmentToLaunch.EDIT);
                break;
        }

        startActivity(intent);
    }
}
