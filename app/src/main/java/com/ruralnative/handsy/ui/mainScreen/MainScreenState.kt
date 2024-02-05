package com.ruralnative.handsy.ui.mainScreen

import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.PhrasesLesson

data class MainScreenState(
    val alphabetLessons: List<AlphabetLesson>? = null,
    val phrasesLesson: List<PhrasesLesson>? = null,
)