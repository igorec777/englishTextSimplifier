package textUtils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TextSimplifierTest
{
    @Test
    public void simplifyText1()
    {
        String text = "cacao and coffee is the success";

        assertEquals(TextSimplifier.simplifyText(text), "kakao and kofi is sukses");
    }

    @Test
    public void simplifyText2()
    {
        String text = "The table";

        assertEquals(TextSimplifier.simplifyText(text), "tabl");
    }

    @Test
    public void simplifyText3()
    {
        String text = "This is the text. Official test with the table. He feels sick.";

        assertEquals(TextSimplifier.simplifyText(text),
                "This is text. Ofisial test with tabl. H fils sik.");
    }

    @Test
    public void removeArticles()
    {
        TextSimplifier.setText("the table");
        assertEquals(TextSimplifier.removeArticles(), "table");
    }

    @Test
    public void removeLast_E_()
    {
        TextSimplifier.setText("The");
        assertEquals(TextSimplifier.removeLast_E_(), "Th");
    }

    @Test
    public void removeDoubleLetters() throws Exception
    {
        TextSimplifier.setText("ooo");
        assertEquals(TextSimplifier.removeDoubleLetters(), "uo");

        TextSimplifier.setText("oou");
        assertEquals(TextSimplifier.removeDoubleLetters(), "u");

        TextSimplifier.setText("iee");
        assertEquals(TextSimplifier.removeDoubleLetters(), "i");
    }

    @Test
    public void isTextSimple()
    {
        TextSimplifier.setText("Hello world");
        assertFalse(TextSimplifier.isTextSimple());

        TextSimplifier.setText("Kakao and kofi sukses");
        assertTrue(TextSimplifier.isTextSimple());
    }

    @Test
    public void remove_C_()
    {
        TextSimplifier.setText("success");

        assertEquals(TextSimplifier.remove_C_(), "suksess");
    }
}