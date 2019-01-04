package util;

/**
 * Created by CPU11630_LOCAL on 12/11/2018.
 */
public class Key {

    public final int first;
    public final int second;

    public Key(final int first, final int second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals (final Object O) {
        if (!(O instanceof Key)) return false;
        if (((Key) O).first != first) return false;
        if (((Key) O).second != second) return false;
        return true;
    }

    public int hashCode() {
        return (first << 16) + second;
    }

}