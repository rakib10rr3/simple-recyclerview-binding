package com.rakib.recyclerviewsample

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class User(
    @Json(name = "address")
    var address: Address?,
    @Json(name = "company")
    var company: Company?,
    @Json(name = "email")
    var email: String?, // Rey.Padberg@karina.biz
    @Json(name = "id")
    var id: Int?, // 10
    @Json(name = "name")
    var name: String?, // Clementina DuBuque
    @Json(name = "phone")
    var phone: String?, // 024-648-3804
    @Json(name = "username")
    var username: String?, // Moriah.Stanton
    @Json(name = "website")
    var website: String? // ambrose.net
) {
    @Keep
    data class Address(
        @Json(name = "city")
        var city: String?, // Lebsackbury
        @Json(name = "geo")
        var geo: Geo?,
        @Json(name = "street")
        var street: String?, // Kattie Turnpike
        @Json(name = "suite")
        var suite: String?, // Suite 198
        @Json(name = "zipcode")
        var zipCode: String? // 31428-2261
    ) {
        @Keep
        data class Geo(
            @Json(name = "lat")
            var lat: String?, // -38.2386
            @Json(name = "lng")
            var lng: String? // 57.2232
        )
    }

    @Keep
    data class Company(
        @Json(name = "bs")
        var bs: String?, // target end-to-end models
        @Json(name = "catchPhrase")
        var catchPhrase: String?, // Centralized empowering task-force
        @Json(name = "name")
        var name: String? // Hoeger LLC
    )
}