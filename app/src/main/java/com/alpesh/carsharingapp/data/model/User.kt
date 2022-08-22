package com.alpesh.carsharingapp.data.model

data class User(
    private val userId: String = "0",
    private val firstName: String,
    private val lastName: String,
    private val mobileNo: String,
    private val emailId: String,
    private val aadharNo: String,
    private val password: String
) : BaseModel() {

    fun getMobileNumber(): String {
        return this.mobileNo
    }

    fun getUserId(): String {
        return this.userId
    }

    fun toHashMap(): HashMap<String, String> {
        var hashMap = HashMap<String, String>();

        hashMap.apply {
            put("firstName", firstName)
            put("lastName", lastName)
            put("mobileNo", mobileNo)
            put("aadharNo", aadharNo)
            put("emailId", emailId)
            put("password", password)
        }

        return hashMap
    }

    companion object {
        fun toObject(hashMap: MutableMap<String, Any>): User {
            return User(
                hashMap.getOrElse("carId") { "" }.toString(),
                hashMap.getOrElse("firstName") { "" }.toString(),
                hashMap.getOrElse("lastName") { "" }.toString(),
                hashMap.getOrElse("mobileNo") { "" }.toString(),
                hashMap.getOrElse("aadharNo") { "" }.toString(),
                hashMap.getOrElse("emailId") { "" }.toString(),
                hashMap.getOrElse("password") { "" }.toString(),
            )
        }
    }
}
