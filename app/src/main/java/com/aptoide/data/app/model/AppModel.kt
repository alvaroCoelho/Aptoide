package com.aptoide.data.app.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AppModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("package")
    val appPackageName: String,
    @SerializedName("store_id")
    val storeId: Int,
    @SerializedName("store_name")
    val storeName: String,
    @SerializedName("vername")
    val verName: String,
    @SerializedName("vercode")
    val verCode: Int,
    @SerializedName("md5sum")
    val md5Sum: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("downloads")
    val downloads: Int,
    @SerializedName("pdownloads")
    val pDownloads: Int,
    @SerializedName("added")
    val added: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("graphic")
    val graphic: String,
    @SerializedName("uptype")
    val upType: String
): Serializable


data class AppModelList(
    @SerializedName("list")
    val list: List<AppModel>
): Serializable

data class Data(
    @SerializedName("data")
    val appModelList: AppModelList
): Serializable


data class AppModelAll(
    @SerializedName("all")
    val data: Data
): Serializable

data class DataSet(
    @SerializedName("datasets")
    val appModelAll: AppModelAll
): Serializable

data class ListApps(
    @SerializedName("listApps")
    val dataSet: DataSet
): Serializable

data class AppModelReponse(
    @SerializedName("responses")
    val listApps: ListApps
): Serializable



