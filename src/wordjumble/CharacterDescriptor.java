
package wordjumble;

public final class CharacterDescriptor 
{
    private char character;
    private int count;
    
    public CharacterDescriptor(char character,
                               int count)
    {
        setCharacter(character);
        setCount(count);
    }
    
    private void setCharacter(char character)
    {
        this.character = character;
    }
    
    public char getCharacter()
    {
        return character;
    }
    
    private void setCount(int count)
    {
        if(count <= 0)
        {
            throw new IllegalArgumentException("The count must be positive.");
        }
        
        this.count = count;
    }
    
    public int getCount()
    {
        return count;
    }
    
    @Override
    public int hashCode()
    {
        return (((int) character) << 16) | count;
    }
    
    @Override
    public boolean equals(Object object)
    {
        if(object == null)
        {
            return false;
        }
        
        if(!(object instanceof CharacterDescriptor))
        {
            return false;
        }
        
        CharacterDescriptor cd = (CharacterDescriptor) object;
        
        return getCharacter() == cd.getCharacter() && getCount() == cd.getCount();
    }
}
