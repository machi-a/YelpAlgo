package com.example.demo.avltree;

import com.example.demo.business.Business;

import java.util.ArrayList;

public class StringAVLTree {

    StringNode rootNode;

    //Constructor to set null value to the rootNode
    public StringAVLTree()
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

    // create insertElement() to insert element to to the AVL Tree
    public void insertElement(String element, ArrayList<Business> bizList)
    {
        rootNode = insertElement(element, bizList, rootNode);
    }

    //create getHeight() method to get the height of the AVL Tree
    private int getHeight(StringNode node)
    {
        return node == null ? -1 : node.h;
    }

    //create maxNode() method to get the maximum height from left and right node
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight)
    {
        return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
    }


    //create insertElement() method to insert data in the AVL Tree recursively
    private StringNode insertElement(String element, ArrayList<Business> bizList, StringNode node)
    {
        //check whether the node is null or not
        if (node == null)
            node = new StringNode(element, bizList);
            //insert a node in case when the given element is lesser than the element of the root node
        else if (element.compareTo(node.element) <= 0)
        {
            node.leftChild = insertElement( element, bizList, node.leftChild );
            if( getHeight( node.leftChild ) - getHeight( node.rightChild ) == 2 )
                if( element.compareTo(node.leftChild.element) <= 0 )
                    node = rotateWithLeftChild( node );
                else
                    node = doubleWithLeftChild( node );
        }
        else if( element.compareTo(node.element) > 0 )
        {
            node.rightChild = insertElement( element, bizList,node.rightChild );
            if( getHeight( node.rightChild ) - getHeight( node.leftChild ) == 2 )
                if( element.compareTo(node.rightChild.element) > 0)
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
    private StringNode rotateWithLeftChild(StringNode node2)
    {
        StringNode node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        node2.h = getMaxHeight( getHeight( node2.leftChild ), getHeight( node2.rightChild ) ) + 1;
        node1.h = getMaxHeight( getHeight( node1.leftChild ), node2.h ) + 1;
        return node1;
    }

    // creating rotateWithRightChild() method to perform rotation of binary tree node with right child
    private StringNode rotateWithRightChild(StringNode node1)
    {
        StringNode node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        node1.h = getMaxHeight( getHeight( node1.leftChild ), getHeight( node1.rightChild ) ) + 1;
        node2.h = getMaxHeight( getHeight( node2.rightChild ), node1.h ) + 1;
        return node2;
    }

    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
    private StringNode doubleWithLeftChild(StringNode node3)
    {
        node3.leftChild = rotateWithRightChild( node3.leftChild );
        return rotateWithLeftChild( node3 );
    }

    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child
    private StringNode doubleWithRightChild(StringNode node1)
    {
        node1.rightChild = rotateWithLeftChild( node1.rightChild );
        return rotateWithRightChild( node1 );
    }

    //create getTotalNumberOfNodes() method to get total number of nodes in the AVL Tree
    public int getTotalNumberOfNodes()
    {
        return getTotalNumberOfNodes(rootNode);
    }

    private int getTotalNumberOfNodes(StringNode head)
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
    public boolean searchElement(String element)
    {
        return searchElement(rootNode, element);
    }

    private boolean searchElement(StringNode head, String element)
    {
        boolean check = false;
        while ((head != null) && !check)
        {
            String headElement = head.element;
            if (element.compareTo(headElement) < 0)
                head = head.leftChild;
            else if (element.compareTo(headElement) > 0)
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

    StringNode getElement(String element){
        return getElement(rootNode, element);
    }

    StringNode getElement(StringNode head, String element)
    {
        boolean check = false;
        while ((head != null) && !check)
        {
            String headElement = head.element;
            if (element.compareTo(headElement) < 0)
                head = head.leftChild;
            else if (element.compareTo(headElement) > 0)
                head = head.rightChild;
            else
            {
                check = true;
                break;
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
    private void inorderTraversal(StringNode head)
    {
        if (head != null)
        {
            inorderTraversal(head.leftChild);
            System.out.print(head.bizList+" ");
            inorderTraversal(head.rightChild);
        }
    }

    // create preorderTraversal() method for traversing AVL Tree in pre-order form
    public void preorderTraversal()
    {
        preorderTraversal(rootNode);
    }
    private void preorderTraversal(StringNode head)
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

    private void postorderTraversal(StringNode head)
    {
        if (head != null)
        {
            postorderTraversal(head.leftChild);
            postorderTraversal(head.rightChild);
            System.out.print(head.element+" ");
        }
    }

    public void returnOrderedList(ArrayList<Business> returnBizList){ returnOrderedList(rootNode, returnBizList); }

    public void returnOrderedList(StringNode head, ArrayList<Business> returnBizList){
        if (head != null)
        {
            returnOrderedList(head.leftChild, returnBizList);
//            System.out.println(head.element);
            returnBizList.addAll(head.bizList);
            returnOrderedList(head.rightChild, returnBizList);
        }
    }

    public void getStateList(String state, ArrayList<Business> returnBizList)
    {
        getStateList(state, rootNode, returnBizList);
    }
    private void getStateList(String state, StringNode head, ArrayList<Business> returnBizList)
    {
//        System.out.println(head.element);
        if (head != null && state.compareTo(head.element) == 0)
        {

            returnBizList.addAll(head.bizList);
//            System.out.print(head.element+" ");

        } else if (head != null){
            getStateList(state, head.leftChild, returnBizList);
            getStateList(state, head.rightChild, returnBizList);
        }


    }
    public void getCityList(String city, ArrayList<Business> returnBizList)
    {
        getCityList(city, rootNode, returnBizList);
    }
    private void getCityList(String city, StringNode head, ArrayList<Business> returnBizList)
    {

//        System.out.println(head.element);
        if (head != null && city.compareTo(head.element) == 0)
        {

            returnBizList.addAll(head.bizList);
//            System.out.print(head.element+" ");

        } else if (head != null){
            getCityList(city, head.leftChild, returnBizList);
            getCityList(city, head.rightChild, returnBizList);
        }


    }
}
