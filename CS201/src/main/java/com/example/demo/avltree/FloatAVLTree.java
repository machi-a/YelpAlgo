package com.example.demo.avltree;

import com.example.demo.avltree.FloatNode;
import com.example.demo.business.Business;

import java.util.ArrayList;
import java.io.Serializable;

public class FloatAVLTree  implements Serializable {
    private static final long serialVersionUID = 1L;
    private FloatNode rootNode;

    //Constructor to set null value to the rootNode
    public FloatAVLTree()
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
        if(rootNode == null)
            return true;
        else
            return false;
    }



    //create getHeight() method to get the height of the AVL Tree
    private int getHeight(FloatNode node )
    {
        return node == null ? -1 : node.h;
    }

    //create maxNode() method to get the maximum height from left and right node
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight)
    {
        return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
    }

    // create insertElement() to insert element to to the AVL Tree
    public void insertElement(float element, ArrayList<Business> bizList)
    {
        rootNode = insertElement(element, bizList, rootNode);
    }

    //create insertElement() method to insert data in the AVL Tree recursively
    FloatNode insertElement(float element, ArrayList<Business> bizList, FloatNode node)
    {
        //check whether the node is null or not
        if (node == null)
            node = new FloatNode(element, bizList);
            //insert a node in case when the given element is lesser than the element of the root node
        else if (element < node.element)
        {
            node.leftChild = insertElement( element, bizList, node.leftChild );
            if( getHeight( node.leftChild ) - getHeight( node.rightChild ) == 2 )
                if( element < node.leftChild.element )
                    node = rotateWithLeftChild( node );
                else
                    node = doubleWithLeftChild( node );
        }
        else if( element > node.element )
        {
            node.rightChild = insertElement( element, bizList, node.rightChild );
            if( getHeight( node.rightChild ) - getHeight( node.leftChild ) == 2 )
                if( element > node.rightChild.element)
                    node = rotateWithRightChild( node );
                else
                    node = doubleWithRightChild( node );
        }
        else
            ;  // if the element is already present in the tree, we will do nothing
        node.h = getMaxHeight( getHeight( node.leftChild ), getHeight( node.rightChild ) ) + 1;

        return node;

    }

    // creating rotateWithLeftChild() method to perform rotation of binary tree node with left child
    private FloatNode rotateWithLeftChild(FloatNode node2)
    {
        FloatNode node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        node2.h = getMaxHeight( getHeight( node2.leftChild ), getHeight( node2.rightChild ) ) + 1;
        node1.h = getMaxHeight( getHeight( node1.leftChild ), node2.h ) + 1;
        return node1;
    }

    // creating rotateWithRightChild() method to perform rotation of binary tree node with right child
    private FloatNode rotateWithRightChild(FloatNode node1)
    {
        FloatNode node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        node1.h = getMaxHeight( getHeight( node1.leftChild ), getHeight( node1.rightChild ) ) + 1;
        node2.h = getMaxHeight( getHeight( node2.rightChild ), node1.h ) + 1;
        return node2;
    }

    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
    private FloatNode doubleWithLeftChild(FloatNode node3)
    {
        node3.leftChild = rotateWithRightChild( node3.leftChild );
        return rotateWithLeftChild( node3 );
    }

    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child
    private FloatNode doubleWithRightChild(FloatNode node1)
    {
        node1.rightChild = rotateWithLeftChild( node1.rightChild );
        return rotateWithRightChild( node1 );
    }

    //create getTotalNumberOfNodes() method to get total number of nodes in the AVL Tree
    public int getTotalNumberOfNodes()
    {
        return getTotalNumberOfNodes(rootNode);
    }
    private int getTotalNumberOfNodes(FloatNode head)
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
    public boolean searchElement(float element)
    {
        return searchElement(rootNode, element);
    }

    private boolean searchElement(FloatNode head, float element)
    {
        boolean check = false;
        while ((head != null) && !check)
        {
            float headElement = head.element;
            if (element < headElement)
                head = head.leftChild;
            else if (element > headElement)
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
    FloatNode getElement(float element){
        return getElement(rootNode, element);
    }

    FloatNode getElement(FloatNode head, float element)
    {
        boolean check = false;
        while ((head != null) && !check)
        {
            float headElement = head.element;
            if (element < headElement)
                head = head.leftChild;
            else if (element > headElement)
                head = head.rightChild;
            else
            {

                check = true;
                return head;
//                break;
            }
            head = getElement(head, element);
        }
        if (check){
            return head;
        } else {
            return null;
        }
    }

    // create inorderTraversal() method for traversing AVL Tree in in-order form
    public void inorderTraversal()
    {
        inorderTraversal(rootNode);
    }
    private void inorderTraversal(FloatNode head)
    {
        if (head != null)
        {
            inorderTraversal(head.leftChild);
            System.out.print(head.element+" ");
            System.out.print(head.bizList);
            inorderTraversal(head.rightChild);
        }
    }

    // create preorderTraversal() method for traversing AVL Tree in pre-order form
    public void preorderTraversal()
    {
        preorderTraversal(rootNode);
    }
    private void preorderTraversal(FloatNode head)
    {
        if (head != null)
        {
            System.out.print(head.element+" ");
            preorderTraversal(head.leftChild);
            preorderTraversal(head.rightChild);
        }
    }

    // create postorderTraversal() method for traversing AVL Tree in post-order form
    public void postorderTraversal()
    {
        postorderTraversal(rootNode);
    }

    private void postorderTraversal(FloatNode head)
    {
        if (head != null)
        {
            postorderTraversal(head.leftChild);
            postorderTraversal(head.rightChild);
            System.out.print(head.element+" ");
        }
    }


    public void getMinFloat(Float minFloat, ArrayList<Business> returnBizList)
    {
        getMinFloat(minFloat, rootNode, returnBizList);
    }
    private void getMinFloat(Float minFloat, FloatNode head, ArrayList<Business> returnBizList)
    {

        if (head != null && head.element >= minFloat){
            getMinFloat(minFloat, head.leftChild, returnBizList);
            for (Business temp : head.bizList){
                returnBizList.add(temp);
            }
//            System.out.print(head.element+" ");

        } else if (head != null && head.element < minFloat){
            getMinFloat(minFloat, head.rightChild, returnBizList);
        }


    }

}
