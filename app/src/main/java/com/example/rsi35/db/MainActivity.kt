package com.example.rsi35.db

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.rsi35.db.analytics.Analytics
import com.example.rsi35.db.analytics.DapTracker
import com.example.rsi35.db.dbstuff.DatabaseConnector
import com.example.rsi35.db.model.pojo.Event
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    var eventNr = 0
    lateinit var dapTracker: DapTracker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DatabaseConnector.create(applicationContext)
        dapTracker = DapTracker(applicationContext)
        dapTracker.thisIsJustADebuggingCallbackPlsDontKillMe({ displayCurrentEvents() })

        displayCurrentEvents()
    }

    fun onSendEventsClick(view: View) {
    }

    fun onAddEventsClick(view: View) {
        val unsuccessfulCountText = events_count.text.toString()
        val unsuccessfulCount: Int = if (unsuccessfulCountText == "") 1 else unsuccessfulCountText.toInt()

        doAsync {
            for (i in 1..unsuccessfulCount) {
                val event = Event("key", eventNr++.toString())
                Analytics.trackEvent(event)
                Thread.sleep(100)
                uiThread { displayCurrentEvents() }
            }

        }
    }

    fun onDeleteEventsClick(view: View) {
        doAsync {
            DatabaseConnector.getInstance().nukeTable()
            uiThread { displayCurrentEvents() }
        }
    }

    fun displayCurrentEvents() {
        events_list.text = ""
        doAsync {
            val events = DatabaseConnector.getInstance().allEvents
            uiThread {
                for (event in events) {
                    events_list.text = events_list.text.toString() + event.eventJson + "\n"
                }
                if (events.isEmpty()) {
                    events_list.text = "\n\n\nThis isn't empty.\n\nI mean, the table is empty, but not this label.\n\n\n\nJust thought you should know.\n\n\n\n\n\nK Bye."
                }
            }
        }
    }
}
