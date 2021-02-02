package com.example.moviedirectory.Model

import java.io.Serializable

class Movie : Serializable {
    var title: String? = null
    var year: String? = null
    var imdbID: String? = null
    var poster: String? = null
    var movieType: String? = null
    private var backGround: String? = null
    fun setBackGround(backGround: String?) {
        this.backGround = backGround
    }

    companion object {
        private const val id = 1L
    }
}