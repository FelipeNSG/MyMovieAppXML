package com.example.mymovieappxml.network.model
import com.google.gson.annotations.SerializedName

data class Dates(
    val maximum: String?,
    val minimum: String?
)

data class MovieListsWithDate(
    @SerializedName("dates")
    val dates: Dates? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<Result?>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)