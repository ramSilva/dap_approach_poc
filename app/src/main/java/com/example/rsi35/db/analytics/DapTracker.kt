package com.example.rsi35.db.analytics

import android.content.Context
import android.util.Log
import com.example.rsi35.db.dbstuff.EventSender
import com.example.rsi35.db.model.pojo.DapEvent
import com.example.rsi35.db.model.pojo.Event
import io.reactivex.observers.DisposableObserver

class DapTracker(context: Context) {
    private val analytics = Analytics
    private lateinit var eventsDisposable: DisposableObserver<DapEvent>
    private var onPostEventCallback: (() -> Unit)? = null

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        eventsDisposable = analytics.eventsStream()
                .filter(this::acceptEvent)
                .doOnNext(this::logEvent)
                .map(this::transformEvent)
                .subscribeWith(object : DisposableObserver<DapEvent>() {
                    override fun onNext(dapEvent: DapEvent) {
                        postEvent(dapEvent)
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })

    }

    fun acceptEvent(event: Event): Boolean {
        return true
    }

    fun logEvent(event: Event) {
        Log.d("DapTracker", "event : " + event.key + " " + event.value)
    }

    fun transformEvent(event: Event): DapEvent {
        val dapEvent = DapEvent()
        dapEvent.eventJson = "{${event.key}:${event.value}}"
        return dapEvent
    }

    fun postEvent(event: DapEvent) {
        EventSender.sendEvents(1.0f, onPostEventCallback, onPostEventCallback)
    }

    fun thisIsJustADebuggingCallbackPlsDontKillMe(onPostEvent: (() -> Unit)?) {
        onPostEventCallback = onPostEvent
    }
}