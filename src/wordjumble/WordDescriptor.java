package wordjumble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class WordDescriptor 
{
    private String word;
    private List<CharacterDescriptor> characterList = new ArrayList<CharacterDescriptor>();
    
    public WordDescriptor(String word)
    {
        setWord(word);
        
        initialize();
    }
    
    private void initialize()
    {
        char[] characters = word.toCharArray();
        Map<Character, Integer> charToCount = new HashMap<Character, Integer>();
        
        for(int counter = 0;
            counter < characters.length;
            counter++)
        {
            char character = characters[counter];
            
            if(!Character.isLetter(character))
            {
                continue;
            }
            
            Integer count = charToCount.get(character);
            
            if(count == null)
            {
                count = 0;
            }
            
            count++;
            charToCount.put(character, count);
        }
        
        Set<Character> characterSet = charToCount.keySet();
        
        for(Character character: characterSet)
        {
            characterList.add(new CharacterDescriptor(character,
                                                      charToCount.get(character)));
        }
        
        Collections.sort(characterList, CharacterComparator.getInstance());
        
        characterList = Collections.unmodifiableList(characterList);
    }
    
    private void setWord(String word)
    {
        this.word = word.toLowerCase().replaceAll("[^a-z]", "");
    }
    
    public String getWord()
    {
        return word;
    }
    
    public boolean isValid()
    {
        return characterList.size() > 0;
    }
    
    public List<CharacterDescriptor> getCharacterList()
    {
        return characterList;
    }
    
    @Override
    public boolean equals(Object object)
    {
        if(object == null)
        {
            return false;
        }
        
        if(!(object instanceof WordTree))
        {
            return false;
        }
        
        WordDescriptor wd = (WordDescriptor) object;
        
        return word.equals(wd.getWord());
    }
}
