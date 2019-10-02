package mbitsystem.com.postsviewer.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("username")
    val userName: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("address")
    val address: Address,
    @JsonProperty("phone")
    val phone: String,
    @JsonProperty("website")
    val webiste: String,
    @JsonProperty("company")
    val company: Company
)


data class Company(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("catchPhrase")
    val catchPhrase: String,
    @JsonProperty("bs")
    val bs: String
)

data class Geo(
    @JsonProperty("lat")
    val lat: String,
    @JsonProperty("lng")
    val lng: String
)

data class Address(
    @JsonProperty("street")
    val street: String,
    @JsonProperty("suite")
    val suite: String,
    @JsonProperty("city")
    val city: String,
    @JsonProperty("zipcode")
    val zipcode: String,
    @JsonProperty("geo")
    val geo: Geo
)