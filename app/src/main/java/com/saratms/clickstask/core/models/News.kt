package com.saratms.clickstask.core.models

import java.io.Serializable

data class News(
    var title: String, var image: String, var source: String
) : Serializable