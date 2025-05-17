package by.emred.telegram.cloud

import java.io.Serializable

fun main() {
    val address = CurrentAddress(
        clientId = "5-OYZUP7MJ",
        name = "Менделеева, д. 11",
        value = "пер. Менделеева, д. 11, тер. СНТ Лето, г. Калининград, обл. Калининградская, Россия",
        country = "Россия",
        region = "обл. Калининградская",
        city = "г. Калининград",
        settlement = "тер. СНТ Лето",
        street = "пер. Менделеева",
        house = "11",
        latitude = 50.0,
        longitude = 50.0,
    )
    println(address.geocode())
}

fun CurrentAddress.geocode(): String {
    val area = this.area?.takeIf { it.isNotBlank() } ?: this.region?.takeIf { it.isNotBlank() }
    val settlement = this.settlement?.takeIf { it.isNotBlank() } ?: this.city?.takeIf { it.isNotBlank() }
    val house = "д. ${this.house}"
    val block = this.block?.takeIf { it.isNotBlank() }
    val street = this.street?.takeIf { it.isNotBlank() }
    return if ((area != null || settlement != null) && street != null) {
        listOfNotNull(area, settlement, street, house, block).joinToString(", ")
    } else {
        this.value
    }
}

data class CurrentAddress(
    var clientId: String,
    var name: String? = null,
    var value: String,
    var postCode: String? = null,
    var country: String? = null,
    var region: String? = null,
    var area: String? = null,
    var city: String? = null,
    var settlement: String? = null,
    var cityDistrict: String? = null,
    var street: String? = null,
    var house: String,
    var block: String? = null,
    var flat: String? = null,
    var doorphone: String? = null,
    var comment: String? = null,
    var latitude: Double,
    var longitude: Double,
    var doorway: String? = null,
    var storey: String? = null,
    var privateHouse: Boolean? = null
) : Serializable