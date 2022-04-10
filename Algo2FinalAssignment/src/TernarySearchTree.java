import java.util.ArrayList;

class TSTNode
{
   char data;
   Integer value;
   TSTNode left, mid, right;

   public TSTNode(char data, Integer value)
   {
     this.value = value;
     this.data = data;
     left = null;
     mid = null;
     right = null;
   }
}
 
public class TernarySearchTree
{
	public static ArrayList<Integer> listOfAllNames = new ArrayList<>(); 
    TSTNode root;
    
    public TernarySearchTree(){root = null;}
    
    public int get(String key)
    {
        if (key.isEmpty())
        {
            return -1;
        }
        key = key.toUpperCase();
        return get(root, key);
    }

    public int get(TSTNode node, String key)
    {
        char a = key.charAt(0);
        if (node == null)
        {   
            return -1;
        }
        else if (key.length() > 1)
        {
            if (a == node.data)
            {   
                return get(node.mid, key.substring(1));
            }
            else if (a > node.data)
            {   
                return get(node.right, key);
            }
            else
            {   
                return get(node.left, key);
            }
        }
        else
        {   
            if (a == node.data)
            {   
                if (node.value != null)
                {   
                    return node.value;
                }
                else
                {   
                    traverse(node, "");
                    return 0;
                }
            }
            else if (a > node.data)
            {
                if (node.value == null)
                {   
                    return get(node.right, key);
                }
                return -1; 
            }
            else
            {
                if (node.value == null)
                {   
                    return get(node.left, key);
                }
                return -1;
            }
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
                listOfAllNames.add(node.value);
            }
            traverse(node.mid, string);
            string = string.substring(0, string.length() - 1);
            traverse(node.right, string);
        }
    }

    public void put(String key, int value)
    {
        if (key.isEmpty())
        {
            return;
        }
        key = key.toUpperCase();
        root = put(root, key, value);
    }

    public TSTNode put(TSTNode node, String key, int value)
    {
        char c = key.charAt(0);
        if (key.length() > 1)
        {
            if (node == null)
            {   
                node = new TSTNode(c, null);
                node.mid = put(node.mid, key.substring(1), value);
                return node;
            }
            else if (c == node.data)
            {   
                node.mid = put(node.mid, key.substring(1), value);
                return node;
            }
            else if (c < node.data)
            {   
                node.left = put(node.left, key, value);
                return node;
            }
            else
            {  
                node.right = put(node.right, key, value);
                return node;
            }
        }
        else
        {
            if (node == null)
            {
                return new TSTNode(c, value);
            }
            else
            {   
                return node;
            }
        }
    }
}