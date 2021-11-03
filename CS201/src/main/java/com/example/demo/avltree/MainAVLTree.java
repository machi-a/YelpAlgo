package com.example.demo.avltree;
import  com.example.demo.business.Business;
import com.example.demo.avltree.BusinessNode;

class MainAVLTree
{
    private BusinessNode rootNode;

    //Constructor to set null value to the rootNode
    public MainAVLTree()
    {
        rootNode = null;
    }

    //create removeAll() method to make AVL Tree empty
    public void removeAll()
    {
        rootNode = null;
    }

    // create checkEmpty() method to check whether the AVL Tree is empty or not
    public boolean checkEmpty()
    {
        return rootNode == null;
    }

    // create insertElement() to insert element into the AVL Tree
    public void insertElement(Business element)
    {
        rootNode = insertElement(element, rootNode);
    }

    //create getHeight() method to get the height of the AVL Tree
    private int getHeight(BusinessNode node )
    {
        return node == null ? -1 : node.h;
    }

    //create maxNode() method to get the maximum height from left and right node
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight)
    {
        return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
    }


    //create insertElement() method to insert data in the AVL Tree recursively
    private BusinessNode insertElement(Business element, BusinessNode node)
    {
        //check whether the node is null or not
        if (node == null)
            node = new BusinessNode(element);
            //insert a node in case when the given element is lesser than the element of the root node
        else if (element.getBusinessId().compareTo(node.element.getBusinessId()) < 0)
        {
            node.leftChild = insertElement( element, node.leftChild );
            if( getHeight( node.leftChild ) - getHeight( node.rightChild ) == 2 )
                if( element.getBusinessId().compareTo(node.leftChild.element.getBusinessId()) < 0 )
                    node = rotateWithLeftChild( node );
                else
                    node = doubleWithLeftChild( node );
        }
        else if( element.getBusinessId().compareTo(node.element.getBusinessId()) > 0 )
        {
            node.rightChild = insertElement( element, node.rightChild );
            if( getHeight( node.rightChild ) - getHeight( node.leftChild ) == 2 )
                if( element.getBusinessId().compareTo(node.rightChild.element.getBusinessId()) > 0)
                    node = rotateWithRightChild( node );
                else
                    node = doubleWithRightChild( node );
        }  // if the element is already present in the tree, we will do nothing

        node.h = getMaxHeight( getHeight( node.leftChild ), getHeight( node.rightChild ) ) + 1;

        return node;

    }

    // creating rotateWithLeftChild() method to perform rotation of binary tree node with left child
    private BusinessNode rotateWithLeftChild(BusinessNode node2)
    {
        BusinessNode node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        node2.h = getMaxHeight( getHeight( node2.leftChild ), getHeight( node2.rightChild ) ) + 1;
        node1.h = getMaxHeight( getHeight( node1.leftChild ), node2.h ) + 1;
        return node1;
    }

    // creating rotateWithRightChild() method to perform rotation of binary tree node with right child
    private BusinessNode rotateWithRightChild(BusinessNode node1)
    {
        BusinessNode node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        node1.h = getMaxHeight( getHeight( node1.leftChild ), getHeight( node1.rightChild ) ) + 1;
        node2.h = getMaxHeight( getHeight( node2.rightChild ), node1.h ) + 1;
        return node2;
    }

    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
    private BusinessNode doubleWithLeftChild(BusinessNode node3)
    {
        node3.leftChild = rotateWithRightChild( node3.leftChild );
        return rotateWithLeftChild( node3 );
    }

    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child
    private BusinessNode doubleWithRightChild(BusinessNode node1)
    {
        node1.rightChild = rotateWithLeftChild( node1.rightChild );
        return rotateWithRightChild( node1 );
    }

    //create getTotalNumberOfNodes() method to get total number of nodes in the AVL Tree
    public int getTotalNumberOfNodes()
    {
        return getTotalNumberOfNodes(rootNode);
    }
    private int getTotalNumberOfNodes(BusinessNode head)
    {
        if (head == null)
            return 0;
        else
        {
            int length = 1;
            length = length + getTotalNumberOfNodes(head.leftChild);
            length = length + getTotalNumberOfNodes(head.rightChild);
            return length;
        }
    }

    //create searchElement() method to find an element in the AVL Tree
    public boolean searchElement(Business element)
    {
        return searchElement(rootNode, element);
    }

    private boolean searchElement(BusinessNode head, Business element)
    {
        boolean check = false;
        while ((head != null) && !check)
        {
            Business headElement = head.element;
            if (element.getBusinessId().compareTo(headElement.getBusinessId()) < 0)
                head = head.leftChild;
            else if (element.getBusinessId().compareTo(headElement.getBusinessId()) > 0)
                head = head.rightChild;
            else
            {
                check = true;
                break;
            }
            check = searchElement(head, element);
        }
        return check;
    }
    // create inorderTraversal() method for traversing AVL Tree in in-order form
    public void inorderTraversal()
    {
        inorderTraversal(rootNode);
    }
    private void inorderTraversal(BusinessNode head)
    {
        if (head != null)
        {
            inorderTraversal(head.leftChild);
            System.out.print(head.element.getBusinessId()+" ");
            inorderTraversal(head.rightChild);
        }
    }

    // create preorderTraversal() method for traversing AVL Tree in pre-order form
    public void preorderTraversal()
    {
        preorderTraversal(rootNode);
    }
    private void preorderTraversal(BusinessNode head)
    {
        if (head != null)
        {
            System.out.print(head.element.getBusinessId()+" ");
            preorderTraversal(head.leftChild);
            preorderTraversal(head.rightChild);
        }
    }

    // create postorderTraversal() method for traversing AVL Tree in post-order form
    public void postorderTraversal()
    {
        postorderTraversal(rootNode);
    }

    private void postorderTraversal(BusinessNode head)
    {
        if (head != null)
        {
            postorderTraversal(head.leftChild);
            postorderTraversal(head.rightChild);
            System.out.print(head.element.getBusinessId()+" ");
        }
    }
}
