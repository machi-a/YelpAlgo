package com.example.demo.nestedTree;

import com.example.demo.reader.ReadToArray;
import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.business.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootApplication
public class StarsAndReviewCount{

	public static MainTreeNode root;
	private static ArrayList<Business> toReturn= new ArrayList<>();




	public static void main(String[] args) {
		String filepath="/Users/charzzzzy/Downloads/yelp_dataset/yelp_academic_dataset_business.json";
//
//		for (Business b: returned){
//			System.out.println(b.getName());
//
//		}
		long startTimeCreate = System.nanoTime();
		LoadNestedTree(filepath);

		long endTimeCreate = System.nanoTime();
		long timeCreate= endTimeCreate- startTimeCreate;
		System.out.println("Time for creating structure : "+ timeCreate);

//        printMainTree(root);
		long startTimeSearch= System.nanoTime();
		try {
			searchByRatingAndReviews(root,3.5,205);
			long endTimeSearch= System.nanoTime();
			System.out.println(toReturn);
			long timeSearch= endTimeSearch-startTimeSearch;
			System.out.println("Time to find: " + timeSearch);
		}catch(BusinessNotFoundException e){
			System.out.println("Business is not found");
		}

//
//		printMainTree(root);
//		try {
//
//			searchByRatingAndReviews(root, 3.5, 5);
//			System.out.println(toReturn);
//		}catch(BusinessNotFoundException e){
//			System.out.println("Business is not found");
//		}
//
//		Scanner sc= new Scanner(System.in);
//		System.out.println("Choose an option:");
//		String option =sc.nextLine();


//		switch(option){
//			case "1":
//				System.out.println("Enter the rating of restaurant:");
//				String input = sc.nextLine();
//				MainTreeNode ratingGroup = searchByRating(root, Double.parseDouble(input));
//				printSubTree(ratingGroup.getRoot());
//				break;
//			case "2":
//				System.out.println("Enter Longitude and Latitude:");
//				input = sc.nextLine();
////				MainTreeNode LatitudeGroup = searchByRating(root, Double.parseDouble(input));
////				printSubTree(ratingGroup.getRoot());
//				System.out.println("Enter the name of restaurant:");
//				break;
//		}


	}

	public static void LoadNestedTree(String filepath){

		ArrayList<Business> returned = readFile(filepath);

//		ArrayList<Business> returned= readFile("/Users/charzzzzy/Downloads/yelp_dataset/yelp_academic_dataset_business.json");
		HashMap<Double, ArrayList<Business> > grouped=groupRatings(returned);


//		for(Double d: grouped.keySet()){
//			System.out.println("_____________________");
//			System.out.println("Ratings "+ d +"  :");
//			createSubTree(grouped.get(d));
//		}

		linkSubTree(groupRatings(returned));
//		printMainTree(root);


	}
	public static ArrayList<Business> readFile(String filepath){
		ArrayList<Business> businessList = new ArrayList<>();
		try {
			FileReader reader = new FileReader(filepath);

			// System.out.println("FILE");
			BufferedReader bufferedReader = new BufferedReader(reader);

			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				Gson gson = new Gson();

				Business business = gson.fromJson(currentLine.toString(), Business.class);
				businessList.add(business);
			}
		} catch (IOException e) {
			System.out.println("No File Found");
		}
		return businessList;
	}





	//    Group  businesses by stars
	public static HashMap<Double, ArrayList<Business> > groupRatings(ArrayList<Business> businessList){

		HashMap<Double, ArrayList<Business> >map = new HashMap<Double, ArrayList<Business> >();
		for(Business b: businessList){
			if(map.containsKey((double)b.getStars())){

				ArrayList<Business> current=map.get((double)b.getStars());
				current.add(b);
				map.put((double)b.getStars(), current);
			}else{
				ArrayList<Business> toAdd= new ArrayList<Business> ();
				toAdd.add(b);
				map.put((double)b.getStars(), toAdd);
			}
		}
		return map;

	}
	// within groups of businesses, organise base on postal code (form subtrees)
	public static SubTreeNode createSubTree(ArrayList<Business> business) {
		SubTreeNode root = null;
		for (Business b : business) {
			if(b.getPostalCode()!=null){
				SubTreeNode store = new SubTreeNode(b, null, null);
				root = insertSubTree(root, store);
			}

		}

		return root;
	}

	private static MainTreeNode searchByRating(MainTreeNode root, Double target)
	{
		// Base Cases: root is null or key is present at root
		if (root==null || Double.parseDouble(root.getValue())==target)
			return root;

		// Key is greater than root's key
		if (Double.parseDouble(root.getValue())<target)
			return searchByRating(root.getRight(), target);

		// Key is smaller than root's key
		return searchByRating(root.getLeft(),target);
	}

	public static void searchByRatingAndReviews(MainTreeNode root, double rating, int reviewCounts) throws BusinessNotFoundException {
		MainTreeNode found= searchByRating(root, rating);
		if(found==null){
			throw new BusinessNotFoundException();
		}
		searchByReviewCount(found.getRoot(), reviewCounts);

//		if(sub==null){
//			throw new BusinessNotFoundException();
//		}
//		return sub.getBusiness();


	}
	private static void searchByReviewCount(SubTreeNode root, int reviewCounts) {
		if (root==null )
			return;

		if(root.getBusiness().getReviewCount()==reviewCounts)
			toReturn.add(root.getBusiness());
		// Key is greater than root's key
		if (root.getBusiness().getReviewCount()< reviewCounts)
			searchByReviewCount(root.getRight(), reviewCounts);

		// Key is smaller than root's key
		searchByReviewCount(root.getLeft(),reviewCounts);
	}


	private static void printSubTree(SubTreeNode root){
		//Check whether tree is empty

		if(root == null){
			System.out.println("Tree is empty");
			return;
		}
		else {

			if(root.getLeft()!= null){
				printSubTree(root.getLeft());
			}

			System.out.print(root.getBusiness().getName() + " ");
			System.out.print(root.getBusiness().getStars() + " ");
			System.out.println(root.getBusiness().getReviewCount());


			if(root.getRight()!= null){
				printSubTree(root.getRight());
			}


		}
	}

	private static void printMainTree(MainTreeNode root){
		//Check whether tree is empty

		if(root == null){
			System.out.println("Tree is empty");
			return;
		}
		else {

			if(root.getLeft()!= null){
				printMainTree(root.getLeft());
			}

			System.out.println("_____________________");
			System.out.println("Ratings " + root.getValue() +" :");
			printSubTree(root.getRoot());


			if(root.getRight()!= null){
				printMainTree(root.getRight());
			}


		}
	}

	private static SubTreeNode insertSubTree(SubTreeNode root, SubTreeNode store) {
		if (root == null) {
			root = store;
			return root;
		}


		if (store.getBusiness().getReviewCount() <= root.getBusiness().getReviewCount()) {
//		if(root.getBusiness().getName().compareTo(store.getBusiness().getName())>0){
			root.setLeft(insertSubTree(root.getLeft(), store));
		}
//		else if(root.getBusiness().getName().compareTo(store.getBusiness().getName())<0){
		else if (store.getBusiness().getReviewCount()> root.getBusiness().getReviewCount()){

			root.setRight(insertSubTree(root.getRight(), store));
		}
		return root;
	}


	// form Main TreeNode - consist of ratings, subtree, left, right, parent
	public static void linkSubTree(HashMap<Double, ArrayList<Business> > map){
		for(double ratings: map.keySet()){
			MainTreeNode toInsert= new MainTreeNode(""+ratings, createSubTree(map.get(ratings)) ,null,null );
			root=insertMainTree(root,toInsert);
		}

	}


	///	insert Treenode into main tree
	public static MainTreeNode insertMainTree(MainTreeNode root, MainTreeNode toInsert)
	{

		if (root == null)
		{
			root=toInsert;
			return root;
		}
		if (Double.parseDouble(toInsert.getValue() )<=Double.parseDouble(root.getValue()))
			root.setLeft(insertMainTree(root.getLeft(), toInsert));
		else if (Double.parseDouble(toInsert.getValue() ) > Double.parseDouble(root.getValue()))
			root.setRight(insertMainTree(root.getRight(), toInsert));
		/* return the (unchanged) node pointer */
		return root;
	}
}
