package com.example.yehezkiel.cermati.Model

data class UsersX(
        val total_count: Int,
        val incomplete_results: Boolean,
        open val items: List<Item>
)