package com.example.andriginting.footballmatch.utils

class StringsUtils {
    companion object {
        fun count(text: String): Int? {
            val ch = ';'
            var frequency = 0
            return if (text.isBlank() || text.isNullOrEmpty()) {
                0
            } else {
                for (i in 0 until text.length) {
                    if (ch == text[i]) {
                        ++frequency
                    }
                }
                frequency
            }
        }

        fun transformNull(text: Int): String{
            return if (text == null){
                "-"
            }else{
                text.toString()
            }
        }
    }
}