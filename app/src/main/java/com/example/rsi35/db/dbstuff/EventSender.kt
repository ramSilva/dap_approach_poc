package com.example.rsi35.db.dbstuff

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

object EventSender {

    private var isSendingEvents = false

    fun sendEvents(successPercentage: Float = 0.9F, onEventSuccess: (() -> Unit)?, onEventFailure: (() -> Unit)?) {
        if (!isSendingEvents) {
            isSendingEvents = true
            doAsync {
                var events = DatabaseConnector.getInstance().allEvents
                while (!events.isEmpty()) {
                    for (event in events) {
                        Thread.sleep(1000)
                        val random = Random()
                        if (random.nextInt(10) < successPercentage * 10) {
                            DatabaseConnector.getInstance().deleteEvent(event)
                            uiThread {
                                onEventSuccess?.invoke()
                            }
                        } else {
                            onEventFailure?.invoke()
                            break
                        }
                    }
                    events = DatabaseConnector.getInstance().allEvents
                }
                isSendingEvents = false
            }
        }
    }
}
