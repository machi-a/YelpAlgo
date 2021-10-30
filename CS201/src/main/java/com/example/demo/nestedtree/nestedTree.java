package com.example.demo.NestedTree;



import com.example.demo.business.Business;

import java.util.ArrayList;
import java.util.HashMap;

public class nestedTree {

    private static MainTreeNode root;

    public static void main(String[] args) {

    }


//    Group  businesses by stars
    public static HashMap<Double, ArrayList<Business> > groupRatings(ArrayList<Business> businessList){

        HashMap<Double, ArrayList<Business> >map = new HashMap<Double, ArrayList<Business> >();
        for(Business b: businessList){
            if(map.containsKey(Double.parseDouble(b.getStars()))){

                ArrayList<Business> current=map.get(Double.parseDouble(b.getStars()));
                current.add(b);
                map.put(Double.parseDouble(b.getStars()), current);
            }else{
                ArrayList<Business> toAdd= new ArrayList<>();
                toAdd.add(b);
                map.put(Double.parseDouble(b.getStars()), toAdd);
            }
        }
        return map;

    }
// within groups of businesses, organise base on postal code (form subtrees)
    public static SubTreeNode createSubTree(ArrayList<Business> business) {

        for (Business b : business) {
            SubTreeNode store = new SubTreeNode(Double.parseDouble(b.getPostalCode()), null, null);

        }
        return null;
    }




//    public static void InsertIntoTree(HashMap<Double, ArrayList<Business> > map){
//        for(double d: map.keySet()){
//            MainTreeNode ref=createSubTree(map.get(d));
//            MainTreeNode toInsert= new MainTreeNode(d,ref,null,null);
//            formTree(root,toInsert);
//        }
//    }

    ///	insert Treenode into main tree
    public static MainTreeNode formTree(MainTreeNode root, MainTreeNode toInsert)
    {

        if (root == null)
        {
            root=toInsert;
            return root;
        }
        if (toInsert.getValue() < root.getValue())
            root.setLeft(formTree(root.getLeft(), toInsert));
        else if (toInsert.getValue() > root.getValue())
            root.setRight(formTree(root.getRight(), toInsert));

        /* return the (unchanged) node pointer */
        return root;
    }

}
