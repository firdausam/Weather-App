package com.firdausam.mobile.myweather.data.models

interface IWeatherItem {
    fun getSrc(): String
    fun getTime(): String
    fun getDate(): String
    fun getMaxDegree(): String
    fun getMinDegree(): String
    fun getDescription(): String

    class Builder {
        private var src: String? = null
        private var time: String? = null
        private var date: String? = null
        private var maxDegree: String? = null
        private var minDegree: String? = null
        private var description : String? = null

        fun setSrc(src: String) = this.apply {
            this.src = src
        }

        fun setTime(time: String) = this.apply {
            this.time = time
        }

        fun setDate(date: String) = this.apply {
            this.date = date
        }

        fun setMaxDegree(maxDegree: String) = this.apply {
            this.maxDegree = maxDegree
        }

        fun setMinDegree(minDegree: String) = this.apply {
            this.minDegree = minDegree
        }

        fun setDescription(description : String) = this.apply {
            this.description = description
        }

        fun build() = object : IWeatherItem {
            override fun getSrc(): String = src ?: ""

            override fun getTime(): String = time ?: ""

            override fun getDate(): String = date ?: ""

            override fun getMaxDegree(): String = maxDegree ?: ""

            override fun getMinDegree(): String = minDegree ?: ""

            override fun getDescription(): String = description ?: ""
        }
    }
}