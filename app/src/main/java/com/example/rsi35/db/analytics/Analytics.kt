package com.example.rsi35.db.analytics

import com.example.rsi35.db.dbstuff.DatabaseConnector
import com.example.rsi35.db.model.pojo.Event
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object Analytics {
    private val eventPubSub: PublishSubject<Event> = PublishSubject.create()

    fun eventsStream(): Observable<Event> {
        return eventPubSub
    }

    fun trackEvent(event: Event) {
        DatabaseConnector.getInstance().insertAll(event)
        eventPubSub.onNext(event)
    }
}