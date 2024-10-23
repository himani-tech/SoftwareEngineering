package main;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.io.*;
import java.util.Date;

public class Prescription
{
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = {"Client", "Optometrist"};
    private ArrayList<String> postRemarks = new ArrayList<>();

    // Constructor accepting date as String and converting it to Date
    public Prescription(int prescID, String firstName, String lastName, String address, float sphere, float cylinder, float axis, String examinationDateStr, String optometrist) {
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.cylinder = cylinder;
        this.axis = axis;
        this.optometrist = optometrist;

        // Validate and convert the date string
        if (validateExaminationDate(examinationDateStr)) {
            this.examinationDate = convertToDate(examinationDateStr); // Convert string to Date
        } else {
            this.examinationDate = null; // Invalid date
        }
    }

    public boolean addPrescription() {
        // Validate first name and last name (4-15 characters)
        if (firstName.length() < 4 || lastName.length() < 4 || firstName.length() > 15 || lastName.length() > 15) {
            return false;
        }

        // Validate address length (at least 20 characters)
        if (address.length() < 20) {
            return false;
        }

        // Validate sphere, cylinder, and axis
        if (sphere < -20.0 || sphere > 20.0 || cylinder < -4.0 || cylinder > 4.0 || axis < 0 || axis > 180) {
            return false;
        }

        // Validate the examination date
        if (examinationDate == null) {
            return false; // Invalid date
        }

        // Validate optometrist name (8-25 characters)
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        // If all checks are valid, write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("presc.txt", true))) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            String formattedDate = sdf.format(examinationDate);
            writer.write("Prescription ID: " + prescID + ", " + firstName + " " + lastName + ", Address: " + address +
                         ", Sphere: " + sphere + ", Cylinder: " + cylinder + ", Axis: " + axis +
                         ", Exam Date: " + formattedDate + ", Optometrist: " + optometrist + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Validate the examination date format (DD/MM/YY)
    private boolean validateExaminationDate(String dateStr) {
        if (dateStr == null || dateStr.length() != 8) {
            return false; // Date should not be null and must have 8 characters (DD/MM/YY)
        }

        String[] dateParts = dateStr.split("/");

        // Check if the date is split into exactly 3 parts (day, month, year)
        if (dateParts.length != 3) {
            return false; // Invalid date format
        }

        try {
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            // Validate the day, month, and year ranges
            if (day < 1 || day > 31 || month < 1 || month > 12 || year < 0 || year > 99) {
                return false;
            }

            // Check for months with less than 31 days
            if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                return false;
            }

            // Check for February (28 days, no leap year logic here)
            if (month == 2 && day > 28) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false; // If parsing fails, return false
        }

        return true; // Date is valid
    }

    // Convert the validated string date to a Date object
    private Date convertToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        try {
            return sdf.parse(dateStr); // Convert to Date
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Return null if parsing fails
        }
    }

    // Method to add a remark
    public boolean addRemark(String remark, String category) {
        // Condition 1: Remark length should be between 6 and 20 words
        String[] words = remark.split(" ");
        if (words.length < 6 || words.length > 20) {
            return false;
        }

        // Condition 2: First word should start with upper case
        if (!Character.isUpperCase(remark.charAt(0))) {
            return false;
        }

        // Condition 3: Check if category is valid
        if (!category.equalsIgnoreCase("Client") && !category.equalsIgnoreCase("Optometrist")) {
            return false;
        }

        // Condition 4: Prescription can only have 2 remarks
        if (postRemarks.size() >= 2) {
            return false;
        }

        // If all validations pass, add the remark to the list and write to the file
        postRemarks.add(remark);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("remark.txt", true))) {
            writer.write("Prescription ID: " + prescID + ", Remark: " + remark + ", Category: " + category + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        Prescription e = new Prescription(1, "Himani", "Singla", "12 souvenir Street, tarneit, 3029, Australia", -5.0f, 2.0f, 2.0f, "05/12/24", "Dr.Nandini");
        System.out.println("Prescription added: " + e.addPrescription());
    }
}