package com.blazeworks.quicknote;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {


    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View editFragmentLayout = inflater.inflate(R.layout.fragment_note_edit , container,false);

        EditText noteTitle = editFragmentLayout.findViewById(R.id.edit_item_note_title);
        EditText noteBody = editFragmentLayout.findViewById(R.id.edit_item_note_body);
        ImageButton noteImageButton = editFragmentLayout.findViewById(R.id.edit_item_note_image_button);

        Intent intent = getActivity().getIntent();

        noteTitle.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
        noteBody.setText(intent.getExtras().getString(MainActivity.NOTE_BODY_EXTRA));

        /* Note.Category is passed as a serialized object so we get it as that
         * Then we use the Note class's static method categoryToDrawable() to
         * get the image drawable.
         */
        Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);
        noteImageButton.setImageDrawable(Note.categoryToDrawable(noteCat));
        
        return editFragmentLayout;
    }

}
