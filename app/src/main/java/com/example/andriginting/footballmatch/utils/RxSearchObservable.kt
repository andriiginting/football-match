package com.example.andriginting.footballmatch.utils

import android.widget.SearchView
import rx.Observable
import rx.subjects.PublishSubject

class RxSearchObservable {
    companion object {
        fun fromView(searchView: SearchView): Observable<String> {
            val subject: PublishSubject<String> = PublishSubject.create()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subject.onCompleted()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    subject.onNext(newText)
                    return true
                }

            })

            return subject
        }
    }
}