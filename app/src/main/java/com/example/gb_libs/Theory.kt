package com.example.gb_libs

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Scope
import javax.inject.Singleton

class Author
class Book

interface IAuthorsRepository {
    fun getAuthors(): Single<List<Author>>
}

class AuthorsRepository : IAuthorsRepository {
    override fun getAuthors(): Single<List<Author>> {
        return Single.just(listOf(Author()))
    }
}

interface IBooksRepository {
    fun getBooks(): Single<List<Book>>
}

class BooksRepository : IBooksRepository {
    override fun getBooks(): Single<List<Book>> {
        return Single.just(listOf(Book()))
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthorsScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class BooksScope

@Module
open class AuthorsRepositoryModule {
    @AuthorsScope
    @Provides
    fun repo(): IAuthorsRepository {
        return AuthorsRepository()
    }
}

@Module
open class BooksRepositoryModule {
    @BooksScope
    @Provides
    fun repo(): IBooksRepository {
        return BooksRepository()
    }
}

@Subcomponent(
    modules = [AuthorsRepositoryModule::class]
)
@AuthorsScope
interface AuthorsSubcomponent {

    fun booksSubcomponent(): BooksSubcomponent

    fun inject(app: App)
}

@Subcomponent(
    modules = [BooksRepositoryModule::class]
)
@BooksScope
interface BooksSubcomponent {

    fun inject(app: App)
}

@Component
@Singleton
interface TestAppComponent {
    fun authorsSubcomponent(): AuthorsSubcomponent
}