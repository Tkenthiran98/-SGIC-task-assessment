package com.sgic.java.util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeFilter {
    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/book_reader_xml";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        List<Employee> qualityEngineers = filterQualityEngineers("src\\main\\java\\com\\sgic\\java\\util\\Employee.xml");
        // Store the filtered quality engineers in a database table
        insertEmployees(qualityEngineers);
        // You can also print the filtered employees if needed
        for (Employee employee : qualityEngineers) {
            System.out.println(employee);
        }
    }

    public static List<Employee> filterQualityEngineers(String filename) {
        List<Employee> qualityEngineers = new ArrayList<>();
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("employee");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String position = element.getElementsByTagName("position").item(0).getTextContent();
                    if (position.equals("Quality Engineer")) {
                        String id = element.getElementsByTagName("id").item(0).getTextContent();
                        String name = element.getElementsByTagName("name").item(0).getTextContent();
                        String department = element.getElementsByTagName("department").item(0).getTextContent();
                        qualityEngineers.add(new Employee(Integer.parseInt(id), name, position, department));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qualityEngineers;
    }

    public static void insertEmployees(List<Employee> employees) {
        String query = "INSERT INTO employee (id, name, position, department) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (Employee employee : employees) {
                pstmt.setInt(1, employee.getId());
                pstmt.setString(2, employee.getName());
                pstmt.setString(3, employee.getPosition());
                pstmt.setString(4, employee.getDepartment());
                pstmt.executeUpdate();
            }
            System.out.println("Insertion successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Employee {
    private int id;
    private String name;
    private String position;
    private String department;

    public Employee(int id, String name, String position, String department) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
