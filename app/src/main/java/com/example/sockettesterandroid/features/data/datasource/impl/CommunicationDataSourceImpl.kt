package com.example.sockettesterandroid.features.data.datasource.impl

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.sockettesterandroid.features.data.datasource.CommunicationDataSource
import com.example.sockettesterandroid.features.domain.entity.SocketResultEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.*
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketTimeoutException
import java.time.LocalDateTime
import javax.inject.Inject

class CommunicationDataSourceImpl @Inject constructor() :
    CommunicationDataSource {

    private var socket: Socket? = null
    private var mBufferOut: PrintWriter? = null
    private var mBufferIn: BufferedReader? = null
    private var isRunning = false

    private fun clearAll() {
        socket = null
        mBufferOut = null
        mBufferIn = null
        isRunning = false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun startConnection(
        ipAddress: String,
        port: String,
        onConnected: (() -> Unit)?,
        onError: ((String) -> Unit)?,
        onResult: ((SocketResultEntity) -> Unit)?,
        onDone: (() -> Unit)?,
    ) {
        return withContext(Dispatchers.IO) {
            try {
                val serverAddress = InetAddress.getByName(ipAddress)

                socket = Socket()
                socket!!.connect(InetSocketAddress(serverAddress, port.toInt()), 10000)

                if (socket!!.isConnected) {
                    onConnected?.invoke()

                    mBufferOut =
                        PrintWriter(
                            BufferedWriter(OutputStreamWriter(socket!!.getOutputStream())),
                            true
                        )
                    mBufferIn = BufferedReader(InputStreamReader(socket!!.getInputStream()))

                    isRunning = true

                    while (isRunning) {
                        val mServerMessage = mBufferIn!!.readLine()
                        if (mServerMessage != null && onResult != null) {
                            val data = SocketResultEntity(
                                data = mServerMessage,
                                date = LocalDateTime.now(),
                            )
                            onResult(data)
                        }
                    }
                } else {
                    onError?.invoke("Failed to connect")
                }
            } catch (e: SocketTimeoutException) {
                onError?.invoke(e.message ?: "Time out")
            } catch (e: Exception) {
                onError?.invoke(e.message ?: "Something went wrong")
            } finally {
                socket?.close()
                onDone?.invoke()
                clearAll()
            }
        }
    }

    override suspend fun stopConnection() {
        isRunning = false
        if (mBufferOut != null) {
            mBufferOut!!.flush()
            mBufferOut!!.close()
        }
    }

    override suspend fun sendData(data: String) {
        withContext(Dispatchers.IO) {
            try {
                if (socket?.isConnected == true && mBufferOut != null && !mBufferOut!!.checkError()) {
                    Log.d("CommunicationDataSourceImpl", "Sending data: $data")
                    mBufferOut!!.println(data)
                    mBufferOut!!.flush()
                } else {
                    Log.e("CommunicationDataSourceImpl", "Cannot send data: Socket not connected or output stream is null")
                }
            } catch (e: IOException) {
                Log.e("CommunicationDataSourceImpl", "Error while sending data: ${e.message}")
            }
        }
    }
}