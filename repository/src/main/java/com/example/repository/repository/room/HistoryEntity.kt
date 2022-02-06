package com.example.repository.repository.room



@Entity(indices = [Index(value = arrayOf("word"), unique = true)])
class HistoryEntity(
    @field:PrimaryKey
    @field:ColumnInfo(name = "word")
    var word: String, @field:ColumnInfo(name = "description")
    var description: String?
)