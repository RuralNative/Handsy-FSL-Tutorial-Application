package com.ruralnative.handsy.ui.assessment_screen

data class AssessmentState(
    val questionNumber: Int = 0,
    val questionMedia: List<String> = listOf("letter_a", "letter_b", "letter_c"),
    val questionDescription: List<String> = listOf("alphabet_a_description", "alphabet_b_description", "alphabet_c_description"),
    val questionAnswer: List<String> = listOf("A", "B", "C"),
    val displayedMedia: String = "EMPTY",
    val displayedDescription: String = "EMPTY",
    val displayedAnswer: String = "EMPTY",
    val isAnswerCorrect: Boolean = true,
    val questionResultDescription: String = "SCORE: 0/3",
    val numberOfCorrectAnswers: Int = 0
)
