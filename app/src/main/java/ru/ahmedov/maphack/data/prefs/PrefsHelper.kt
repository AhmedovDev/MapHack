package ru.ahmedov.maphack.data.prefs

import android.content.SharedPreferences

class PrefsHelper(private val sharedPrefs: SharedPreferences) {

    private val PREF_PAY_WAY = "pay way"
    private val PREF_ADDRESS = "address"
    private val PREF_TOKEN = "token"
    private val PREF_DISHCART = "dishCart"
    private val PREF_PHONE = "phone"
    private val PREF_DEVICE_ID = "device_id"
    private val PREF_STREET = "street"
    private val PREF_HOME = "home"
    private val PREF_PODEZD = "podezd"
    private val PREF_LEVEL = "level"
    private val PREF_OFICE = "ofice"
    private val PREF_COMMENT = "comment"


    fun getDeviceId(): String? = sharedPrefs.getString(PREF_DEVICE_ID, null)

    fun saveDeviceId(device_id: String) = sharedPrefs.edit()
        .putString(PREF_DEVICE_ID, device_id)
        .apply()

    fun clearDeviceId() = sharedPrefs.edit()
        .remove(PREF_DEVICE_ID)
        .apply()


    fun getPhone(): String? = sharedPrefs.getString(PREF_PHONE, null)

    fun savePhone(phone: String) = sharedPrefs.edit()
        .putString(PREF_PHONE, phone)
        .apply()

    fun clearPhone() = sharedPrefs.edit()
        .remove(PREF_PHONE)
        .apply()

    fun getCallDishCart(): Boolean? = sharedPrefs.getBoolean(PREF_DISHCART, true)

    fun saveCallDishCart(call: Boolean) = sharedPrefs.edit()
        .putBoolean(PREF_DISHCART, call)
        .apply()

    fun clearCallDishCart() = sharedPrefs.edit()
        .remove(PREF_DISHCART)
        .apply()


    fun getToken(): String? = sharedPrefs.getString(PREF_TOKEN, null)

    fun saveToken(token: String) = sharedPrefs.edit()
        .putString(PREF_TOKEN, token)
        .apply()

    fun clearToken() = sharedPrefs.edit()
        .remove(PREF_TOKEN)
        .apply()

    fun getPayWay(): String? = sharedPrefs.getString(PREF_PAY_WAY, null)

    fun savePayWay(payWay: String) = sharedPrefs.edit()
        .putString(PREF_PAY_WAY, payWay)
        .apply()

    fun clearPayWay() = sharedPrefs.edit()
        .remove(PREF_PAY_WAY)
        .apply()


    fun getAddresses(): String? = sharedPrefs.getString(PREF_ADDRESS,"null")

    fun saveAddress(address: String) = sharedPrefs.edit()
        .putString(PREF_ADDRESS,address)
        .apply()

    fun clearAddress()= sharedPrefs.edit()
        .remove(PREF_ADDRESS)
        .apply()

    fun getStreet(): String? = sharedPrefs.getString(PREF_STREET, null)

    fun saveStreet(street: String) = sharedPrefs.edit()
        .putString(PREF_STREET, street)
        .apply()

    fun clearStreet() = sharedPrefs.edit()
        .remove(PREF_STREET)
        .apply()

    fun getHome(): String? = sharedPrefs.getString(PREF_HOME, null)

    fun saveHome(home: String) = sharedPrefs.edit()
        .putString(PREF_HOME, home)
        .apply()

    fun clearHome() = sharedPrefs.edit()
        .remove(PREF_HOME)
        .apply()

    fun getPodezd(): String? = sharedPrefs.getString(PREF_PODEZD, null)

    fun savePodezd(podezd: String) = sharedPrefs.edit()
        .putString(PREF_PODEZD, podezd)
        .apply()

    fun clearPodezd() = sharedPrefs.edit()
        .remove(PREF_PODEZD)
        .apply()

    fun getLevel(): String? = sharedPrefs.getString(PREF_LEVEL, null)

    fun saveLevel(level: String) = sharedPrefs.edit()
        .putString(PREF_LEVEL, level)
        .apply()

    fun clearLevel() = sharedPrefs.edit()
        .remove(PREF_LEVEL)
        .apply()

    fun getOfice(): String? = sharedPrefs.getString(PREF_OFICE, null)

    fun saveOfice(ofice: String) = sharedPrefs.edit()
        .putString(PREF_OFICE, ofice)
        .apply()

    fun clearOfice() = sharedPrefs.edit()
        .remove(PREF_OFICE)
        .apply()


    fun getComment(): String? = sharedPrefs.getString(PREF_COMMENT, null)

    fun saveComment(comment: String) = sharedPrefs.edit()
        .putString(PREF_COMMENT, comment)
        .apply()

    fun clearComment() = sharedPrefs.edit()
        .remove(PREF_COMMENT)
        .apply()


}