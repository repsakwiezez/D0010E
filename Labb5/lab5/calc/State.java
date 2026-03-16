package calc;

/**
 * Representerar tillståndet i kalkylatorns inmatningsflöde.
 */
enum State {
    /** Användaren matar in det första talet. */
    Input1,

    /** Ett binärt operatorval har gjorts och nästa siffra väntas. */
    OpReady,

    /** Användaren matar in det andra talet. */
    Input2,

    /** Beräkningen har utförts och resultat visas. */
    HasResult
}