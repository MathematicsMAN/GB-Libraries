package com.example.gb_libs

import android.app.Application
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs_lesson1.BuildConfig
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class App : Application() {
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigationHolder get() = cicerone.navigatorHolder
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        GitHubDatabase.create(this)

        //============
        val exampleComponent = DaggerExampleComponent.builder().build()
        val example = Example()
        exampleComponent.inject(example)

        Timber.d(example.a)
        Timber.d(example.b)

    }

    companion object {
        lateinit var instance: App
    }
}

// ==============================================

@Module
class ExampleModule {
    @Provides
    fun aExample():String{
        //Имя не принципиально. Главное возвращ.тип должен
        //соответствовать типу переменной с @Inject
        return "ABC"
    }
}

@Component(modules = [ExampleModule::class])
interface ExampleComponent {
    fun inject(e: Example)
}

class Example { //В этом классе в переменную заинъектить значение
    @Inject lateinit var a: String
    @Inject lateinit var b: String
}
