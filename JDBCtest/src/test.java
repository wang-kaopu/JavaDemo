import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
/*
        try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/test","root","meiyoumima123")) {
            try(Statement stmt = con.createStatement()){
                try(ResultSet rs = stmt.executeQuery("select name, id from students")){
                    while(rs.next()){
                        String name = rs.getString(1);
                        int id = rs.getInt(2);
                        System.out.print(name+' ');
                        System.out.println(id);
                    }
                }
            }
        }*/

        String JDBC_URL = "jdbc:mysql://localhost:3306/test";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "meiyoumima123";
        try(Connection conn = DriverManager.getConnection(
        JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
            try(PreparedStatement ps = conn.prepareStatement(
                "select id, name, score from students where gender = ?")){
                ps.setObject(1,"M");
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int score = rs.getInt("score");
                        System.out.println(id+"->"+name+"->"+score);
                    }
                }
            }
        }

//        try(Connection conn = DriverManager.getConnection(
//                JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
//            try(PreparedStatement ps = conn.prepareStatement(
//                    "insert into students (class_id, name, score, gender)",Statement.RETURN_GENERATED_KEYS)){
//                ps.setObject(1,2);
//                ps.setObject(2,"Faye");
//                ps.setObject(3,89);
//                ps.setObject(4,"F");
//
//
//                int n = ps.executeUpdate();

//                try(ResultSet rs = ps.getGeneratedKeys()){
//                    if(rs.next()){
//                        int i = rs.getInt(1);
//                    }
//                }

//                }

        try(Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
            try(PreparedStatement ps = connection.prepareStatement(
                    "delete from students where id = 9;"
            )){
                //ps.setObject(1,"小南");

                int n = ps.executeUpdate();
                System.out.println(n);
            }
        }

        ArrayList<student> students = new ArrayList<>();
        Collections.addAll(students,new student("Alice", 98, 2, "F"),
                new student("Bob", 97, 3, "M"));
        try(Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement("insert into students (name, class_id, gender, score) values (?, ?, ?, ?)")){
                for(student s : students){
                    ps.setString(1, s.name);
                    ps.setInt(2, s.class_id);
                    ps.setString(3, s.gender);
                    ps.setInt(4, s.score);
                    ps.addBatch();
                }
                int[] executed = ps.executeBatch();
                for (int i : executed) {
                    System.out.println(i);
                }
            }
        }
    }


        }


