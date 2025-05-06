package com.jicay.bookmanagement.domain.domain

import com.jicay.bookmanagement.domain.model.Book
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BookTest : FunSpec({

    test("name should not be empty") {
        shouldThrow<IllegalArgumentException> { Book("", "Victor Hugo") }
    }

    test("author should not be empty") {
        shouldThrow<IllegalArgumentException> { Book("Les Misérables", "") }
    }

    test("reserve a non-reserved book should succeed") {
        val book = Book("Test Title", "Test Author", isReserved = false)
        val reservedBook = book.reserve()
        reservedBook.isReserved shouldBe true
    }

    test("reserving an already reserved book should throw") {
        val book = Book("Test Title", "Test Author", isReserved = true)
        shouldThrow<IllegalStateException> {
            book.reserve()
        }
    }

    test("book should be equal to itself after copy") {
        val book = Book("Title", "Author")
        val copied = book.copy()
        copied shouldBe book
    }

    test("book with different reservation states should not be equal") {
        val available = Book("Book A", "Author", false)
        val reserved = Book("Book A", "Author", true)
        (available == reserved) shouldBe false
    }

})