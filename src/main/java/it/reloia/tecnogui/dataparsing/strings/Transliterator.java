package it.reloia.tecnogui.dataparsing.strings;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class Transliterator {
    private static final Map<Character, Character> TRANSLIT_MAP = new HashMap<>();

    static {
        TRANSLIT_MAP.put('ᴀ', 'A');
        TRANSLIT_MAP.put('ʙ', 'B');
        TRANSLIT_MAP.put('ᴄ', 'C');
        TRANSLIT_MAP.put('ᴅ', 'D');
        TRANSLIT_MAP.put('ᴇ', 'E');
        TRANSLIT_MAP.put('ꜰ', 'F');
        TRANSLIT_MAP.put('ɢ', 'G');
        TRANSLIT_MAP.put('ʜ', 'H');
        TRANSLIT_MAP.put('ɪ', 'I');
        TRANSLIT_MAP.put('ᴊ', 'J');
        TRANSLIT_MAP.put('ᴋ', 'K');
        TRANSLIT_MAP.put('ʟ', 'L');
        TRANSLIT_MAP.put('ᴍ', 'M');
        TRANSLIT_MAP.put('ɴ', 'N');
        TRANSLIT_MAP.put('ᴏ', 'O');
        TRANSLIT_MAP.put('ᴘ', 'P');
        TRANSLIT_MAP.put('ꞯ', 'Q');
        TRANSLIT_MAP.put('ʀ', 'R');
        TRANSLIT_MAP.put('ꜱ', 'S');
        TRANSLIT_MAP.put('ᴛ', 'T');
        TRANSLIT_MAP.put('ᴜ', 'U');
        TRANSLIT_MAP.put('ᴠ', 'V');
        TRANSLIT_MAP.put('ᴡ', 'W');
        TRANSLIT_MAP.put('ʏ', 'Y');
        TRANSLIT_MAP.put('ᴢ', 'Z');
    }

    /**
     * Converts weird characters to normal characters.
     *
     * @param input The input string to be transliterated.
     * @return The transliterated string.
     */
    public static String transliterate(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (TRANSLIT_MAP.containsKey(c)) {
                result.append(TRANSLIT_MAP.get(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Normalize the string to remove unwanted characters (like combining characters).
     *
     * @param input The input string to normalize.
     * @return The normalized string.
     */
    public static String normalize(String input) {
        // Normalize the string to NFC (Canonical Composition)
        return Normalizer.normalize(input, Normalizer.Form.NFC);
    }
}
