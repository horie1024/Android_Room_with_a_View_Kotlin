package com.horie1024.roomwordsample

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository
    val allWords: LiveData<List<Word>>

    init {

        val wordDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordDao)
        allWords = repository.allWords

        viewModelScope.launch {
            repository.allWordsFlow.collect {
                it.forEach {word ->
                    Log.i("test", word.word)
                }
            }
        }
    }

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}