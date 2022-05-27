package ru.vad1mchk.progr.lab05.common.communication

import java.io.*


object Serializer {
    fun serialize(objectToSerialize: Serializable?): ByteArray? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutput: ObjectOutput = ObjectOutputStream(byteArrayOutputStream)
        objectOutput.writeObject(objectToSerialize)
        objectOutput.flush()
        objectOutput.close()
        println("Serialization of ${objectToSerialize?.javaClass} complete.")
        return byteArrayOutputStream.toByteArray()
    }

    fun deserialize(serializedObject: ByteArray?): Serializable? {
        val byteArrayInputStream = ByteArrayInputStream(serializedObject)
        val objectInput = ObjectInputStream(byteArrayInputStream)
        println("Deserialization of ${serializedObject?.javaClass} complete.")
        return try {
            objectInput.readObject() as Serializable
        } catch (e: ClassNotFoundException) {
            null
        } catch (e: IOException) {
            null
        }
    }

}