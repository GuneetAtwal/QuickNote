package com.blazeworks.quicknote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by GuneetAtwal on 11/6/2017.
 */

public class NoteArrayAdapter extends ArrayAdapter<Note> {

    private static class ViewHolder {
        TextView noteTitle;
        TextView noteBody;
        ImageView noteIcon;
    }

    public NoteArrayAdapter(Context context , ArrayList<Note> notes){
        super(context , 0 , notes);
    }

    /**
     *
     * @param position position of the row in the list
     * @param convertView View associate with the row. When it get out of the view, android save it
     * @param parent it's the layout
     * @return convertView
     */
    @Override
    public View getView(int position , View convertView , ViewGroup parent){
        /* Get the particular note item for this position */
        Note note = getItem(position);
        ViewHolder viewHolder;

        /* Check if an existing view is being reused otherwise inflate a new view */
        if(convertView == null){

            /* if there is no view that is being reused then create and one
             * and crete viewHolder object to save our references to.
             */
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout , parent ,false);

            /* set our references to viewHolder so that we do not have to use
             * findViewById() every time we have a new row which is expensive.
             */
            viewHolder.noteTitle = (TextView) convertView.findViewById(R.id.list_item_note_title);
            viewHolder.noteBody = (TextView) convertView.findViewById(R.id.list_item_note_body);
            viewHolder.noteIcon = (ImageView) convertView.findViewById(R.id.list_item_note_image);
            convertView.setTag(viewHolder);
        } else {

            /* if we already have a view then grab the references
             * to our widgets from the viewHolder.
             */
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /* update our widgets with the appropriate data of the note. */
        viewHolder.noteTitle.setText(note.getTitle());
        viewHolder.noteBody.setText(note.getBody());
        viewHolder.noteIcon.setImageDrawable(note.getNoteIcon());

        return convertView;
    }
}
