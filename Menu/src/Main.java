import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3307/test";
        String username = "root";
        // need to add password
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             Scanner scanner = new Scanner(System.in)) {  // Create a Scanner object to read input from user.
            while (true) {
                System.out.println("Select a query to execute:");
                System.out.println("1. 1.1 Room Utilization");
                System.out.println("2. 1.2 Room Utilization");
                System.out.println("3. 1.3 Room Utilization");
                System.out.println("4. 2.1 Patient Information");
                System.out.println("5. 2.2 Patient Information");
                System.out.println("6. 2.3 Patient Information");
                System.out.println("7. 2.4 Patient Information");
                System.out.println("8. 2.5 Patient Information");
                System.out.println("9. 2.6 Patient Information");
                System.out.println("10. 2.7 Patient Information");
                System.out.println("11. 2.8 Patient Information");
                System.out.println("12. 3.1 Diagnosis and Treatment Information");
                System.out.println("13. 3.2 Diagnosis and Treatment Information");
                System.out.println("14. 3.3 Diagnosis and Treatment Information");
                System.out.println("15. 3.4 Diagnosis and Treatment Information");
                System.out.println("16. 3.5 Diagnosis and Treatment Information");
                System.out.println("17. 4.1 Employee Information");
                System.out.println("18. 4.2 Employee Information");
                System.out.println("19. 4.3 Employee Information");
                System.out.println("20. 4.4 Employee Information");
                System.out.println("21. 4.5 Employee Information");
                System.out.println("22. Quit");

                int choice = scanner.nextInt();  // Reads input from user using Scanner.

                if (choice == 22) {
                    break;
                }

                String query = "";
                switch (choice) {
                    case 1:
                        System.out.println("1.1. Room Utilization");
                        query = "SELECT rooms.room_number, patients.patient_name, admissions.admit_date " +
                                "FROM rooms " +
                                "JOIN admissions ON rooms.room_number = admissions.room_number " +
                                "JOIN patients ON admissions.patient_id = patients.patient_id " +
                                "WHERE rooms.occupied = TRUE";
                        break;
                    case 2:
                        System.out.println("1.2. Room Utilization");
                        query = "SELECT * FROM rooms WHERE occupied = FALSE;";
                        break;
                    case 3:
                        System.out.println("1.3. Room Utilization");
                        query = "SELECT rooms.room_number, patients.patient_name, admissions.admit_date " +
                        "FROM rooms " +
                        "INNER JOIN admissions ON rooms.room_number = admissions.room_number " +
                        "INNER JOIN patients ON admissions.patient_id = patients.patient_id";
                        break;
                    case 4:
                        System.out.println("2.1 Patient Information");
                        query = "SELECT * FROM patients;";
                        break;
                    case 5:
                        System.out.println("2.2 Patient Information");
                        query = "SELECT patient_id, patient_name " +
                        "FROM patients " +
                        "WHERE patient_id IN (SELECT patient_id FROM admissions WHERE discharge_date IS NULL)";
                        break;
                    case 6:
                        System.out.println("2.3 Patient Information");
                        query = "SELECT patient_id, patient_name " +
                        "FROM patients " +
                        "WHERE patient_id IN (SELECT patient_id FROM admissions " +
                                "WHERE discharge_date BETWEEN '2000-01-01' AND '2024-01-01')";
                        break;
                    case 7:
                        System.out.println("2.4 Patient Information");
                        query = "SELECT patient_id, patient_name " +
                                "FROM patients " +
                                "WHERE patient_id IN (SELECT patient_id FROM admissions WHERE discharge_date IS NULL)";
                        break;
                    case 8:
                        System.out.println("2.5 Patient Information");
                        query = "SELECT a.admission_id, a.patient_id, a.admit_date, a.discharge_date, a.diagnosis_id, a.admitting_doctor, p.patient_name " +
                                "FROM admissions a " +
                                "INNER JOIN patients p ON a.patient_id = p.patient_id " +
                                "WHERE a.patient_id = '1' OR p.patient_name = 'patientName' " +
                                "ORDER BY a.admit_date DESC";
                        break;
                    case 9:
                        System.out.println("2.6 Patient Information");
                        query = "SELECT admissions.admission_id, admissions.admit_date, admissions.discharge_date, treatments.treatment_name, treatments.treatment_date " +
                                "FROM patients " +
                                "JOIN admissions ON patients.patient_id = admissions.patient_id " +
                                "JOIN treatments ON admissions.admission_id = treatments.admission_id " +
                                "WHERE patients.patient_id = 1 OR patients.patient_name = 'John Doe' " +
                                "ORDER BY admissions.admit_date DESC, treatments.treatment_date ASC";
                        break;
                        case 10:
                        System.out.println("2.7 Patient Information");
                        query = "SELECT p.patient_id, p.patient_name, d.diagnosis_name, CONCAT(e.first_name, ' ', e.last_name) AS doctor_name " +
                                    "FROM patients p " +
                                    "JOIN admissions a ON p.patient_id = a.patient_id " +
                                    "JOIN diagnoses d ON a.diagnosis_id = d.diagnosis_id " +
                                    "JOIN employees e ON a.admitting_doctor = e.employee_id " +
                                    "WHERE a.admit_date <= DATE_SUB(a.discharge_date, INTERVAL -30 DAY)";
                            break;
                        case 11:
                        System.out.println("2.8 Patient Information");
                        query = "SELECT " +
                                "p.patient_id, " +
                                "COUNT(DISTINCT a.admission_id) AS total_admissions, " +
                                "AVG(DATEDIFF(a.discharge_date, a.admit_date)) AS avg_duration, " +
                                "DATEDIFF(MAX(a2.admit_date), MIN(a.admit_date)) AS longest_span, " +
                                "DATEDIFF(MIN(a2.admit_date), MAX(a.admit_date)) AS shortest_span, " +
                                "AVG(DATEDIFF(a2.admit_date, a.admit_date)) AS avg_span " +
                                "FROM " +
                                "patients p " +
                                "JOIN admissions a ON p.patient_id = a.patient_id " +
                                "LEFT JOIN admissions a2 ON p.patient_id = a2.patient_id AND a2.admit_date > a.admit_date " +
                                "GROUP BY " +
                                "p.patient_id";
                        break;
                        case 12:
                        System.out.println("3.1 Diagnosis and Treatment Information");
                        query = "SELECT diagnoses.diagnosis_id, diagnosis_name, COUNT(*) AS total_occurrences " +
                            "FROM diagnoses " +
                            "JOIN admissions ON diagnoses.diagnosis_id = admissions.diagnosis_id " +
                            "GROUP BY diagnoses.diagnosis_id, diagnosis_name " +
                            "ORDER BY total_occurrences DESC";
                            break;
                    case 13:
                        System.out.println("3.2 Diagnosis and Treatment Information");
                        query = "SELECT diagnoses.diagnosis_id, diagnosis_name, COUNT(*) AS total_occurrences " +
                        "FROM diagnoses " +
                        "JOIN admissions ON diagnoses.diagnosis_id = admissions.diagnosis_id " +
                        "WHERE discharge_date IS NULL " +
                        "GROUP BY diagnosis_id, diagnosis_name " +
                        "ORDER BY total_occurrences DESC";
                        break;
                    case 14:
                        System.out.println("3.3 Diagnosis and Treatment Information");
                        query = "SELECT treatment_id, treatment_name, COUNT(*) AS total_occurrences " +
                        "FROM treatments " +
                        "GROUP BY treatment_id, treatment_name " +
                        "ORDER BY total_occurrences DESC";
                        break;
                    case 15:
                        System.out.println("3.4 Diagnosis and Treatment Information");
                        query = "SELECT diagnoses.diagnosis_id, diagnosis_name, COUNT(*) AS correlation " +
                        "FROM diagnoses " +
                        "JOIN admissions ON diagnoses.diagnosis_id = admissions.diagnosis_id " +
                        "GROUP BY diagnosis_id, diagnosis_name " +
                        "ORDER BY correlation ASC";
                        break;
                    case 16:
                        System.out.println("3.5 Diagnosis and Treatment Information");
                        System.out.println("Note* first_name and last_name = employee first and last name");
                        query = "SELECT patients.patient_name, employees.first_name, employees.last_name " +
                        "FROM treatments " +
                        "JOIN admissions ON treatments.admission_id = admissions.admission_id " +
                        "JOIN patients ON admissions.patient_id = patients.patient_id " +
                        "JOIN employees ON treatments.doctor_id = employees.employee_id " +
                        "WHERE treatment_id = '1'";
                        break;
                    case 17:
                        System.out.println("4.1 Employee Information");
                        query = "SELECT first_name, last_name, job_title " +
                        "FROM employees " +
                        "ORDER BY last_name ASC, first_name ASC";
                        break;
                    case 18:
                        System.out.println("4.2 Employee Information");
                        System.out.println("Note* I don't have any people " +
                                "with greater than 4 admissions in my database.");
                        query = "SELECT patients.primary_doctor " +
                                "FROM patients " +
                                "JOIN admissions a ON patients.patient_id = patients.patient_id " +
                                "WHERE a.admit_date >= DATE_SUB(NOW(), INTERVAL 1 YEAR) " +
                                "GROUP BY patients.primary_doctor " +
                                "HAVING COUNT(DISTINCT a.patient_id) >= 4";
                        break;
                    case 19:
                        System.out.println("4.3 Employee Information");
                        query = "SELECT diagnoses.diagnosis_id, diagnosis_name, COUNT(*) AS total_occurrences " +
                        "FROM diagnoses " +
                        "JOIN admissions ON diagnoses.diagnosis_id = admissions.diagnosis_id " +
                        "WHERE admitting_doctor = '1' " +
                        "GROUP BY diagnosis_id, diagnosis_name " +
                        "ORDER BY total_occurrences DESC";
                        break;
                    case 20:
                        System.out.println("4.4 Employee Information");
                        query = "SELECT t.treatment_id, t.treatment_name, COUNT(*) AS total_occurrences " +
                        "FROM treatments t " +
                        "INNER JOIN admissions a ON t.admission_id = a.admission_id " +
                        "WHERE t.doctor_id = '1' " +
                        "GROUP BY t.treatment_id, t.treatment_name " +
                        "ORDER BY total_occurrences DESC";
                        break;
                    case 21:
                        System.out.println("4.5 Employee Information");
                        query = "SELECT * FROM employees " +
                        "WHERE employee_id IN (SELECT DISTINCT admitting_doctor FROM admissions) " +
                        "AND employee_id IN (SELECT DISTINCT doctor_id FROM treatments)";
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        continue;
                }

                ResultSet rs = stmt.executeQuery(query);

                ResultSetMetaData rsmd = rs.getMetaData();
                int numColumns = rsmd.getColumnCount();
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(rsmd.getColumnLabel(i) + "\t");
                }
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= numColumns; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }

                rs.close();
            }
        }
    }
}