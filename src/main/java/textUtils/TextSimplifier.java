package textUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class TextSimplifier
{

    public enum Patterns
    {
        DOUBLE_LETTERS("([a-z])\\1+"),
        LAST_E_("([a-zA-Z]+)(e|E)(\\W|$)"),
        ARTICLES("^(a|A|The|the|An|an)\\W|\\W(a|A|The|the|An|an)$"),
        ARTICLES_INSIDE("\\W(a|A|The|the|An|an)\\W");

        private final String pattern;

        Patterns(String pattern) {
            this.pattern = pattern;
        }
    }

    private static String text;

    static
    {
        text = "";
    }

    private TextSimplifier(){}

    public static String removeArticles()
    {
        return text
                .replaceAll(Patterns.ARTICLES.pattern, "")
                .replaceAll(Patterns.ARTICLES_INSIDE.pattern, " ");
    }

    public static String removeLast_E_()
    {
        return text.replaceAll(Patterns.LAST_E_.pattern, "$1$3");
    }

    public static String remove_C_()
    {
        return text
                .replaceAll("ci", "si")
                .replaceAll("ce", "se")
                .replaceAll("ck", "k")
                .replaceAll("c", "k");
    }

    public static String removeDoubleLetters()
    {
        return text
                .replaceAll("ee", "i")
                .replaceAll("oo", "u")
                .replaceAll(Patterns.DOUBLE_LETTERS.pattern, "$1");
    }

    public static CharSequence simplifyText(CharSequence text)
    {
        do
        {
            TextSimplifier.text = text.toString();

            TextSimplifier.text = removeArticles();

            TextSimplifier.text = remove_C_();

            TextSimplifier.text = removeDoubleLetters();

            TextSimplifier.text = removeLast_E_();
        }
        while (!isTextSimple());

        return TextSimplifier.text;
    }

    public static boolean isTextSimple()
    {
        Matcher matcher;

        if (text.contains("ci") ||
                text.contains("ce") ||
                text.contains("c"))
            return false;

        matcher = Pattern.compile(Patterns.DOUBLE_LETTERS.pattern).matcher(text);

        if (matcher.find())
            return false;

        matcher = Pattern.compile(Patterns.LAST_E_.pattern).matcher(text);

        if (matcher.find())
            return false;

        return true;
    }

    public static void setText(String text)
    {
        TextSimplifier.text = text;
    }
}