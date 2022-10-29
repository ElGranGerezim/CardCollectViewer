package com.example.cardcollectviewer.deckList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cardcollectviewer.R
import com.google.firebase.firestore.DocumentSnapshot


/*
* Adapter class for Decks
* Translates the Document Snapshots into views that
* the RecyclerView can use to display all of them
* */
class DeckAdapter(private val dataSet: List<DocumentSnapshot>?) : RecyclerView.Adapter<DeckAdapter.ViewHolder>() {

    /* ViewHolder for deck view */
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val textView : TextView
        init {
            textView = view.findViewById(R.id.deckText)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.deck_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        if (dataSet != null){
            Log.d("Myself", "Got an element, putting ${dataSet[position].id} in")
            viewHolder.textView.text = dataSet[position].id
        }
        else {
            viewHolder.textView.text = "Error"
        }
    }

    // Return the size of dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet!!.size
}