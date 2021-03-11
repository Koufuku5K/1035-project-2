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

    }

    public void createModule(){

        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the module ID:");
        String ID = s.nextLine();
        System.out.println("Please enter the module name:");
        String name = s.nextLine();
        System.out.println("Please enter the module credits:");
        s.nextLine();
        int credits = s.nextInt();
        System.out.println("Please enter the weeks the module will last for:");
        s.nextLine();
        int weeks = s.nextInt();
        System.out.println("Please enter the number of lectures per week:");
        s.nextLine();
        int numLectures = s.nextInt();
        System.out.println("Please enter the length of lectures:");
        s.nextLine();
        double lectureLength = s.nextDouble();
        System.out.println("Please enter the number of practicals per week:");
        s.nextLine();
        int numPracticals = s.nextInt();
        System.out.println("Please enter the length of practicals:");
        s.nextLine();
        double practicalLength = s.nextDouble();
        System.out.println("Please enter the number of students enrolled on this module");
        s.nextLine();
        int numEnrolled = s.nextInt();

        Module m = new Module(ID,name,weeks,credits,numLectures,lectureLength,numPracticals,practicalLength,numEnrolled);

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

    }

    public static void main(String[] args) {
    }
}
