package com.horie1024.roomwordsample

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()
    val allWordsFlow: Flow<List<Word>> = wordDao.getAllWordsFlow()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}