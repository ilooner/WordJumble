package wordjumble;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class TreeNode 
{
    private CharacterDescriptor characterDescriptor;
    private Set<String> leafWords = new HashSet<String>();
    private Map<CharacterDescriptor, TreeNode> cdToTreeNode = new HashMap<CharacterDescriptor, TreeNode>();
    
    public TreeNode(CharacterDescriptor characterDescriptor)
    {
        setCharacterDescriptor(characterDescriptor);
    }
    
    private void setCharacterDescriptor(CharacterDescriptor characterDescriptor)
    {
        this.characterDescriptor = characterDescriptor;
    }
    
    public CharacterDescriptor getCharacterDescriptor()
    {
        return characterDescriptor;
    }
    
    public TreeNode getChild(CharacterDescriptor characterDescriptor)
    {
        return cdToTreeNode.get(characterDescriptor);
    }
    
    public boolean hasChild(CharacterDescriptor characterDescriptor)
    {
        return getChild(characterDescriptor) != null;
    }
    
    public boolean addChild(CharacterDescriptor characterDescriptor)
    {
        if(characterDescriptor == null)
        {
            throw new NullPointerException("The characterDescriptor cannot be null.");
        }
        
        if(hasChild(characterDescriptor))
        {
            return false;
        }
        
        cdToTreeNode.put(characterDescriptor, new TreeNode(characterDescriptor));
        
        return true;
    }
    
    public TreeNode getAddChild(CharacterDescriptor characterDescriptor)
    {
        addChild(characterDescriptor);
        return getChild(characterDescriptor);
    }
    
    public boolean isRoot()
    {
        return characterDescriptor == null;
    }
    
    public void addLeafWord(String word)
    {
        if(word == null)
        {
            throw new NullPointerException("The given leaf word cannot be null.");
        }
        
        leafWords.add(word);
    }
    
    public Set<String> getLeafWords()
    {
        return Collections.unmodifiableSet(leafWords);
    }
    
    public Set<TreeNode> getChildrenLTE(CharacterDescriptor characterDescriptor)
    {
        Set<TreeNode> treeNodeSet = new HashSet<TreeNode>();
        
        while(true)
        {
            if(this.hasChild(characterDescriptor))
            {
                treeNodeSet.add(this.getChild(characterDescriptor));
            }
            
            if(characterDescriptor.getCount() == 1)
            {
                break;
            }
            
            characterDescriptor = new CharacterDescriptor(characterDescriptor.getCharacter(),
                                                          characterDescriptor.getCount() - 1);
        }
        
        return treeNodeSet;
    }
}
