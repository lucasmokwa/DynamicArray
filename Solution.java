import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    // Solution to HackerRank Dynamic Array Challenge

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
    
        //initialize n Array lists
        ArrayList<Integer>[] set = new ArrayList[n];

        for (int i = 0; i < n; i++) { 
            set[i] = new ArrayList<Integer>(); 
        } 

        //initialize buffer list 
        List<Integer> currentList = new LinkedList<>();
        
        //initialize our output list
        List<Integer> output = new LinkedList<>();
        int lastAns = 0;
        //perform operations for all lists
        while(!queries.isEmpty()){
            
            currentList = queries.remove(0);
            lastAns = performQuery(set, output, currentList, lastAns);
        }
        
        return output;

    }
    
    public static int performQuery(List<Integer>[] set, List<Integer> output, List<Integer> query, int lastAns){
        //initialize aux variables
        int queryType, x, y;
        queryType = query.remove(0);
        x = query.remove(0);
        y = query.remove(0);
        
        //return size of set
        int n = set.length;
        
        //find index
        int i = (x^lastAns) % n;
        
        if(queryType == 1){
           
           set[i].add(y);
           
        }else if(queryType == 2){
            lastAns = set[i].get(y%set[i].size());
            output.add(lastAns);
        }
        
        return lastAns;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.dynamicArray(n, queries);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
