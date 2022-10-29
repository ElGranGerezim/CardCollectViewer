package com.example.cardcollectviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cardcollectviewer.deckList.Datasource
import com.example.cardcollectviewer.deckList.DeckAdapter
import com.google.firebase.firestore.DocumentSnapshot
import org.w3c.dom.Document

class MainActivity : AppCompatActivity() {
    // Enum to simplify application state
    enum class RefreshType {ALL, DETAILED, SIMPLE}
    var rType : RefreshType = RefreshType.ALL

    // Initialize Activity variables
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onRefresh(findViewById(R.id.refresh_button))
    }

    // Called by Radio Buttons, changes state to let us know which type of deck to display
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_detailed ->
                    if (checked) {
                        rType = RefreshType.DETAILED
                    }
                R.id.radio_simple ->
                    if (checked) {
                        rType = RefreshType.SIMPLE
                    }
                R.id.radio_all ->
                    if (checked){
                        rType = RefreshType.ALL
                    }
            }
        }
    }

    // Called by refresh button, updates RecyclerView and Header
    fun onRefresh(view: View){
        var deckList : List<DocumentSnapshot>? = null
        val data = Datasource(this)
        when(rType){
            RefreshType.ALL ->{
                deckList = data.getDeckListAll()
            }
            RefreshType.SIMPLE ->{
                deckList = data.getDeckListSimple()
            }
            RefreshType.DETAILED ->{
                deckList = data.getDeckListDetailed()
            }
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = DeckAdapter(deckList)
        findViewById<TextView>(R.id.textView).text = "Number of decks: ${if(deckList?.size != null) deckList.size else "0"}"
    }
}