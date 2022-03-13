package UI;
import java.util.*;
import java.sql.*;
import BackEnd.Employee;
import BackEnd.Patient;

public class Screens {
    Statement stmt;
    Scanner scan = new Scanner(System.in);

    public Screens(Statement statement){
        this.stmt = statement;
    }
    
    public void login(Employee auth){
        String userName, password;
        boolean loggedin = false;

        while(!loggedin){
            System.out.print("Enter your userName: ");
            userName = scan.next();

            System.out.print("Enter your Password: ");
            password = scan.next();

            loggedin = auth.login(userName, password);
        }
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        menu();
    }

    public void menu(){
        int opt;
        do{
            System.out.println("Select an Option:");
            System.out.println("1. Register New Patient");
            System.out.println("2. Search for Patient");
            System.out.println("3. Update Patient's data");
            System.out.println("4. Delete Patient's data");
            System.out.println("5. Send Email");
            System.out.println("6. Add new User");
            System.out.println("7. View covid cases");
            System.out.println("0. Logout");
            System.out.println("NB. Never give up");
            System.out.print("Choice => ");
            opt = scan.nextInt();
            System.out.print("\033[H\033[2J");  
            System.out.flush();  

            switch(opt){
                case 1: registerPatientScreen(new Patient(this.stmt));
                        break;
                
                case 2: searchPatient(new Patient(this.stmt));
                        break;

                case 3: updatePatientData(new Patient(this.stmt));
                        break;
                
                case 4: deletePatientData(new Patient(this.stmt));
                        break;
                
                case 5: sendEmailScreen(new Employee(this.stmt));
                        break;

                case 6: addNewUserScreen(new Employee(this.stmt));
                        break;
                
                case 7: viewCovidCasesScreen(new Employee(this.stmt));
                        break;
                    
                case 0: try{
                            scan.close();
                            stmt.close();
                            System.exit(0);
                        } catch(Exception e){
                            System.out.println(e);
                        }
                        break;

                default: System.out.println("Not an option");
                        break;
            }
        }while(opt != 0);
        
    }

    public void registerPatientScreen(Patient person){
        String firstName, lastName, sex, address, email, dob;
        int temperature;
        String vac_status, vac_date_taken, covid_test_taken, covid_test_result;
        String booking_date, phone, date;

        scan.skip("\r\n|[\n\r\u2028\u2029\u0085]");
        System.out.print("Enter First Name: ");
        firstName = scan.nextLine();

        System.out.print("Enter Last Name: ");
        lastName = scan.nextLine();

        System.out.print("Enter Sex(Female/Male): ");
        sex = scan.next();
        scan.skip("\r\n|[\n\r\u2028\u2029\u0085]");

        System.out.print("Enter address: ");
        address = scan.nextLine();

        System.out.print("Enter Phone: ");
        phone = scan.nextLine();
        scan.skip("\r\n|[\n\r\u2028\u2029\u0085]");

        System.out.print("Enter email: ");
        email = scan.next();
        scan.skip("\r\n|[\n\r\u2028\u2029\u0085]");

        System.out.print("Enter date of birth: ");
        dob = scan.nextLine();
        
        System.out.print("Enter temperature: ");
        temperature = scan.nextInt();
        scan.skip("\r\n|[\n\r\u2028\u2029\u0085]");

        System.out.print("Enter vaccination status: ");
        vac_status = scan.nextLine();

        System.out.print("Enter date vaccine was taken: ");
        vac_date_taken = scan.nextLine();

        System.out.print("Enter date covid test was taken: ");
        covid_test_taken = scan.nextLine();

        System.out.print("Enter covid test result: ");        
        covid_test_result = scan.nextLine();

        System.out.print("Enter Appointment date: ");
        booking_date = scan.nextLine();

        System.out.print("Enter Current date: ");
        date = scan.nextLine();

        if(person.addPatient(firstName, lastName, sex, address, email, dob, temperature,vac_status, vac_date_taken, covid_test_taken, covid_test_result, booking_date, phone, date)){
            System.out.print("Saved");
        } else {
            System.out.print("Error Occured");
        }
        scan.next();
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public void sendEmailScreen(Employee user){
        String email,subject, message;

        scan.skip("\r\n|[\n\r\u2028\u2029\u0085]");
        System.out.println("Enter receipient Email: ");
        email = scan.nextLine();

        System.out.println("Enter Subject for Email: ");
        subject = scan.nextLine();

        System.out.println("Enter message for Email: ");
        message = scan.nextLine();

        try{
            user.sendEmail(email, subject, message);
            System.out.println("Message sent!!");
        } catch(Exception e){
            System.out.print(e);
        }
        scan.next();
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public void addNewUserScreen(Employee user){
        String username, password;

        System.out.print("Enter username: ");
        username = scan.nextLine();

        System.out.print("Enter password: ");
        password = scan.nextLine();

        if(user.addUser(username, password)){
            System.out.println("User Added");
        }
        else{
            System.out.println("Get to debugging bro");
        }
        scan.next();
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public void viewCovidCasesScreen(Employee user){
        String country;

        System.out.print("Enter the country: ");
        country = scan.next();

        user.covidCases(country);
        scan.next();
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public void searchPatient(Patient patient){
        String firstName;

        System.out.print("Enter First name: ");
        firstName = scan.next();

        patient.searchPatient(firstName);
        scan.next();
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public void updatePatientData(Patient patient){
        String firstName = "";
        int temperature;
        String vac_status, vac_date_taken, covid_test_taken, covid_test_result;
        String booking_date, date;
        int id;

        System.out.print("Enter the patient first Name: ");
        firstName = scan.next();

        id = patient.patientId(firstName);

        if(id < 1)
        {
            System.out.println("No user Found");
            scan.next();
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
        }
        else {
            System.out.print("Enter temperature: ");
            temperature = scan.nextInt();
            scan.skip("\r\n|[\n\r\u2028\u2029\u0085]");

            System.out.print("Enter vaccination status: ");
            vac_status = scan.nextLine();

            System.out.print("Enter date vaccine was taken: ");
            vac_date_taken = scan.nextLine();

            System.out.print("Enter date covid test was taken: ");
            covid_test_taken = scan.nextLine();

            System.out.print("Enter covid test result: ");        
            covid_test_result = scan.nextLine();

            System.out.print("Enter Appointment date: ");
            booking_date = scan.nextLine();

            System.out.print("Enter Current date: ");
            date = scan.nextLine();

            if(patient.modifyPatient(id, temperature, vac_status, vac_date_taken, covid_test_taken, covid_test_result, booking_date, date)){
                System.out.println("Modify successful");
            } else{
                System.out.println("Bro, start debugging");
            }
            scan.next();
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
        }
    }

    public void deletePatientData(Patient patient){
        String firstName, opt;

        System.out.print("Enter the patient first Name: ");
        firstName = scan.next();

        int id = patient.patientId(firstName);

        if(id < 1){
            System.out.println("No user found");
            scan.next();
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
        } else {
            patient.searchPatient(firstName);
            System.out.print("\nDo you want to delete this patient's Data(Y/N): ");
            opt = scan.next();

            if(opt.equals("Y")){
                if(patient.deletePatientData(id)){
                    System.out.println("Delete Complete");
                } else{
                    System.out.println("Delete not complete");
                }
            } else {
                System.out.print("\033[H\033[2J");  
                System.out.flush();  
                menu();
            }
        }
        scan.next();
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
