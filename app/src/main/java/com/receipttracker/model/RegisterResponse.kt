package com.receipttracker.model

data class RegisterResponse (val error: Boolean, val message: String, val token: String, val userId: Int)