import java.util.Scanner;

public class Main {
    public static void signUpMenu(Session session){

        System.out.println("Are you a students or teacher?");
        Student student = new Student();
        Teacher teacher = new Teacher();

        Scanner scChoose = new Scanner(System.in);
        String choose = scChoose.nextLine();
        if(choose.equals("students")) {
            System.out.println("Input Name");
            Scanner scName = new Scanner(System.in);
            student.setName(scName.nextLine());
            System.out.println("Input Class");
            Scanner scClass = new Scanner(System.in);
            student.setKlass(scClass.nextLine());
            if(student.getKlass().length() <= 2) {
                session.signUpStudent(choose, student.getName(), student.getKlass());
                menuU();
            }else {
                System.out.println("Something went wrong.");
                signUpMenu(session);
            }
        }else if(choose.equals("teacher")) {
            System.out.println("Input Name");
            Scanner scName = new Scanner(System.in);
            teacher.setName(scName.nextLine());
            session.signUpTeacher(choose, teacher.getName());
            menuU();
        }
        else {
            System.out.println("Something went wrong.");
            signUpMenu(session);
        }
    }
    public static void signInMenu(Session session) {
        System.out.println("Are you a students or teacher?");
            Scanner scChoose = new Scanner(System.in);
            Student student = new Student();
            Teacher teacher = new Teacher();
            String choose = scChoose.nextLine();
            if (choose.equals("teacher")) {
                System.out.println("Input teacher login");
                Scanner scTName = new Scanner(System.in);
                teacher.setName(scTName.nextLine());

                System.out.println("Input Name");
                Scanner scName = new Scanner(System.in);
                student.setName(scName.nextLine());

                System.out.println("Input Subject");
                Scanner scNote = new Scanner(System.in);
                student.setSubject(scNote.nextLine());

                System.out.println("Input Mark");
                Scanner scMark = new Scanner(System.in);
                student.setMark(scMark.nextLine());

                session.addMark("students", teacher.getName(), student.getName(), student.getSubject(), student.getMark());
            }else if (choose.equals("students")) {
                System.out.println("Input Name");
                Scanner scName = new Scanner(System.in);
                String name = scName.nextLine();
                session.studentsViewMarks(name);
                menuU();
            }
            else {
                System.out.println("Something went wrong.");
                signInMenu(session);
            }
        }
    public static void menuU(){
        Session session = new Session();
        try {
            System.out.println("Sign up (1) or Sign in (2)");
            Scanner sc = new Scanner(System.in);
            int sign = sc.nextInt();
            if(sign == 1) {
                signUpMenu(session);
            }
            else if(sign == 2) {
                signInMenu(session);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            menuU();
        }
    }
    public static void main(String[] args)  {
        menuU();
    }
}