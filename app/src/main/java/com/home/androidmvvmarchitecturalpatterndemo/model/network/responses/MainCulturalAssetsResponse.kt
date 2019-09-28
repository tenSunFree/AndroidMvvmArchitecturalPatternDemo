package com.home.androidmvvmarchitecturalpatterndemo.model.network.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MainCulturalAssetsResponse(
    @SerializedName("result")
    val result: Result
) : Serializable {

    data class Result(
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("offset")
        val offset: Int,
        @SerializedName("count")
        val count: Int,
        @SerializedName("sort")
        val sort: String,
        @SerializedName("results")
        val results: List<Results>
    ) : Serializable {

        data class Results(
            @SerializedName("pic96_url")
            val pic96Url: String,
            @SerializedName("laws_reference")
            val lawsReference: String,
            @SerializedName("land_scope")
            val landScope: String,
            @SerializedName("building_actual_state")
            val buildingActualState: String,
            @SerializedName("building_brief")
            val buildingBrief: String,
            @SerializedName("longitude")
            val longitude: String,
            @SerializedName("case_name")
            val caseName: String,
            @SerializedName("assets_type_code")
            val assetsTypeCode: String,
            @SerializedName("case_id")
            val caseId: String,
            @SerializedName("official_doc_no")
            val officialDocNo: String,
            @SerializedName("belong_address")
            val belongAddress: String,
            @SerializedName("latitude")
            val latitude: String,
            @SerializedName("belong_city_name")
            val belongCityName: String,
            @SerializedName("_id")
            val id: Int,
            @SerializedName("register_reason")
            val registerReason: String,
            @SerializedName("register_date")
            val registerDate: String,
            @SerializedName("page_url")
            val pageUrl: String
        ) : Serializable
    }
}