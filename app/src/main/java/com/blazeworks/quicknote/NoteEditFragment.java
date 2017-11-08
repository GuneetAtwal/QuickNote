package com.blazeworks.quicknote;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {

    private EditText noteTitle , noteBody;
    private ImageButton noteImageButton;
    private AlertDialog alertDialogObject , confirmDialogObject;
    private Note.Category savedNoteCategory;

    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View editFragmentLayout = inflater.inflate(R.layout.fragment_note_edit , container,false);

        noteTitle = editFragmentLayout.findViewById(R.id.edit_item_note_title);
        noteBody = editFragmentLayout.findViewById(R.id.edit_item_note_body);
        noteImageButton = editFragmentLayout.findViewById(R.id.edit_item_note_image_button);
        Button saveNoteButton = editFragmentLayout.findViewById(R.id.edit_item_note_save_button);

        Intent intent = getActivity().getIntent();

        noteTitle.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
        noteBody.setText(intent.getExtras().getString(MainActivity.NOTE_BODY_EXTRA));

        /* Note.Category is passed as a serialized object so we get it as that
         * Then we use the Note class's static method categoryToDrawable() to
         * get the image drawable.
         */
        Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);
        savedNoteCategory = noteCat;
        noteImageButton.setImageDrawable(Note.categoryToDrawable(noteCat));

        /* This method builds alertDialogBox to change categories */
        buildCategoryPickerDialog();

        /* This method builds a confirm DialogBox to confirm
         * if the user wants to save their changes or not
         */
        buildConfirmDialog();

        /* Show the category picker onClicking the ImageButton */
        noteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogObject.show();
            }
        });

        /* Show the confirm dialogBox onClicking the Save Button */
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialogObject.show();
            }
        });

        return editFragmentLayout;
    }

    private void buildCategoryPickerDialog(){
        final String[] categories= {"Personal" , "Finance" , "Technical" , "Quote"};
        AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle("Choose Note Type");
        categoryBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item){
                    case 0:
                        savedNoteCategory = Note.Category.PERSONAL;
                        noteImageButton.setImageDrawable(Note.categoryToDrawable(savedNoteCategory));
                        break;
                    case 1:
                        savedNoteCategory = Note.Category.FINANCE;
                        noteImageButton.setImageDrawable(Note.categoryToDrawable(savedNoteCategory));
                        break;
                    case 2:
                        savedNoteCategory = Note.Category.TECHNICAL;
                        noteImageButton.setImageDrawable(Note.categoryToDrawable(savedNoteCategory));
                        break;
                    case 3:
                        savedNoteCategory = Note.Category.QUOTE;
                        noteImageButton.setImageDrawable(Note.categoryToDrawable(savedNoteCategory));
                        break;
                }
                /* It makes the alert dialog go when we click on any item in the alert dialog */
                alertDialogObject.cancel();
            }
        });

        alertDialogObject = categoryBuilder.create();
    }

    private void buildConfirmDialog(){
        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getActivity());
        confirmBuilder.setTitle("Are you sure?");
        confirmBuilder.setMessage("Once done changes cannot be undone");

        confirmBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Save Note Button","New Title" + noteTitle.getText()+ " New Body" + noteBody.getText() + " New Category" + savedNoteCategory);
                /* On Clicking confirm get the user back to Main Activity */
                Intent intent = new Intent(getActivity() , MainActivity.class);
                startActivity(intent);
            }
        });

        confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /* On pressing this button do nothing */
            }
        });

        confirmDialogObject = confirmBuilder.create();
    }

}
