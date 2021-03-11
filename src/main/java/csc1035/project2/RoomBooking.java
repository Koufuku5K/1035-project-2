package csc1035.project2;

import java.util.Scanner;

public class RoomBooking {
    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        boolean flag = true;

        while (flag) {
            System.out.println("-------------------------------\n" +
                               "| MENU:                       |\n" +
                               "| 0 - View All Rooms          |\n" +
                               "| 1 - Book A Room             |\n" +
                               "| 2 - Cancel Booking          |\n" +
                               "| 3 - View Room Timetable     |\n" +
                               "| 4 - Update A Room           |\n" +
                               "| 5 - Exit                    |\n" +
                               "-------------------------------");
            System.out.println("Enter option (0-5):");
            String option = getInput();

            switch (option) {
                case "0" -> ;
                case "1" -> ;
                case "2" -> ;
                case "3" -> ;
                case "4" -> updateRoom();
                case "5" -> flag = false;
                default -> System.out.println("Please enter a menu option"););
            }
        }
    }

    private void updateRoom() {
            System.out.println("Please choose a Room to update");
            roomList();
            System.out.println("Please enter option");
            String room = s.nextLine();



    }



}

