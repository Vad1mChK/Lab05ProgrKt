package ru.vad1mchk.progr.lab05.common.communication

import java.io.*


object Serializer {
    fun serialize(objectToSerialize: Serializable?): ByteArray? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutput: ObjectOutput = ObjectOutputStream(byteArrayOutputStream)
        objectOutput.flush()
        objectOutput.writeObject(objectToSerialize)
        objectOutput.flush()
        return byteArrayOutputStream.toByteArray()
    }

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