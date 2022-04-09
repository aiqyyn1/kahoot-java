package com.example.project2;
import java.util.*;
import java.io.*;
public class Quiz extends Question {
    //private String name;
    public static ArrayList<Question> questions = new ArrayList<>();
    public static ArrayList<String> correct_Answers;

    public Quiz() {
    }

    public static void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {

        return questions;
    }

    public Quiz loadFromFile(String filename) throws FileNotFoundException, InvalidQuizFormatException {
        Quiz quiz = new Quiz();


        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String description = scanner.nextLine();
            System.out.println(description);
            if (description.contains("{blank}")) {
                Fillin fillIn = new Fillin();
                fillIn.setDescription(description);
                fillIn.setAnswer(scanner.nextLine());
                addQuestion(fillIn);
            } else {
                Test test = new Test();
                test.setDescription(description);

//                System.out.println(description);

                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = scanner.nextLine();
                    System.out.println(options[i]);
                }
                test.setAnswer(options[0]);

                test.setOptions(options);
                addQuestion(test);
            }
  if (scanner.hasNextLine())
      scanner.nextLine();
        }

        Collections.shuffle(quiz.questions);


        return quiz;


    }
    public void start() {

        Quiz quiz = new Quiz();
        Scanner in = new Scanner(System.in);
        int score = 0;
        for (Question j : questions) {
            System.out.println(j);
            if (j instanceof Test) {
                System.out.println("Enter the correct choice: ");
                while (true) {
                    try {
                        String insert =in.nextLine();
                        if (insert.length() > 1) {
                            System.out.println("Invalid choice! Try again (Ex: A, B, ...):");
                        } else {
                            char userAnswer = in.nextLine().charAt(0);
                            int ind = (userAnswer - 'A');
                            String lol = ((Test) j).getOptionAt(ind);
                            if (lol.equals(j.getAnswer())) {
                                System.out.println("Correct! Well done! ");
                                score++;
                            } else {
                                System.out.println("Incorrect! ");
                            }
                            break;
                        }
                    } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid choice! Try again (Ex: A, B, ...):");
                    }
                }
            } else {
                System.out.println("Type your answer: ");
                String s=in.nextLine();
                if (s.equals(j.getAnswer())) {
                    System.out.println("Correct ");
                    score++;
                } else {
                    System.out.println("Incorrect! ");
                }
            }

        }

        System.out.printf("\nCorrect Answers: %s/%s (%.1f%c)", score, questions.size(), ((float) (score * 100) / (float) (questions.size())), '%');
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "name='" +  + '\'' +
                ", questions=" + questions +

                '}';
    }
}