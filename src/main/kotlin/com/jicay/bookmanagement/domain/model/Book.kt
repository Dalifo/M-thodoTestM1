package com.jicay.bookmanagement.domain.model

data class Book(val name: String, val author: String, val isReserved: Boolean = false) {
    init {
        require(name.isNotBlank())   { "Name must not be blank" }
        require(author.isNotBlank()) { "Author must not be blank" }
    }

    fun reserve(): Book =
        if (isReserved) error("Book already reserved")
        else copy(isReserved = true)
}
