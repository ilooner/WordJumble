package wordjumble;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordJumble 
{
    private static WordTree wordTree;
    
    private static void buildWordTree()
    {
        InputStream dictionaryInput = WordJumble.class.getResourceAsStream("/corncob_lowercase.txt");
        Set<WordDescriptor> wordDescriptors = new HashSet<WordDescriptor>();
        
        Scanner wordScanner = new Scanner(dictionaryInput);
        
        while(wordScanner.hasNextLine())
        {
            String word = wordScanner.nextLine().trim();
            wordDescriptors.add(new WordDescriptor(word));
        }
        
        wordTree = new WordTree(wordDescriptors);
        
        wordScanner.close();
        try {
            dictionaryInput.close();
        } catch (IOException ex) {
            Logger.getLogger(WordJumble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) 
    {
        Scanner inputScanner = new Scanner(System.in);
        
        System.out.println("Building Word Tree. Please Wait.");
        
        buildWordTree();
        
        System.out.println("Done building word tree.");
        
        while(true)
        {
            System.out.println("Please enter a word:");
            
            WordDescriptor wordDescriptor = new WordDescriptor(inputScanner.nextLine());
            
            if(!wordDescriptor.isValid())
            {
                System.out.println("This is not a valid word.");
                continue;
            }
            
            Set<String> similarWords = wordTree.getAllWords(wordDescriptor);
            
            if(similarWords.isEmpty())
            {
                continue;
            }
            
            List<String> similarWordsList = new ArrayList<String>();
            similarWordsList.addAll(similarWords);
            Collections.sort(similarWordsList);
            
            System.out.println("The jumbled words are:");
            
            for(String similarWord: similarWordsList)
            {
                if(similarWord.equals(wordDescriptor.getWord()))
                {
                    continue;
                }
                
                System.out.println(similarWord);
            }
        }
    }
}
