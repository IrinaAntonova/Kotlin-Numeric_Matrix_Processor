package processor

enum class ProgramState {
    WAITING,
    WAIT_FOR_CONSTANT,
    WAIT_FOR_ONE_MATRICES,
    WAIT_FOR_TWO_MATRICES,
    EXIT,
}
