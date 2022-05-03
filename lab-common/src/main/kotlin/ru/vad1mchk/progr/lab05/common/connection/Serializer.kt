package ru.vad1mchk.progr.lab05.common.connection

import java.io.*
import java.nio.ByteBuffer

/**
 * Object in charge of serializing/deserializing objects transmitted via the connection.
 */
object Serializer {
    /**
     * Serializes the request on the client's side for transmission to the server.
     * @param request Request to serialize.
     * @return Buffer of bytes to send.
     */
    fun serializeRequest(request: Request): ByteBuffer {
        val bufferToSend: ByteBuffer
        ByteArrayOutputStream().use { byteArrayOutputStream ->
            ObjectOutputStream(byteArrayOutputStream).use { objectOutputStream ->
                objectOutputStream.writeObject(request)
                objectOutputStream.flush()
                bufferToSend = ByteBuffer.wrap(byteArrayOutputStream.toByteArray())
            }
        }
        return bufferToSend
    }

    /**
     * Serializes the response on the server's side for transmission to the client.
     * @param response Response to serialize.
     * @return Buffer of bytes to send.
     */
    fun serializeResponse(response: Response): ByteBuffer {
        val bufferToSend: ByteBuffer
        ByteArrayOutputStream().use { byteArrayOutputStream ->
            ObjectOutputStream(byteArrayOutputStream).use { objectOutputStream ->
                objectOutputStream.writeObject(response)
                objectOutputStream.flush()
                bufferToSend = ByteBuffer.wrap(byteArrayOutputStream.toByteArray())
            }
        }
        return bufferToSend
    }

    /**
     * Deserializes the request received from the client on the server side.
     * @param bytes Array of bytes received.
     * @return The request if it was deserialized correctly.
     */
    fun deserializeRequest(bytes: ByteArray): Request? {
        try {
            ByteArrayInputStream(bytes).use { byteArrayInputStream ->
                ObjectInputStream(byteArrayInputStream).use { objectInputStream ->
                    return objectInputStream.readObject() as Request
                }
            }
        } catch (e: Exception) {
            if (e is IOException || e is ClassNotFoundException) {
                return null
            }
            throw e
        }
    }

    /**
     * Deserializes the request received from the server on the client side.
     * @param bytes Array of bytes received.
     * @return The response if it was deserialized correctly.
     */
    fun deserializeResponse(bytes: ByteArray): Response? {
        try {
            ByteArrayInputStream(bytes).use { byteArrayInputStream ->
                ObjectInputStream(byteArrayInputStream).use { objectInputStream ->
                    return objectInputStream.readObject() as Response
                }
            }
        } catch (e: Exception) {
            if (e is IOException || e is ClassNotFoundException) {
                return null
            }
            throw e
        }
    }
}