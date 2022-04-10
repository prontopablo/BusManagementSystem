import java.util.ArrayList;

//class TSTNode 
class TSTNode
{
    Integer value;
	char data;
    boolean isEnd;
    TSTNode left, middle, right;
 
    /** Constructor **/
    public TSTNode(char data, Integer value)
    {
        this.data = data;
        this.isEnd = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }        
}
 
//class TernarySearchTree 
class TernarySearchTree
{
    private TSTNode root;
    private ArrayList<String> al;
    public static ArrayList<Integer> listOfAllNames = new ArrayList<>();
 
    // Constructor
    public TernarySearchTree()
    {
        root = null;
    }
    // function to check if empty 
    public boolean isEmpty()
    {
        return root == null;
    }
    // function to clear
    public void makeEmpty()
    {
        root = null;
    }
    //function to insert
    public void insert(String word)
    {
        root = insert(root, word.toCharArray(), 0);
    }
    //function to insert
    public TSTNode insert(TSTNode r, char[] word, int ptr)
    {
        if (r == null)
            r = new TSTNode(word[ptr], ptr);
 
        if (word[ptr] < r.data)
            r.left = insert(r.left, word, ptr);
        else if (word[ptr] > r.data)
            r.right = insert(r.right, word, ptr);
        else
        {
            if (ptr + 1 < word.length)
                r.middle = insert(r.middle, word, ptr + 1);
            else
                r.isEnd = true;
        }
        return r;
    }
 
    //
    public void delete(String word)
    {
        delete(root, word.toCharArray(), 0);
    }
    //function to delete
    private void delete(TSTNode r, char[] word, int ptr)
    {
        if (r == null)
            return;
 
        if (word[ptr] < r.data)
            delete(r.left, word, ptr);
        else if (word[ptr] > r.data)
            delete(r.right, word, ptr);
        else
        {
            //to delete  just make isEnd false
            if (r.isEnd && ptr == word.length - 1)
                r.isEnd = false;
 
            else if (ptr + 1 < word.length)
                delete(r.middle, word, ptr + 1);
        }        
    }
    private void traverse(TSTNode node, String string)
    {
        if (node != null)
        {
            traverse(node.left, string);
            string = string + node.data;
            if (node.value != null)
            {   
            	//If last node then add Line ID to allNames ArrayList.
                listOfAllNames.add(node.value);
            }
            traverse(node.middle, string);
            string = string.substring(0, string.length() - 1);
            traverse(node.right, string);
        }
    }
    // function to search
    public boolean search(String word)
    {
        return search(root, word.toCharArray(), 0);
    }
    // function to search
    private boolean search(TSTNode r, char[] word, int ptr)
    {
        if (r == null)
            return false;
 
        if (word[ptr] < r.data)
            return search(r.left, word, ptr);
        else if (word[ptr] > r.data)
            return search(r.right, word, ptr);
        else
        {
            if (r.isEnd && ptr == word.length - 1)
                return true;
            else if (ptr == word.length - 1)
                return false;
            else
                return search(r.middle, word, ptr + 1);
        }        
    }
    protected void put(String key, int value)
    {
        if (key.isEmpty())
        {
        	//Don't do anything with invalid input.
            System.out.println("invalid input to TST.put()!");
            return;
        }
        //TST is in upper-case.
        key = key.toUpperCase();
        root = put(root, key, value);
    }
    protected TSTNode put(TSTNode node, String key, int value)
    {
        char c = key.charAt(0);
        if (key.length() > 1)
        {
            if (node == null)
            {   
            	//Fully new string, make new intermediate node and continue down.
                node = new TSTNode(c, null);
                node.middle = put(node.middle, key.substring(1), value);
                return node;
            }
            else if (c == node.data)
            {   
            	//Identical substring, continue down.
                node.middle = put(node.middle, key.substring(1), value);
                return node;
            }
            else if (c < node.data)
            {   
            	//Mismatch, continue tree left.
                node.left = put(node.left, key, value);
                return node;
            }
            else
            {   
            	//Mismatch, continue tree right.
                node.right = put(node.right, key, value);
                return node;
            }
        }
        //On last node to add.
        else
        {
        	//New string ends here.
            if (node == null)
            {
                return new TSTNode(c, value);
            }
            else
            {   
            	//Duplicate string, leave unchanged.
                return node;
            }
            }
        }
    //function to print tree
    public String toString()
    {
        al = new ArrayList<String>();
        traverse(root, "");
        return "\nTernary Search Tree : "+ al;
    }
    public int get(String key)
    {
        if (key.isEmpty())
        {
            return -1;
        }
        key = key.toUpperCase(); 
        return get(root, key);
    }
    
    protected int get(TSTNode node, String key)
    {
        char c = key.charAt(0);
        if (node == null)
        {   
        	//Search miss.
            return -1;
        }
        else if (key.length() > 1)
        {
            if (c == node.data)
            {   
            	//Continue down.
                return get(node.middle, key.substring(1));
            }
            else if (c > node.data)
            {   
            	//Continue right.
                return get(node.right, key);
            }
            else
            {   //Continue left.
                return get(node.left, key);
            }
        }
        else
        {   
        	//Last node.
            if (c == node.data)
            {   
            	//Search hit.
                if (node.value != null)
                {   
                	//One result.
                    return node.value;
                }
                else
                {   
                	//Multiple results.
                    traverse(node, "");
                    return 0;
                }
            }
            else if (c > node.data)
            {
                if (node.value == null)
                {   
                	//Continue right.
                    return get(node.right, key);
                }
                //Search miss
                return -1; 
            }
            else
            {
                if (node.value == null)
                {   
                	//Continue left.
                    return get(node.left, key);
                }
                //Search miss
                return -1;
            }
        }
    }
}
 