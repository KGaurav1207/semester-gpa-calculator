import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GpaCalculator implements ActionListener {
    JFrame frame;
    Button[] semBtns = new Button[8];

    GpaCalculator() {
        frame = new JFrame("GPA Calculator");
        frame.setSize(600, 450);
        frame.setLayout(new GridLayout(10, 1, 5, 5));

        Label title = new Label("NIT Mizoram CSE GPA Calculator", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(title);

        for (int i = 0; i < 8; i++) {
            semBtns[i] = new Button("Semester " + (i + 1));
            semBtns[i].addActionListener(this);
            frame.add(semBtns[i]);
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    double getGradeValue(String grade) {
        grade = grade.toUpperCase();
        switch (grade) {
            case "AA": return 10;
            case "AB": return 9;
            case "BB": return 8;
            case "BC": return 7;
            case "CC": return 6;
            case "CD": return 5;
            case "DD": return 4;
            default: return 0;
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == semBtns[0])
            new SemesterWindow("Semester 1",
                    new String[]{"Maths I","Physics","Chemistry","Programming","Electrical","English","Workshop","Environmental"},
                    new int[]{4,3,3,3,3,2,2,2});

        if (e.getSource() == semBtns[1])
            new SemesterWindow("Semester 2",
                    new String[]{"Maths II","Mechanics","Electronics","Data Structures","Engineering Graphics","Communication"},
                    new int[]{4,3,3,3,2,2});

        if (e.getSource() == semBtns[2])
            new SemesterWindow("Semester 3",
                    new String[]{"Discrete Math","Digital Logic","OOP","Data Structures Lab","Maths III","Economics"},
                    new int[]{4,3,3,2,4,2});

        if (e.getSource() == semBtns[3])
            new SemesterWindow("Semester 4",
                    new String[]{"Operating Systems","DBMS","Algorithms","Computer Networks","COA","OS Lab"},
                    new int[]{4,3,3,3,3,2});

        if (e.getSource() == semBtns[4])
            new SemesterWindow("Semester 5",
                    new String[]{"Software Engineering","Compiler Design","Machine Learning","Elective I","Networks Lab"},
                    new int[]{3,4,3,3,2});

        if (e.getSource() == semBtns[5])
            new SemesterWindow("Semester 6",
                    new String[]{"AI","Distributed Systems","Cloud Computing","Elective II","Mini Project"},
                    new int[]{3,3,3,3,2});

        if (e.getSource() == semBtns[6])
            new SemesterWindow("Semester 7",
                    new String[]{"Elective III","Elective IV","Internship","Project Phase I"},
                    new int[]{3,3,4,6});

        if (e.getSource() == semBtns[7])
            new SemesterWindow("Semester 8",
                    new String[]{"Project Phase II","Seminar"},
                    new int[]{12,2});
    }

    class SemesterWindow extends JFrame implements ActionListener {
        TextField[] gradeFields;
        int[] credits;
        Label result;
        Button calcBtn;

        SemesterWindow(String semName, String[] subjects, int[] credits) {
            this.credits = credits;
            setTitle(semName);
            setLayout(new GridLayout(subjects.length + 2, 3, 10, 10));
            setSize(600, 500);

            add(new Label("Subject"));
            add(new Label("Grade"));
            add(new Label("Credit"));

            gradeFields = new TextField[subjects.length];

            for (int i = 0; i < subjects.length; i++) {
                add(new Label(subjects[i]));
                gradeFields[i] = new TextField();
                add(gradeFields[i]);
                add(new Label(String.valueOf(credits[i])));
            }

            calcBtn = new Button("Calculate GPA");
            result = new Label("");
            calcBtn.addActionListener(this);

            add(calcBtn);
            add(result);
            add(new Label(""));

            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            double totalPoints = 0;
            int totalCredits = 0;

            for (int i = 0; i < gradeFields.length; i++) {
                double gradeVal = getGradeValue(gradeFields[i].getText());
                totalPoints += gradeVal * credits[i];
                totalCredits += credits[i];
            }

            double gpa = totalPoints / totalCredits;
            result.setText("Your GPA is " + String.format("%.2f", gpa));
        }
    }

    public static void main(String[] args) {
        new GpaCalculator();
    }
}