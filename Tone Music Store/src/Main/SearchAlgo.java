
package Main;

import java.util.LinkedList;

/**
 *
 * @author Ushaan
 */
public class SearchAlgo {
    LinkedList array;
    LinkedList mainIDs;
    LinkedList searchedIds;
    private LinkedList ID;
    
    
 // Implements binary search to search user entered value
   
    public int[] search(String dtype, LinkedList arr, LinkedList pIds, Object givenValue){
        array = (LinkedList) arr.clone();
        mainIDs = (LinkedList) pIds.clone();
        LinkedList<String> searchedIds = new LinkedList();
        int ids = 0;
        String value = (String) givenValue;
      
//Perform search until the searched value is not present in the array 
        while(ids != -1){
            switch(dtype){
                case "String":
                    
                    // Perform sorting
                    selectionSortStr(array, mainIDs);
                    ids = binarySearchStr(array, 0, array.size()-1, value);
                    break;
                case "int":
                    selectionSortInt(array, mainIDs);
                    ids = binarySearchInt(array, 0, array.size()-1, value);
                    break;
            }
            
            //If the searched value if found peform
            if(ids != -1){
               
                //To remove the found value from sorted array to perform search in other rows
                array.remove(ids);
                searchedIds.add(mainIDs.get(ids).toString()); //Adds searched result's product id to the new list
                mainIDs.remove(ids); //remove the searched item's id from sorted list
            }
        }
        
    int[] indexes = new int[searchedIds.size()];
        //loop to search product id and get the index from table 
        for(int k = 0;k<searchedIds.size();k++){
            for (int i = 0; i < pIds.size(); i++) {
                if(searchedIds.get(k) == pIds.get(i)){
                    indexes[k] = i;
                }
            }
        }
        mergeSortInt(indexes);
        return indexes;
    }
    
   
    
//  Applies binary search to the passes array and return index within the array where the value is found
    
    private static int binarySearchStr(LinkedList array, int low, int high, String c) {
        if(low<=high){
            int mid = (low+high)/2;
            if(array.get(mid).toString().compareTo(c) == 0){
                return mid;
            }
            else if (array.get(mid).toString().compareTo(c) < 0){
                return binarySearchStr(array, mid+1,high,c);
            }
            else if (array.get(mid).toString().compareTo(c) > 0){
                return binarySearchStr(array, low,mid-1,c);
            }         
        }
        return -1;
    }
    

//  Applies binary search to the passes array and return index within the array where the value is found
    private static int binarySearchInt(LinkedList array, int low, int high, String c) {
        if(low<=high){
            int mid=(low+high)/2;
            if((Integer.parseInt(array.get(mid).toString())) == Integer.parseInt(c)){
                return mid;
            }
            else if ((Integer.parseInt(array.get(mid).toString())) < Integer.parseInt(c)){
                return binarySearchInt(array, mid+1, high, c);
            }
            else if ((Integer.parseInt(array.get(mid).toString())) > Integer.parseInt(c)){
                return binarySearchInt(array, low, mid-1, c);
            }
        }
        return -1;
    }
    
 
// Sorts the given collections of data and stores sorted form of it by replacing the previous

    private void selectionSortInt(LinkedList list, LinkedList ID){
        for (int i = 0; i < list.size(); i++) {
            int min_ids = i;
            for(int j = min_ids + 1; j<list.size();j++){
                if((Integer) list.get(j) < (Integer) list.get(min_ids)){
                       min_ids = j;
                }
            }
            int temp = (Integer) list.get(i);
            list.set(i, list.get(min_ids));
            list.set(min_ids, temp);
            
            String temp2 = (String) ID.get(i);
            ID.set(i, ID.get(min_ids));
            ID.set(min_ids, temp2);
        }
        this.array = list;
        this.ID = ID;
    }
    

//  Sorts the given collections of data and stores sorted form of it by replacing the previous

    private void selectionSortStr(LinkedList list, LinkedList ID){
        for (int i = 0; i < list.size(); i++) {
            int min_ids = i;
            for(int j = min_ids + 1; j<list.size();j++){
                if(((String) list.get(j)).compareTo((String) list.get(min_ids)) < 0){
                       min_ids = j;
                }
            }
            String temp = (String) list.get(i);
            list.set(i, list.get(min_ids));
            list.set(min_ids, temp);
            
            String temp2 = (String) ID.get(i);
            ID.set(i, ID.get(min_ids));
            ID.set(min_ids, temp2);
        }
        this.array = list;
        this.ID = ID;
    }
    

// Merge sorting array
    public void mergeSortInt(int[] sortArray){  
      if (sortArray.length <= 1) { 
          return; 
      }
      int[] first = new int[sortArray.length / 2];
      int[] second = new int[sortArray.length - first.length];
      
//    Copy the first half of a into first, the second half into second
      for (int i = 0; i < first.length; i++) 
      { 
         first[i] = sortArray[i]; 
      }
      for (int i = 0; i < second.length; i++) 
      { 
         second[i] = sortArray[first.length + i]; 
      }
      mergeSortInt(first);
      mergeSortInt(second);
      merge(first, second, sortArray);
   }

   /*
      Merging two sorted arrays into one array
      first = first sorted array
      second = second sorted array
      sortArray = first and second merged array
   */
   private static void merge(int[] first, int[] second, int[] sortArray){  
       
      int iFirst = 0; // Next element to consider in the first array
      int iSecond = 0; // Next element to consider in the second array
      int j = 0; // Next open position in sortArray

      while (iFirst < first.length && iSecond < second.length)
      {  
         if (first[iFirst] < second[iSecond])
         {  
            sortArray[j] = first[iFirst];
            iFirst++;
         }
         else
         {  
            sortArray[j] = second[iSecond];
            iSecond++;
         }
         j++;
      }

      while (iFirst < first.length) 
      { 
         sortArray[j] = first[iFirst]; 
         iFirst++; j++;
      }
      while (iSecond < second.length) 
      { 
         sortArray[j] = second[iSecond]; 
         iSecond++; j++;
      }
   }        
}
