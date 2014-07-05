package wordjumble;

import java.util.Comparator;

public final class CharacterComparator implements Comparator<CharacterDescriptor>
{
    private static final CharacterComparator instance = new CharacterComparator();
    
    private CharacterComparator()
    {
    }
    
    public static CharacterComparator getInstance()
    {
        return instance;
    }
    
    @Override
    public int compare(CharacterDescriptor o1,
                       CharacterDescriptor o2)
    {
        return o1.getCharacter() - o2.getCharacter();
    }
}
