import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;

class DayFinder extends JFrame {

    // Declare components
    private JTextField inputField;
    private JTextField resultField;
    private JButton findButton;

    public DayFinder() {
        // Set the title of the frame
        setTitle("Day Finder");

        // Set the layout manager
        setLayout(new FlowLayout());

        // Create input field for the date
        inputField = new JTextField(20);
        inputField.setToolTipText("Enter date as dd mm yyyy");

        // Create output field for the result
        resultField = new JTextField(20);
        resultField.setEditable(false);
        resultField.setToolTipText("Day of the week will be displayed here");

        // Create button to find the day
        findButton = new JButton("Find Day");

        // Add event listener to the button
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get input from the user
                String dateText = inputField.getText();
                // Split the input into day, month, and year
                String[] dateParts = dateText.split(" ");

                if (dateParts.length == 3) {
                    try {
                        int d = Integer.parseInt(dateParts[0]);
                        int m = Integer.parseInt(dateParts[1]);
                        int y = Integer.parseInt(dateParts[2]);
                        // Calculate the day of the week
                        String dayName = findDay(d, m, y);
                        resultField.setText(dayName);
                    } catch (NumberFormatException ex) {
                        resultField.setText("Invalid date format");
                    }
                } else {
                    resultField.setText("Please enter in format dd mm yyyy");
                }
            }
        });

        // Add components to the frame
        add(new JLabel("Enter Date (dd mm yyyy):"));
        add(inputField);
        add(findButton);
        add(resultField);

        // Set the default close operation and frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);
    }

    // Method to calculate the day of the week
    public String findDay(int d, int m, int y) {
        int[] m1 = {3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3};
        int[] m2 = {3, 1, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3};

        int r1 = y % 100;
        int r = y / 100;
        int y1 = r % 4;
        int t;
        switch (y1) {
            case 0:
                t = 0;
                break;
            case 1:
                t = 5;
                break;
            case 2:
                t = 3;
                break;
            case 3:
                t = 1;
                break;
            default:
                t = 0; // default
        }

        int r2 = r1 - 1;
        int t1 = r2 % 7;
        int r3 = r2 / 4;
        int t2 = r3 % 7;

        int sum = 0;
        if (y % 400 == 0) {
            for (int i = 0; i < m - 1; i++) {
                sum += m2[i];
            }
        } else {
            if (r1 % 4 == 0) {
                for (int i = 0; i < m - 1; i++) {
                    sum += m2[i];
                }
            } else {
                for (int i = 0; i < m - 1; i++) {
                    sum += m1[i];
                }
            }
        }

        int sum1 = d + sum + t + t1 + t2;
        int odd = sum1 % 7;

        String dayName;
        switch (odd) {
            case 0:
                dayName = "Sunday";
                break;
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            default:
                dayName = "Invalid";
        }
        return dayName;
    }

    public static void main(String[] args) {
        // Run the application
        new DayFinder();
    }
}
