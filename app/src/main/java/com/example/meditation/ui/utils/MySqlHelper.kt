package com.example.meditation.ui.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet


const val DBURL = "jdbc:mysql://rm-2zee587jfc2tt7xws5o.mysql.rds.aliyuncs.com:3306/song_jing"
const val USERNAME = "SongJing"
const val PASSWORD = "UniqueStudio_SongJing"
suspend fun establishConnection(): Connection = withContext(Dispatchers.IO) {
    Class.forName("com.mysql.jdbc.Driver")
    DriverManager.getConnection(DBURL, USERNAME, PASSWORD)
}

suspend fun insertUser(username: String, password: String) {
    withContext(Dispatchers.IO) {
        val connection = establishConnection()
        val sql = "INSERT INTO users (username,password) VALUES(?,?)"
        connection.use {
            val preparedStatement: java.sql.PreparedStatement = it.prepareStatement(sql)
            preparedStatement.setString(1, username)
            preparedStatement.setString(2, password)
            preparedStatement.executeUpdate()
        }
    }
}

suspend fun searchUser(username: String, password: String): Boolean =
    withContext(Dispatchers.IO) {
        val connection = establishConnection()
        val sql = "SELECT * FROM users WHERE username=? AND password=?"
        var result: Boolean
        connection.use {
            val preparedStatement: java.sql.PreparedStatement = it.prepareStatement(sql)
            preparedStatement.setString(1, username)
            preparedStatement.setString(2, password)
            val resultSet: ResultSet = preparedStatement.executeQuery()
            result = resultSet.next()
        }
        result
    }
