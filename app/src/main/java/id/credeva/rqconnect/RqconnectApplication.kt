package id.credeva.rqconnect

import android.app.Application
import id.credeva.rqconnect.data.db.AppDatabase
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.NetworkConnectionInterceptor
import id.credeva.rqconnect.data.preferences.PrefManager
import id.credeva.rqconnect.data.repositories.*
import id.credeva.rqconnect.ui.article.ArticleViewModelFactory
import id.credeva.rqconnect.ui.auth.AuthViewModelFactory
import id.credeva.rqconnect.ui.gallery.GalleryViewModelFactory
import id.credeva.rqconnect.ui.gallery.detail.GalleryDetailViewModelFactory
import id.credeva.rqconnect.ui.payment.PembayaranViewModelFactory
import id.credeva.rqconnect.ui.payment.paymentConfirmation.ConfirmViewModelFactory
import id.credeva.rqconnect.ui.payment.paymentConfirmation.deposit.DepositViewModelFactory
import id.credeva.rqconnect.ui.payment.paymentConfirmation.infaq.InfaqViewModelFactory
import id.credeva.rqconnect.ui.payment.paymentSelection.PilihPembayaranViewModelFactory
import id.credeva.rqconnect.ui.profile.ProfileViewModelFactory
import id.credeva.rqconnect.ui.tahfidz.lajnah.LajnahViewModelFactory
import id.credeva.rqconnect.ui.tahfidz.pekan.PekanViewModelFactory
import id.credeva.rqconnect.ui.tahfidz.triwulan.TriwulanViewModelFactory
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
        bind() from singleton { DepositPaymentRepository(instance()) }
        bind() from singleton { InfaqPaymentRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { ArticleViewModelFactory(instance()) }
        bind() from provider { PekanViewModelFactory(instance()) }
        bind() from provider { LajnahViewModelFactory(instance()) }
        bind() from provider { TriwulanViewModelFactory(instance()) }
        bind() from provider { GalleryViewModelFactory(instance()) }
        bind() from provider { DepositViewModelFactory(instance()) }
        bind() from provider { InfaqViewModelFactory(instance()) }
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