package com.tupaiaer.rqconnect

import android.app.Application
import com.tupaiaer.rqconnect.data.db.AppDatabase
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.NetworkConnectionInterceptor
import com.tupaiaer.rqconnect.data.preferences.PrefManager
import com.tupaiaer.rqconnect.data.repositories.*
import com.tupaiaer.rqconnect.ui.article.ArticleViewModelFactory
import com.tupaiaer.rqconnect.ui.auth.AuthViewModelFactory
import com.tupaiaer.rqconnect.ui.gallery.GalleryViewModelFactory
import com.tupaiaer.rqconnect.ui.gallery.detail.GalleryDetailViewModelFactory
import com.tupaiaer.rqconnect.ui.payment.PembayaranViewModelFactory
import com.tupaiaer.rqconnect.ui.payment.paymentConfirmation.ConfirmViewModelFactory
import com.tupaiaer.rqconnect.ui.payment.paymentSelection.PilihPembayaranViewModelFactory
import com.tupaiaer.rqconnect.ui.profile.ProfileViewModelFactory
import com.tupaiaer.rqconnect.ui.tahfidz.lajnah.LajnahViewModelFactory
import com.tupaiaer.rqconnect.ui.tahfidz.pekan.PekanViewModelFactory
import com.tupaiaer.rqconnect.ui.tahfidz.triwulan.TriwulanViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class RqconnectApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@RqconnectApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { PrefManager(instance()) }
        bind() from singleton { UserRepository(instance(), instance(), instance()) }
        bind() from singleton { ArticleRepository(instance()) }
        bind() from singleton { PekanRepository(instance()) }
        bind() from singleton { LajnahRepository(instance()) }
        bind() from singleton { TriwulanRepository(instance()) }
        bind() from singleton { LatePaymentRepository(instance()) }
        bind() from singleton { RemainPaymentRepository(instance()) }
        bind() from singleton { PaidOffPaymentRepository(instance()) }
        bind() from singleton { GalleryRepository(instance()) }
        bind() from singleton { GalleryDetailRepository(instance(), instance()) }
        bind() from singleton { ChosePaymentRepository(instance()) }
        bind() from singleton { ConfirmPaymentRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { ArticleViewModelFactory(instance()) }
        bind() from provider { PekanViewModelFactory(instance()) }
        bind() from provider { LajnahViewModelFactory(instance()) }
        bind() from provider { TriwulanViewModelFactory(instance()) }
        bind() from provider { GalleryViewModelFactory(instance()) }
        bind() from provider { GalleryDetailViewModelFactory(instance()) }
        bind() from provider { PembayaranViewModelFactory(instance(), instance()) }
        bind() from provider {
            PilihPembayaranViewModelFactory(
                instance()
            )
        }
        bind() from provider {
            ConfirmViewModelFactory(
                instance()
            )
        }

    }

    companion object {
        @get:Synchronized
        lateinit var instance: RqconnectApplication
        lateinit var prefManager: PrefManager
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefManager = PrefManager(this)
    }
}