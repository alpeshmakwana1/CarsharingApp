package com.alpesh.carsharingapp.data.model

data class User(
    private val userId: String?,
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

    fun toObject(hashMap: HashMap<String, String>): User {
        return User(
            hashMap.getOrElse("carId") { "" },
            hashMap.getOrElse("firstName") { "" },
            hashMap.getOrElse("lastName") { "" },
            hashMap.getOrElse("mobileNo") { "" },
            hashMap.getOrElse("aadharNo") { "" },
            hashMap.getOrElse("emailId") { "" },
            hashMap.getOrElse("password") { "" },
        )
    }
}
