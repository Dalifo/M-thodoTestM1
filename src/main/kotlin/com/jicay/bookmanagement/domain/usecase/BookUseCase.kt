package com.jicay.bookmanagement.domain.usecase

import com.jicay.bookmanagement.domain.model.Book
import com.jicay.bookmanagement.domain.port.BookPort

class BookUseCase(
    private val bookPort: BookPort
) {
    fun getAllBooks(): List<Book> {
        return bookPort.getAllBooks().sortedBy {
            it.name.lowercase()
        }
    }

    fun addBook(book: Book) {
        bookPort.createBook(book)
    }

    fun reserveBook(name: String) {
        val target = bookPort.getAllBooks().find { it.name == name }
            ?: error("Book $name not found")

        if (target.isReserved) error("Book already reserved")

        bookPort.reserveBook(name)
    }
}