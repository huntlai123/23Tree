public class TwoThreeTree
{
    private Node root;
    
    TwoThreeTree()
    {
        root = new Node();
    }
    
    public boolean insert(int x)
    {
        Node finalNode = recurseSearch(root, x); // finding node that might have x in it
        
        if(finalNode.searchNode(x)) // search for x in Node
            return false;
        
        recurse(root, x); // recurse down the tree
        return true;
    }
    
    public void recurse(Node root, int key)
    {
        if(root.isLeaf()) // at a leaf
        {
            root.addKey(key);
            if(root.size() > 2)
                split(root, key);
            return;
        }
        
        recurse(root.childToRecurse(key), key); // recurse down
            
        if(root.size() == 3) // check to see if the node is over full from any keys passed up
            split(root, key); // split if node is over full
        return;
    }
    
    public Node split(Node root, int key)
    {
        Node newChild = new Node(root.getKeys()[root.size() - 1]); // create a new child
        
        if(!root.isLeaf()) // if node being split is not a leaf
            for(int i = root.size(); i > ((root.size() + 1)/2 - 1); i--) // split children to each of the two nodes
            {
                root.getChildren()[i].setParent(newChild);
                newChild.addChild(root.getChildren()[i]);
                root.getChildren()[i] = null;
            }
        
        root.removeKey(root.size() - 1); // remove the key taken out of the root to make the newChild
            
        if(!root.hasParent()) 
        {
            Node newParent = new Node(root.getKeys()[root.size() - 1]); // if no parent, then create a new parent
            root.removeKey(root.size() - 1); // remove the key from root used to make the parent
            newParent.addChild(root); // add root and new child to the parent
            newParent.addChild(newChild);
            root.setParent(newParent); // set the root and newChild's parent attribute
            newChild.setParent(newParent);
            this.root = root.parent(); //change the root of the tree
            
            return newChild; // return the parent
        }
        
        root.parent().addKey(root.getKeys()[root.size() - 1]); //add key to parent if there is a parent
        root.removeKey(root.size() - 1); // remove key added to parent from root
        newChild.setParent(root.parent()); // set newChild's parent to the parent of the root
        root.parent().addChild(newChild);
        
        return newChild;
    }
    
    public String search(int x)
    {
        Node finalNode = recurseSearch(root, x); // calling recursive search to find node
        return finalNode.toString(); // returning node as string
    }
    
    public Node recurseSearch(Node root, int x)
    {
        if (root.isLeaf() || root.searchNode(x)) // if you are at a leaf or x is in the node,
            return root; // then return node.
                
        return recurseSearch(root.childToRecurse(x), x); // searching child
    }
}

class Node
{
    private Integer[] keys;
    private Node[] children;
    private Node parent;
    private int size;
    
    Node(int first)
    {
        keys = new Integer[3];
        keys[0] = first;
        children = new Node[4];
        size = 1;
    }
    
    Node()
    {
        keys = new Integer[3];
        children = new Node[4];
        size = 0;
    }
    
    public Integer[] getKeys()
    {
        return keys;
    }
    
    public Node[] getChildren()
    {
        return children;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }
    
    public int size()
    {
        return size;
    }

    public Node parent()
    {
        return parent;
    }

    public boolean addChild(Node newChild)
    {
        if(children[0] == null) // if 
        {
            children[0] = newChild;
            return true;
        }
        
        children[size] = newChild;
        
        int i = size;
        while(i > 0 && children[i].getKeys()[0] < children[i - 1].getKeys()[0])
        {
                Node temp = children[i];
                children[i] = children[i - 1];
                children[i - 1] = temp;
                i--;
        }
        return true;
    }
    
    public boolean addKey(int key)
    {
        if(size == 0)
        {
            keys[0] = key;
            size++;
            return true;
        }
        
        keys[size] = key;
        size++;
        
        int i = size - 1;
        while(i > 0 && keys[i] < keys[i - 1])
        {
                int temp = keys[i];
                keys[i] = keys[i - 1];
                keys[i - 1] = temp;
                i--;
        }
        return true;
    }

    public boolean hasParent()
    {
        if(parent == null)
            return false;
        return true;
    }

    public boolean isLeaf()
    {
        if(children[0] == null)
            return true;
        return false;
    }
    
    public void removeKey(int index)
    {
        keys[index] = null;
        size--;
    }
    
    public boolean searchNode(int x)
    {
        int i = 0;
        
        while(i < size()) // searching to see if the Node holds the integer x 
        {
            if(keys[i] == x)
                return true;
            i++;
        }
        return false;
    }
    
    public Node childToRecurse(int x)
    {
        int j = 0;
        while(j < size && keys[j] < x) // finding which child to search
            j++;
        
        return children[j];
        
    }
    
    public String toString()
    {
        String output = "";
        for(int i = 0; i < size - 1; i++)
        {
            output += keys[0] + " ";
        }
        
        output += keys[size - 1];
        
        return output;
    }
}