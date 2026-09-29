package gui;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

/**
 * Emoji - draws emoji on every computer, not just a Mac.
 *
 * On Windows, Java's fonts have no emoji, so "💥" draws as an empty box.
 * fit() leaves text alone when the label's font can draw all of it, and
 * otherwise sets just the characters it can't in a font that can.
 */
final class Emoji {

    private static final String[] FALLBACKS = {
        "Segoe UI Emoji", "Apple Color Emoji", "Noto Color Emoji", "Segoe UI Symbol"
    };
    private static final Map<String, Font> FONTS = new HashMap<>();

    private Emoji() {}

    /** The text to give a label or button using this font. */
    static String fit(String text, Font font) {
        if (text == null || text.isEmpty() || text.startsWith("<html>")
                || font.canDisplayUpTo(text) == -1) {
            return text;
        }
        StringBuilder out = new StringBuilder("<html>");
        String open = null; // the fallback font of the span we are inside, if any
        for (int i = 0; i < text.length(); ) {
            int cp = text.codePointAt(i);
            i += Character.charCount(cp);
            if (cp == 0xFE0F) continue; // emoji-style selector; draws as a box on its own
            String family = font.canDisplay(cp) ? null : familyFor(cp);
            if (family == null ? open != null : !family.equals(open)) {
                if (open != null) out.append("</span>");
                if (family != null) out.append("<span style=\"font-family: ").append(family).append("\">");
                open = family;
            }
            switch (cp) {
                case '<': out.append("&lt;"); break;
                case '>': out.append("&gt;"); break;
                case '&': out.append("&amp;"); break;
                default: out.appendCodePoint(cp);
            }
        }
        if (open != null) out.append("</span>");
        return out.append("</html>").toString();
    }

    /** The first installed fallback font that has this character, or null. */
    private static String familyFor(int cp) {
        for (String name : FALLBACKS) {
            Font f = FONTS.computeIfAbsent(name, n -> new Font(n, Font.PLAIN, 12));
            // A missing font silently becomes Dialog, so check it is really there.
            if (f.getFamily().equals(name) && f.canDisplay(cp)) return name;
        }
        return null;
    }
}
