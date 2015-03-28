package BalancedTree;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary {
    
    AvlTree dictionary = new AvlTree();

    public void load_dictionary() {
        try {
            File file = new File("test.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    dictionary.insert(line);
                } catch (Exception ex) {

                }

            }
            fileReader.close();

        } catch (IOException e) {
            System.out.println("File is not found !");
        }
    }

    public void print_dictionary_size() {
        System.out.println(dictionary.getSize());
    }

    public void insert_word(String word) {
        try {
            dictionary.insert(word);
            System.out.println("Dictionary Size : "+dictionary.getSize());
            System.out.print("Dictionary Height : ");dictionary.printHight();
        } catch (Exception ex) {
            System.out.println("ERROR: Word already in the dictionary!");
        }
    }

    public boolean look_up(String word) {
        try {
            if (dictionary.search(word) == null) {
                System.out.println("NO");
                return false;
            } else {
                System.out.println("YES");
                return true;
            }
        } catch (Exception ex) {
            System.out.println("ERROR: Dictionary is empty!");
            return false;
        }
    }

    public void remove_word(String word) {
        try {
            dictionary.delete(word);
            System.out.println("Word Removed ..");
            System.out.println("Dictionary Size : "+dictionary.getSize());
            System.out.print("Dictionary Height : ");dictionary.printHight();
        } catch (Exception ex) {
            System.out.println("ERROR: Word is not found in the dictionary!");
        }
    }

    public void batch_lookups() {
        int num_Of_Found_Words = 0;
        try {
            File file = new File("queries.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    System.out.print(line + " :");
                    if (dictionary.search(line)==null) {
                        System.out.println("NO");
                    }
                    else{
                        System.out.println("YES");
                        num_Of_Found_Words++;
                    }

                } catch (Exception ex) {

                }

            }
            fileReader.close();

        } catch (IOException e) {
            System.out.println("File is not found !");
        }
        System.out.println("Number Of found words: " + num_Of_Found_Words);
            System.out.println("Dictionary Size : "+dictionary.getSize());
            System.out.print("Dictionary Height : ");dictionary.printHight();
        

    }

    public void batch_deletions() {
        try {
            File file = new File("deletions.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                remove_word(line);

            }
            fileReader.close();
            
            System.out.println("Dictionary Size : "+dictionary.getSize());
            System.out.print("Dictionary Height : ");dictionary.printHight();

        } catch (IOException e) {
            System.out.println("File is not found !");
        }
         
    }

}
