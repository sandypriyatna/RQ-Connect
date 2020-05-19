package id.credeva.rqconnect.data.preferences

import android.content.Context
import android.content.SharedPreferences

private const val PREFS_NAME = "rq_connect"
private const val KEY_TOKEN = "key_token"
private const val KEY_GALLERY_ID = "key_gallery_id"
private const val KEY_INTRO_SLIDER = "key_intro_slider"
private const val KEY_REFF_KEY = "key_reff_key"
private const val KEY_TOTAL_PAYMENT = "key_total_payment"
private const val KEY_DUE_DATE_PAY = "key_due_date_pay"
private const val KEY_REK = "key_rek"
private const val KEY_REK_NUMBER = "key_rek_number"
private const val KEY_REK_NAME = "key_rek_name"
private const val KEY_NAME = "key_name"
private const val KEY_NIS = "key_nis"
private const val KEY_AVATAR = "key_avatar"
private const val KEY_JUZ = "key_juz"
private const val KEY_ID_PAYMENT = "key_id_payment"
private const val KEY_EVIDENCE = "key_evidence"
private const val KEY_TEMP_CODE = "key_temp_code"
private const val KEY_TEMP_TOTAL = "key_temp_total"
private const val KEY_STATUS_PAYMENT = "key_status_payment"

class PrefManager(context: Context) {

    private val sp: SharedPreferences by lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    val spe: SharedPreferences.Editor by lazy {
        sp.edit()
    }

    fun clear() {
        sp.edit().clear().commit()
    }

    fun deleteSpName() {
        sp.edit().remove(KEY_NAME).commit();
    }

    var spToken: String?
        get() = sp.getString(KEY_TOKEN, "")
        set(value) {
            spe.putString(KEY_TOKEN, value)
            spe.commit()
        }

    var spIdGallery: Int
        get() = sp.getInt(KEY_GALLERY_ID, 0)
        set(value) {
            if (value != null) {
                spe.putInt(KEY_GALLERY_ID, value)
            }
            spe.commit()
        }

    var spCheckIntroSlider: Boolean
        get() = sp.getBoolean(KEY_INTRO_SLIDER, false)
        set(value) {
            spe.putBoolean(KEY_INTRO_SLIDER, value)
            spe.commit()
        }

    var spIdPayment: String?
        get() = sp.getString(KEY_ID_PAYMENT, "")
        set(value) {
            spe.putString(KEY_ID_PAYMENT, value)
            spe.commit()
        }

    var spRefKey: String?
        get() = sp.getString(KEY_REFF_KEY, "")
        set(value) {
            spe.putString(KEY_REFF_KEY, value)
            spe.commit()
        }

    var spTotalPayment: String?
        get() = sp.getString(KEY_TOTAL_PAYMENT, "")
        set(value) {
            spe.putString(KEY_TOTAL_PAYMENT, value)
            spe.commit()
        }

    var spDueDatePay: String?
        get() = sp.getString(KEY_DUE_DATE_PAY, "")
        set(value) {
            spe.putString(KEY_DUE_DATE_PAY, value)
            spe.commit()
        }

    var spRekNumber: String?
        get() = sp.getString(KEY_REK_NUMBER, "")
        set(value) {
            spe.putString(KEY_REK_NUMBER, value)
            spe.commit()
        }

    var spRekName: String?
        get() = sp.getString(KEY_REK_NAME, "")
        set(value) {
            spe.putString(KEY_REK_NAME, value)
            spe.commit()
        }

    var spRek: String?
        get() = sp.getString(KEY_REK, "")
        set(value) {
            spe.putString(KEY_REK, value)
            spe.commit()
        }

    var spName: String?
        get() = sp.getString(KEY_NAME, "")
        set(value) {
            spe.putString(KEY_NAME, value)
            spe.commit()
        }

    var spNis: String?
        get() = sp.getString(KEY_NIS, "")
        set(value) {
            spe.putString(KEY_NIS, value)
            spe.commit()
        }

    var spAvatar: String?
        get() = sp.getString(KEY_AVATAR, "")
        set(value) {
            spe.putString(KEY_AVATAR, value)
            spe.commit()
        }

    var spJuz: String?
        get() = sp.getString(KEY_JUZ, "")
        set(value) {
            spe.putString(KEY_JUZ, value)
            spe.commit()
        }

    var spEvidence: String?
        get() = sp.getString(KEY_EVIDENCE, null)
        set(value) {
            spe.putString(KEY_EVIDENCE, value)
            spe.commit()
        }

    var spStatusPayment: String?
        get() = sp.getString(KEY_STATUS_PAYMENT, null)
        set(value) {
            spe.putString(KEY_STATUS_PAYMENT, value)
            spe.commit()
        }

    var spTempRefKey: String?
        get() = sp.getString(KEY_TEMP_CODE, null)
        set(value) {
            spe.putString(KEY_TEMP_CODE, value)
            spe.commit()
        }

    var spTempPayTotal: String?
        get() = sp.getString(KEY_TEMP_TOTAL, null)
        set(value) {
            spe.putString(KEY_TEMP_TOTAL, value)
            spe.commit()
        }
}
