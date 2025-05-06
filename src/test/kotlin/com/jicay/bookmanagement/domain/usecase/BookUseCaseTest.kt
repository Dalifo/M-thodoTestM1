package com.jicay.bookmanagement.domain.usecase

import com.jicay.bookmanagement.domain.model.Book
import com.jicay.bookmanagement.domain.port.BookPort
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify

class BookUseCaseTest : FunSpec({

    val bookPort = mockk<BookPort>()
    val bookUseCase = BookUseCase(bookPort)

    test("get all books should returns all books sorted by name") {
        every { bookPort.getAllBooks() } returns listOf(
            Book("Les Misérables", "Victor Hugo"),
            Book("Hamlet", "William Shakespeare")
        )

        val res = bookUseCase.getAllBooks()

        res.shouldContainExactly(
            Book("Hamlet", "William Shakespeare"),
            Book("Les Misérables", "Victor Hugo")
        )
    }

    test("add book") {
        justRun { bookPort.createBook(any()) }

        val book = Book("Les Misérables", "Victor Hugo")

        bookUseCase.addBook(book)

        verify(exactly = 1) { bookPort.createBook(book) }
    }

    test("reserve ok") {
        val port = mockk<BookPort> {
            every { getAllBooks() } returns listOf(Book("Hamlet", "Shakespeare"))
            justRun { reserveBook("Hamlet") }
        }
        BookUseCase(port).reserveBook("Hamlet")
        verify { port.reserveBook("Hamlet") }
    }

    test("reserve twice -> error") {
        val port = mockk<BookPort> {
            every { getAllBooks() } returns listOf(Book("Hamlet", "S.", true))
        }
        shouldThrow<IllegalStateException> { BookUseCase(port).reserveBook("Hamlet") }
    }
})