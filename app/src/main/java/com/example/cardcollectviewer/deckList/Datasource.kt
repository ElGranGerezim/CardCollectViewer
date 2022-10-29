package com.example.cardcollectviewer.deckList

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Datasource(val context : Context) {
    fun getDeckListAll() : List<DocumentSnapshot>?{
        Log.d("Myself", "Connecting to Database")
        val db = Firebase.firestore
        var data : MutableList<DocumentSnapshot>? = mutableListOf()
        val decks = db.collection("decks").get()
        while(!decks.isComplete){}
        for (doc in decks.result){
            Log.d("Myself", "Adding document to Data")
            data?.add(doc)
            Log.d("Myself", "Data size is now ${data!!.size}")
        }

        Log.d("Myself", "Finished, returning $data")
        return data
    }

    fun getDeckListSimple() : List<DocumentSnapshot>?{
        Log.d("Myself", "Connecting to Database")
        val db = Firebase.firestore
        var data : MutableList<DocumentSnapshot>? = mutableListOf()
        val decks = db.collection("decks").whereEqualTo("Type", "Simple").get()

        while(!decks.isComplete){}
        for (doc in decks.result){
            Log.d("Myself", "Adding document to Data")
            data?.add(doc)
            Log.d("Myself", "Data size is now ${data!!.size}")
        }

        Log.d("Myself", "Finished, returning $data")
        return data
    }

    fun getDeckListDetailed() : List<DocumentSnapshot>?{
        Log.d("Myself", "Connecting to Database")
        val db = Firebase.firestore
        var data : MutableList<DocumentSnapshot>? = mutableListOf()
        val decks = db.collection("decks").whereEqualTo("Type", "Detailed").get()

        while(!decks.isComplete){}
        for (doc in decks.result){
            Log.d("Myself", "Adding document to Data")
            data?.add(doc)
            Log.d("Myself", "Data size is now ${data!!.size}")
        }

        Log.d("Myself", "Finished, returning $data")
        return data
    }
}