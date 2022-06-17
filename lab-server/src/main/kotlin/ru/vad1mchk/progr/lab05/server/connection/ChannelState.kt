package ru.vad1mchk.progr.lab05.server.connection

enum class ChannelState {
    READY_TO_READ,
    READING,
    READY_TO_WRITE,
    WRITING
}