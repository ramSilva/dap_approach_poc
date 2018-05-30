package com.example.rsi35.db

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.rsi35.db.model.pojo.Event
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "events-db").build()
    }

    fun onSuccessfulClick(view: View) {

    }

    fun onUnsuccessfulClick(view: View) {
        val unsuccessfulCountText = unsuccessful_event_count.text.toString()
        val unsuccessfulCount: Int = if (unsuccessfulCountText == "") 1 else unsuccessfulCountText.toInt()

        doAsync {
            for (i in 1..unsuccessfulCount) {
                val event = Event()
                event.eventJson = "{eventnumber:$i}"
                db.eventDao().insertAll(event)

            }
            uiThread {
                displayCurrentEvents()
            }
        }
    }

    fun onDeleteEvents(view: View) {
        doAsync {
            db.eventDao().nukeTable()
            uiThread {
                displayCurrentEvents()
            }
        }
    }

    fun displayCurrentEvents() {
        events_list.text = ""
        doAsync {
            val events = db.eventDao().all
            uiThread {
                for (event in events) {
                    events_list.text = events_list.text.toString() + event.eventJson + "\n"
                }
            }
        }
    }
}
