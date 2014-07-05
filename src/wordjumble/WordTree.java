package wordjumble;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordTree 
{
    TreeNode root = new TreeNode(null);
    
    public WordTree(Set<WordDescriptor> wordDescriptors)
    {
        initialize(wordDescriptors);
    }
    
    private void initialize(Set<WordDescriptor> wordDescriptors)
    {
        for(WordDescriptor wordDescriptor: wordDescriptors)
        {
            addWord(wordDescriptor);
        }
    }
    
    private void addWord(WordDescriptor wordDescriptor)
    {
        List<CharacterDescriptor> characterList = wordDescriptor.getCharacterList();
        
        TreeNode currentNode = root;
        
        for(CharacterDescriptor characterDescriptor: characterList)
        {
            currentNode = currentNode.getAddChild(characterDescriptor);
        }
        
        currentNode.addLeafWord(wordDescriptor.getWord());
    }
    
    public Set<String> getAllWords(WordDescriptor wordDescriptor)
    {
        Set<TreeNode> treeNodeSet = new HashSet<TreeNode>();
        Set<String> allWords = new HashSet<String>();
        List<CharacterDescriptor> characterList = wordDescriptor.getCharacterList();
        
        treeNodeSet.add(root);
        
        for(int counter = 0;
            counter < characterList.size();
            counter++)
        {
            Set<String> tempAllWords = getAllWordsHelper(treeNodeSet,
                                                         wordDescriptor,
                                                         counter);
            
            allWords.addAll(tempAllWords);
        }
        
        return allWords;
    }
    
    private Set<String> getAllWordsHelper(Set<TreeNode> treeNodeSet,
                                          WordDescriptor wordDescriptor,
                                          int characterIndex)
    {
        if(wordDescriptor.getCharacterList().size() <= characterIndex)
        {
            return new HashSet<String>();
        }
        
        List<CharacterDescriptor> characterList = wordDescriptor.getCharacterList();
        CharacterDescriptor characterDescriptor = characterList.get(characterIndex);
        
        Set<String> allWords = new HashSet<String>();
        Set<TreeNode> childTreeNodeSet = new HashSet<TreeNode>();
        
        for(TreeNode treeNode: treeNodeSet)
        {
            CharacterDescriptor tempCD = treeNode.getCharacterDescriptor();
            Set<TreeNode> tempTreeNodeSet = treeNode.getChildrenLTE(characterDescriptor);
            childTreeNodeSet.addAll(tempTreeNodeSet);
        }
        
        for(TreeNode treeNode: childTreeNodeSet)
        {
            allWords.addAll(treeNode.getLeafWords());
        }
        
        for(int counter = characterIndex + 1;
            counter < characterList.size();
            counter++)
        {
            Set<String> tempAllWords = getAllWordsHelper(childTreeNodeSet,
                                                         wordDescriptor,
                                                         counter);
            allWords.addAll(tempAllWords);                       
        }
        
        return allWords;
    }
}
