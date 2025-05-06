package com.jicay.bookmanagement.infrastructure.driven.adapter

import com.jicay.bookmanagement.domain.model.Book
import com.jicay.bookmanagement.domain.port.BookPort
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
class BookDAO(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate): BookPort {
    override fun getAllBooks(): List<Book> {
        return namedParameterJdbcTemplate
            .query("SELECT * FROM BOOK", MapSqlParameterSource()) { rs, _ ->
                Book(
                    name = rs.getString("title"),
                    author = rs.getString("author"),
                    isReserved  = rs.getBoolean("is_reserved")
                )
            }
    }

    override fun createBook(book: Book) {
        namedParameterJdbcTemplate
            .update("INSERT INTO BOOK (title, author, is_reserved) values (:title, :author, :is_reserved)", mapOf(
                "title" to book.name,
                "author" to book.author,
                "is_reserved" to book.isReserved
            ))
    }

    override fun reserveBook(name: String) {
        val rows = namedParameterJdbcTemplate.update(
            """
            UPDATE book
               SET is_reserved = TRUE
             WHERE title = :title
               AND is_reserved = FALSE
            """.trimIndent(),
            mapOf("title" to name)
        )
        if (rows == 0) error("Book $name not reservable or not found")
    }
}