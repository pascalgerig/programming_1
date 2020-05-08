
public class Queue {

	private Node list;
	
	public Queue()
	{
		list = null;
	}
	
	public Object dequeue() throws EmptyQueueException
	{	
		if(isEmpty())
		{
			throw new EmptyQueueException("Queue is empty");
		}
		else
		{
			Object object = list.object;
			list = list.next;
			return object;
		}	
	}
	
	public void enqueue(Object object)
	{
		Node node = new Node(object);
		Node current;
		
		if(isEmpty())
		{
			list = node;
		}
		
		else
		{
			current = list;
			while (current.next != null)
			{
				current = current.next;
			}
			current.next = node;
		}
	}
	
	public boolean isEmpty()
	{
		return list == null;
	}
	

	private class Node
	{
		public Object object;
	    public Node next;

	    public Node (Object object)
	    {
	    	this.object = object;
	    	next = null;
	    }
	}
}
