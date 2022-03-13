package BackEnd;
import java.sql.*;

public class Patient {
    Statement stmt;

    public Patient(Statement statement){
        this.stmt = statement;
    }
    
    public boolean addPatient(String firstName, String lastName, String sex, String address, String email, String dob, int temperature, String vac_status, String vac_date_taken, String covid_test_taken, String covid_test_result, String appointment_date, String phone, String date){
        int id = 0;
        try{
            String query = "INSERT INTO patients (first_name, last_name, sex, address, email, dob, phone_number) VALUES ('" + firstName +"', '" + lastName +"', '" + sex +"', '" + address +"', '"+ email +"', '" + dob +"', '"+phone+"');";
            int x = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                id = rs.getInt(1);
            }

            String query2 = "INSERT INTO medicaldetails (client_ID, temperature, date, vaccination_status, vac_date_taken, covid_test_taken, covid_test_result, appointment_date) values ("+ id + ", " + temperature + ", '"+date+"', '"+vac_status+"', '" + vac_date_taken + "', '" + covid_test_taken + "', '" + covid_test_result + "', '" + appointment_date + "');";
            
            stmt.executeUpdate(query2);
            
            if(x > 0){
                //Success
                return true;
            }
            
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        return false;
    }

    public void searchPatient(String name){
        String firstName, lastName, phone, vac_status, date, test_taken; 
        int id = 0;

        try{
            String query = "SELECT * from patients where first_name='"+name+"';";
            ResultSet rs;
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                id = rs.getInt("client_ID");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                phone = rs.getString("phone_number");

                System.out.println(firstName + " " + lastName + " " + phone);
            }

            String query2 = "SELECT * from medicaldetails where client_ID="+id+";";
            rs = stmt.executeQuery(query2);

            while(rs.next()){
                vac_status = rs.getString("vaccination_status");
                date = rs.getString("date");
                test_taken = rs.getString("covid_test_taken");

                System.out.println(vac_status+ " " + date + " " + test_taken);
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public int patientId(String firstName){
        String query = "SELECT * FROM patients where first_name= '" + firstName + "';";
        try{
            ResultSet rs = stmt.executeQuery(query);

            if(!rs.next()){
                return -1;
            } else {
                //Returns ID 
                return rs.getInt(1);
            }
            

        } catch(Exception e){
            System.out.println(e);
        }

        return -1;
    }

    public boolean modifyPatient(int id, int temperature, String vac_status, String vac_date_taken, String covid_test_taken, String covid_test_result, String booking_date, String date){
        String query = "INSERT INTO medicaldetails values("+id+", "+temperature+", '"+date+"', '"+vac_status+"', '"+vac_date_taken+"', '"+covid_test_taken+"', '"+covid_test_result+"', '"+booking_date+"');";
        try{
            int x = stmt.executeUpdate(query);
            if(x>0){
                return true;
            } else {
                return false;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return false;
    }

    public boolean deletePatientData(int id){
        String query = "DELETE FROM patients where client_ID="+id+";";

        try{
            int x = stmt.executeUpdate(query);

            if(x < 1){
                return false;
            } else{
                return true;
            }

        } catch (Exception e){
            System.out.println(e);
        }

        return false;
    }
}
