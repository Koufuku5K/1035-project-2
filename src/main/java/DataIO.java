import java.util.Scanner;
public class DataIO {

    public void createRoom(){

        Scanner s = new Scanner(System.in);
        System.out.println("Please enter room number:");
        String room = s.nextLine();
        System.out.println("Please enter room type:");
        String type = s.nextLine();
        System.out.println("Please enter room capacity:");
        int capacity = s.nextInt();
        System.out.println("Please enter room socially distanced capacity:");
        int socialCapacity = s.nextInt();

        Room r = new Room(room,type,socialCapacity,capacity);
        r.setRoomNumber(room);
        r.setRoomType(type);
        r.setSocialDistanceCapacity(socialCapacity);
        r.setMaxCapacity(capacity);
    }

    public void createModule(){

        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the module ID:");
        String ID = s.nextLine();
        System.out.println("Please enter the module name:");
        String name = s.nextLine();
        System.out.println("Please enter the module credits:");
        int credits = s.nextInt();
        System.out.println("Please enter the weeks the module will last for:");
        int weeks = s.nextInt();

        Module m = new Module(ID,name,weeks,credits);
        m.setModuleID(ID);
        m.setModuleName(name);
        m.setWeeks(weeks);
        m.setCredits(credits);
    }

    public void createStaff(){

        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the staff members ID:");
        String ID = s.nextLine();
        System.out.println("Please enter the staff members first name:");
        String firstName = s.nextLine();
        System.out.println("Please enter the staff members second:");
        String secondName = s.nextLine();

        Staff S = new Staff(ID,firstName,secondName);
        S.setStaffID(ID);
        S.setStaffFirstName(firstName);
        S.setStaffSecondName(secondName);
    }

    public void createStudent(){

        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the students ID:");
        String ID = s.nextLine();
        System.out.println("Please enter the students first name:");
        String firstName = s.nextLine();
        System.out.println("Please enter the students second:");
        String lastName = s.nextLine();

        Students S = new Students(ID,firstName,lastName);
        S.setStudentID(ID);
        S.setStudentFirstName(firstName);
        S.setStudentLastName(lastName);
    }

    public static void main(String[] args) {
    }
}
