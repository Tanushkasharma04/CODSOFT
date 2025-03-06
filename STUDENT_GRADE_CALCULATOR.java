import java.util.Scanner;

public class STUDENT_GRADE_CALCULATOR{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("ENTER THE NUMBER OF STUDENTS: ");
        int numsubjects=sc.nextInt();

        double[] marks=new double[numsubjects];
        double totalmarks=0;
        for (int i = 0; i < numsubjects; i++) {
             
            System.out.println("ENTER THE MARKS OBTAINED IN EACH SUBJECT: "+(i+1)+"(OUT OF 100): ");
            marks[i]=sc.nextDouble();
            totalmarks+=marks[i];
        }
        double averagepercentage=totalmarks/numsubjects;

        String grade=calculateGrade(averagepercentage);
         System.out.println("\n ----results----");
         System.out.println("Total Marks "+totalmarks);
         System.out.printf("Average Percentage : %.2f%%\n",averagepercentage);
         System.out.println("Grade: "+grade);

         sc.close();
    }
    public static String 
    calculateGrade(double Percentage){
        if (Percentage>=90){
            return "A+";
        }else if (Percentage>=80) {
            return "A";
        }else if (Percentage>=70) {
            return "B";
        }else if (Percentage>=60) {
            return "C";
        }else  if (Percentage>=50) {
            return "D";
        }else{
            return "F";
        }
    }
}