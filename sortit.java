/************************************************************
* Katherine Le
* lek4@winthrop.edu
* CSCI 271 PA3
* Description: Sort data in files or take in standard input
*************************************************************/
import java.io.File; //For File use 
import java.io.IOException; //For File errors
import java.util.Scanner; //For Scanner use  

/*******************************************************
* MyLink class (Link class renamed)
********************************************************/ 
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

/*******************************************************
* Queue class ADT
********************************************************/ 
interface Queue // Source: Shaffer textbook  
{  
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

/*******************************************************
* Linked Queue implementation 
********************************************************/ 
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
  } // Ignores size
  
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

/*******************************************************
* MyQueue class for referencing queue 
********************************************************/ 
class MyQueue 
{
  public MyQueue next; 
  public LQueue data; 

  //Constructors
  public MyQueue(LQueue dataIn, MyQueue nextIn) 
  {
    this.data = dataIn;
    this.next = nextIn;
  }

  public MyQueue(MyQueue nextIn) 
  {
    this.data = null;
    this.next = nextIn;
  }

  LQueue getData() 
  { 
    return data;
  }

  void setData(LQueue newData) 
  { 
    data = newData;
  }

  MyQueue getNext() 
  { // Return the next field
    return next;
  }

  void setNext(MyQueue newNext) 
  { // Set the next field
    next = newNext;
  }
}

/*******************************************************
* Main sortit class begins here
********************************************************/ 
public class sortit
{
  public static void main(String[] args) 
  {
    LQueue list0 = new LQueue(); 
    LQueue list1 = new LQueue(); 
    MyQueue currQueue = new MyQueue(null);
    currQueue.setData(list0);

    for(int h = 0; h < args.length; h++)
    {
      if (args[h].equals("-"))
      {
        try 
        {
          Scanner input = new Scanner(System.in);
          while (input.hasNextLine())
          {
            String lineIn = input.nextLine();
            list0.enqueue(lineIn);
          }
          input.close();
        } 
        catch(Exception e)
        {
          e.printStackTrace();
        }
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
        }
        catch (IOException e) 
        {
          e.printStackTrace();
        }
      } // End if command line argument is not a hypen
    } // End for loop through command line arguments

    /*******************************************************************
     * Data has been accumulated into list0
     * Ready for sorting 
     *******************************************************************/
    String headList = list0.frontValue().getData();
    list0.dequeue(); 
    list0.enqueue(headList);

    int queueSize = list0.length();
    for(int n = 1; n < queueSize; n++)
    {
      String dequeueElement = list0.dequeue().getData();
      int compareDequeueToFront = dequeueElement.compareTo(headList);
      if (compareDequeueToFront >= 0 && currQueue.getData() == list0) 
      {
        list0.enqueue(dequeueElement);
        headList = list0.frontValue().getData();
      }
      else
      {
        list1.enqueue(dequeueElement);
        currQueue = SwapList(currQueue, list0, list1);
        headList = list0.frontValue().getData();
      }
    }

    if(!list0.isEmpty())
    {
      System.out.println("This is list0");
      while(!list0.isEmpty())
      {
        String queueLine = list0.frontValue().getData(); 
        System.out.print(queueLine + "\n");
        list0.dequeue();
      }
    }

    if(!list1.isEmpty())
    {
      System.out.println("This is list1");
      while(!list1.isEmpty())
      {
        String queueLine = list1.frontValue().getData(); 
        System.out.print(queueLine + "\n");
        list1.dequeue();
      }
    }
    System.out.println();

  } // End public static void main

// Functions 
/***********************************************************
* Function Name: SwapList 
* Description: To swap the current list 
* Input: MyQueue, LQueue1, LQueue2
* Output: If MyQueue's data is LQueue1, swap for LQueue2
          If MyQueue's data is LQueue2, swap for LQueue1
************************************************************/
  public static MyQueue SwapList(MyQueue currPtr, LQueue x, LQueue y)
  {
    if(currPtr.getData() == x)
    {
      currPtr.setData(y);
      return currPtr;
    }
    else if(currPtr.getData() == y)
    {
      currPtr.setData(x);
      return currPtr;
    }
    else 
    {
      return currPtr;
    }
  }
} // End public class sortit