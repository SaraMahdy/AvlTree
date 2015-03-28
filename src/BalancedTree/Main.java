package BalancedTree;
import java.util.*;



public class Main {
 
    public static void main(String[] args) {
        Dictionary d=new Dictionary();
        main_menu();
        boolean flag=true;
         try{
        while(flag){
        Scanner sc=new Scanner(System.in);
        int input=sc.nextInt();
       
        switch(input){
            case 1:
                d.load_dictionary();
                System.out.println("Dictionary Loaded");
                main_menu();
                break;
            case 2:
                System.out.print("Dictionary Size: ");
                d.print_dictionary_size();
                main_menu();
                break;
            case 3:
                System.out.println("Enter the word:");
                d.insert_word(sc.next());
                main_menu();
                break;
            case 4:
                System.out.println("Enter the word:");
                d.look_up(sc.next());
                main_menu();
                break;
            case 5:
                 System.out.println("Enter the word:");
                 d.remove_word(sc.next());
                 main_menu();
                break;
            case 6:
                d.batch_lookups();
                main_menu();
                break;
            case 7:
                d.batch_deletions();
                main_menu();
                break;
            case 8:
                flag=false;
                break;
            default:System.out.println("Invalid Choice !");
                
        }
        }
        
        }
        catch(Exception ex){
               System.out.println("ERROR !"); 
           }
    
        
    }
    public static void main_menu(){
        System.out.println("************************** MAIN MENU ******************************");
        System.out.println("Choose an operation ..\n1.Load Dictionary.\n2.Print Dictionary Size.\n3.Insert Word.\n4.Look-Up a Word.\n"
                + "5.Remove Word.\n6.Batch Look-Ups.\n7.Batch Deletions.\n8.Quit");
    }
}