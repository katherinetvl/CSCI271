/************************************************************
* Katherine Le
* lek4@winthrop.edu
* CSCI 271 PA3
* Description: Sort data in files or take in standard input
*************************************************************/
import java.io.File; //For File use 
import java.io.IOException; //For File errors
import java.util.Scanner; //For Scanner use  

class MyLink  // Source: Shaffer textbook  
{
    public MyLink next; //Point to next node in list
    public String data; //Value for this node
  
    //Constructors
    public MyLink(String dataIn, MyLink nextIn) 
    {
      this.data = dataIn;
      this.next = nextIn;
    }
  
    public MyLink(MyLink nextIn) 
    {
      this.data = null;
      this.next = nextIn;
    }
  
    String getData() 
    { // Return the data field
      return data;
    }
  
    void setData(String newData) 
    { // Set the data field
      data = newData;
    }
  
    MyLink getNext() 
    { // Return the next field
      return next;
    }
  
    void setNext(MyLink newNext) 
    { // Set the next field
      next = newNext;
    }
}

// Queue class ADT
interface Queue // Source: Shaffer textbook  
{  
    // Reinitialize queue
    // public void clear();
  
    // Put element on rear
    public boolean enqueue(String it);
  
    // Remove and return element from front
    public MyLink dequeue();
  
    // Return front element
    public MyLink frontValue();
  
    // Return queue size
    public int length();
  
    // Return true if the queue is empty
    public boolean isEmpty();
}

// Linked queue implementation
class LQueue implements Queue 
{
    private MyLink front; // Pointer to front queue node
    private MyLink rear;  // Pointer to rear queue node
    private int size;   // Number of elements in queue
  
    // Constructors
    LQueue() 
    {
        init(); 
    }
    LQueue(int size) 
    {
        init(); 
    } // Ignore size
  
    // Initialize queue
    private void init() 
    {
      front = rear = new MyLink(null);
      size = 0;
    }
  
    // Put element on rear
    public boolean enqueue(String it) 
    {
      rear.setNext(new MyLink(it, null));
      rear = rear.next;
      size++;
      return true;
    }

  // Remove and return element from front
    public MyLink dequeue() 
    {
      if (size == 0)
      {
        return null;
      }

      MyLink it = front.getNext(); // Store the value
      front.setNext(front.getNext().next); // Advance front

      if (front.getNext() == null)
      {
        rear = front; // Last element
      }
      size--;
      return it; // Return element
    }

    // Return front element
    public MyLink frontValue() 
    {
      if (size == 0)
      {
        return null;
      }
      return front.getNext();
    }
  
    // Return queue size
    public int length() 
    {
        return size; 
    }
  
    // Check if the queue is empty
    public boolean isEmpty() 
    {
        return size == 0; 
    }
}

public class sortit
{
    public static void main(String[] args) 
    {
        LQueue list0 = new LQueue(); 
        LQueue list1 = new LQueue(); 

        for(int h = 0; h < args.length; h++)
        {
            String currArgument = args[h]; 
            if (currArgument.equals("-"))
            {
                list0.enqueue(currArgument); 
            }
        }
        for(int f = 0; f < args.length; f++)
        {
            if(!args[f].equals("-"))
            {
                String currFile = args[f];

                File commandLineFile = new File(currFile);
                String currLine = null; 
    
                try 
                {
                    Scanner fin = new Scanner(commandLineFile);
                    while (fin.hasNextLine())
                    {
                        currLine = fin.nextLine();
                        list0.enqueue(currLine);
                    }
                    // Close file
                    fin.close();
                } catch (IOException e) 
                    {
                        e.printStackTrace();
                        System.out.println("An error occurred with opening this file.");
                    }
            } // End if command line argument is not a hypen
        } // End for loop through command line arguments

        System.out.println("Showing contents of lists: ");
        System.out.println("List 1: ");

        if(list0.isEmpty())
        {
            System.out.print("List 1 is empty. \n");
        }
        else 
        {
            while(!list0.isEmpty())
            {
                String queueChar = list0.frontValue().getData(); 
                System.out.print(queueChar + "\n");
                list0.dequeue();
            }
        }
        System.out.println();

        System.out.println("List 2: ");
        if(list1.isEmpty())
        {
            System.out.print("List 2 is empty. \n");
        }

    } // End public static void main
} // End public class sortit