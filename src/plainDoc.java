import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//  THIS CLASS IS USED TO LIMIT THE LENGTH OF INPUT IN CERTAIN FIELDS

public class plainDoc extends PlainDocument
{
    private int max = 12;

    public plainDoc(int max)
    {
        this.max = max;
    }

    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException
    {
        // check string being inserted does not exceed max length

        if (getLength()+str.length()>max)
        {
            // If it does, then truncate it

            str = str.substring(0, max - getLength());
        }
        super.insertString(offs, str, a);
    }
}