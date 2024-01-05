import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayGame {

    ArrayList<vertex> problem=null;
    //Every time display game is called, Create Edge and AllPath from WordLadder are
    //called only ONCE
    //User can then play several games
    private WordLadder ladder=new WordLadder("src/words.txt");

    private String difficulty="";
    Scanner input=new Scanner(System.in);
    DisplayGame() throws IOException {

       PlayGame();
       // ArrayList set  = Level(difficulty);
        //fill the array with respect to level of difficulty
        //FillArray(file);

        input.close();

    }
    public void PlayGame() throws IOException {
        //  Scanner input=new Scanner(System.in);
        System.out.println("Choose the level of difficulty. Type the corresponding number:");
        System.out.println("1-EASY");
        System.out.println("2-MEDIUM");
        System.out.println("3-DIFFICULT");
        System.out.println("4-EXIT");
        int read=input.nextInt();

        if(read==1)
            difficulty="easy";
        else if(read==2)
            difficulty="medium";
        else if (read==3)
            difficulty="difficult";
        else if(read==4){
            System.exit(0);
        }




        DisplayAndSolveProblem();

    }





   //O(N)
    public void ChooseProblemToSolve(String difficulty) throws IOException {
     problem= ladder.problem(difficulty);
    }


    public void DisplayAndSolveProblem() throws IOException {

        System.out.println("Guess the words at _____");
        System.out.println("Enter a single word and press Enter.");
        System.out.println("Enter 4 to exit anytime. Enter 5 to go back to the menu.");
        int hint = 2;
        int lost = 0;
        int score = 0;


        //Worst case-O(∞)
        //but the worst isnt very relevant because it is very unlikely
        //Best case-O(1)

        while (true) {
            //new problems get chosen everytime
            ChooseProblemToSolve(difficulty);
            // System.out.println(problem);
            int limit = 5;
            int matched = problem.size() - 2;
            String[][] problemList = new String[problem.size()][2];
            for (int i = 0; i < problem.size(); i++) {
                problemList[i][1] = problem.get(i).meaning;
                if (i == 0 || i == problem.size() - 1) {
                    System.out.println(problem.get(i).word + "   " + problem.get(i).meaning);
                    problemList[i][0] = problem.get(i).word;
                } else System.out.println("_____  " + problem.get(i).meaning);
            }

            //ArrayList usersinput=new ArrayList<>();;
            int index = 1;
            //Scanner input=new Scanner(System.in);
            boolean win = false;
            //maximum runs=11
            while (lost <= 3 && !win) {
                //while(input.hasNext());
                System.out.println("Enter word");
                String entered = input.next();

                if (entered.equals("4") || entered.equals("5")) {
                    if (entered.equals("4")) System.exit(1);
                    if (entered.equals("5")) {
                        PlayGame();
                        return;
                    }
                }


                System.out.println(entered);
                //System.out.println(usersinput);

                if (problem.get(index).word.equalsIgnoreCase(entered)) {
                    problemList[index][0] = problem.get(index).word;
                    System.out.println();
                    printArray(problemList);
                    index++;
                    matched--;
                    score += 2;
                    System.out.println("matched " + problem.get(index - 1));
                } else {
                    if (lost != 4)
                    System.out.println("Wrong answer for " + index + " Try Again ");

                    lost++;

                    if (lost != 4)
                        System.out.println("LIVES LEFT:" + (3 - lost));
                    if (hint > 0 && lost != 4) {
                        System.out.println("Need Hint? Enter yes or no");
                        System.out.println("Hints Left: " + hint);
                        String h = input.next();

                        //worst case=O(∞)
                        //best case-O(1)

                        while (!h.equalsIgnoreCase("yes") && !h.equalsIgnoreCase("no") && lost != 4) {
                            System.out.println("Need Hint? Enter yes or no");
                            h = input.next();
                            if (h.equals("4")) System.exit(1);
                            if (h.equals("5")) {
                                PlayGame();
                                return;
                            }
                        }
                        if (h.equalsIgnoreCase("yes")) {
                            hint--;
                            System.out.println("first letter is: " + problem.get(index).word.toLowerCase().charAt(0) + " and the last letter is: "
                                    + problem.get(index).word.charAt(4));


                        }
                    }


                }


                if (matched == 0) {
                    win = true;
                }

                if (input.equals(4))
                    System.exit(1);


            }
            if (win == true) {
                System.out.println("CONGRATULATIONS.YOU WON!!");
                System.out.println("Score: " + score);
                System.out.println("Want to play more?Enter yes or no");
                String in = input.next();
                if (in.equalsIgnoreCase("no"))
                    System.exit(1);
                else continue;


            } else {
                //System.out.println("LIVES LEFT:"+(3-lost));
                System.out.println("GAME OVER!!");
                System.out.println("Do you want to play again?");
                String h = input.next();
                while (!h.equalsIgnoreCase("yes") && !h.equalsIgnoreCase("no")) {
                    h = input.next();
                }
                if (h.equalsIgnoreCase("yes"))
                    PlayGame();
                else if (h.equalsIgnoreCase("no"))
                    System.exit(1);


            }

        }
    }

    //worst case-length of array=10
   public void printArray(String[][] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i][0]==null) System.out.println("-----"+arr[i][1]);
            else System.out.println(arr[i][0]+"   "+arr[i][1]);
        }
    }
}