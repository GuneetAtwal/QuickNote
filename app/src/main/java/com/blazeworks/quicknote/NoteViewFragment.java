package com.blazeworks.quicknote;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteViewFragment extends Fragment {


    public NoteViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View noteViewFragmentLayout = inflater.inflate(R.layout.fragment_note_view, container , false);

        TextView noteTitle = noteViewFragmentLayout.findViewById(R.id.view_item_note_title);
        TextView noteBody = noteViewFragmentLayout.findViewById(R.id.view_item_note_body);
        ImageView noteImage = noteViewFragmentLayout.findViewById(R.id.view_item_note_image);

        Intent intent = getActivity().getIntent();

        noteTitle.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
        noteBody.setText(intent.getExtras().getString(MainActivity.NOTE_BODY_EXTRA));

        /* Note.Category is passed as a serialized object so we get it as that
         * Then we use the Note class's static method categoryToDrawable() to
         * get the image drawable.
         */
        Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);
        noteImage.setImageDrawable(Note.categoryToDrawable(noteCat));

        /* Inflate the layout for this fragment */
        return noteViewFragmentLayout;
    }

}