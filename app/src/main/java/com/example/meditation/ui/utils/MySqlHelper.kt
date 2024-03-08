package com.example.meditation.ui.utils

import com.mysql.jdbc.PreparedStatement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class MySqlHelper {
    private val dbUrl = "jdbc:mysql://rm-2zee587jfc2tt7xws5o.mysql.rds.aliyuncs.com:3306/song_jing"
    private val username = "SongJing"
    private val password = "UniqueStudio_SongJing"

    private suspend fun establishConnection(): Connection = withContext(Dispatchers.IO) {
        Class.forName("com.mysql.jdbc.Driver")
        DriverManager.getConnection(dbUrl, username, password)
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
            var result = false
            connection.use {
                val preparedStatement: java.sql.PreparedStatement = it.prepareStatement(sql)
                preparedStatement.setString(1, username)
                preparedStatement.setString(2, password)
                val resultSet: ResultSet = preparedStatement.executeQuery()
                result = resultSet.next()
            }
            result
        }

}