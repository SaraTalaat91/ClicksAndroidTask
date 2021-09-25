package com.saratms.clickstask.models

import java.io.Serializable

data class News(
    var title: String, var image: String, var source: String
) : Serializable