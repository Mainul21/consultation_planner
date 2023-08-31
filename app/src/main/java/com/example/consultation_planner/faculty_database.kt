package com.example.consultation_planner


data class faculty_database(
    val id: String? = null, // Provide default values
    val slot1date: String = "",
    val slot1time: String = "",
    val slot2date: String = "",
    val slot2time: String = ""
) {
    // No-argument constructor
    constructor() : this("", "", "", "", "")
}
