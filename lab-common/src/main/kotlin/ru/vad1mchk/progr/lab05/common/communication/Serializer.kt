package ru.vad1mchk.progr.lab05.common.communication

import java.io.*
import java.nio.ByteBuffer

/**
 * Object that is used to serialize objects to byte array and deserialize them back.
 */
object Serializer {
    /**
     * Serializes the specified object into an array of bytes. This object must implement [Serializable].
     * @param objectToSerialize Object to serialize.
     * @return A byte array representing this object.
     */
    @JvmStatic
    fun serialize(objectToSerialize: Serializable?): ByteArray? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutput: ObjectOutput = ObjectOutputStream(byteArrayOutputStream)
        objectOutput.writeObject(objectToSerialize)
        objectOutput.flush()
        return byteArrayOutputStream.toByteArray()
    }

    /**
     * Deserializes the object from the specified array of bytes. Note that cast to the desired class must be
     * performed afterwards to get an instance of the object you want.
     * @param serializedObject Array of bytes representing the object to deserialize.
     * @return The deserialized object.
     */
    @JvmStatic
    fun deserialize(serializedObject: ByteArray?): Serializable? {
        val byteArrayInputStream = ByteArrayInputStream(serializedObject)
        val objectInput = ObjectInputStream(byteArrayInputStream)
        return try {
            objectInput.readObject() as Serializable
        } catch (e: ClassNotFoundException) {
            null
        } catch (e: IOException) {
            null
        }
    }

}