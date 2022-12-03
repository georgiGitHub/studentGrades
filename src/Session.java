
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


public class Session {

    private void quickSort(Vector<String> subjects, Vector<String> mark) {
        for(int i = 1;i < mark.toArray().length;i++) {
            for(int j = i+1;j < mark.toArray().length;j++) {
                int a = Integer.parseInt(mark.get(i)), b = Integer.parseInt(mark.get(j));
                if( a < b) {
                    String save  = mark.get(i);
                    mark.set(i, mark.get(j));
                    mark.set(j,save);
                    save = subjects.get(i);
                    subjects.set(i, subjects.get(j));
                    subjects.set(j, save);
                }
            }
        }
    }
    private String readFile(String path) {
        String ans = null;
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                ans = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return ans;
    }
    private void createData (String path) {
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private void writeData(String data,String path) {
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(data);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void signUpStudent(String choose,String name,String Class) {
        String path = choose+"/"+name+".txt";
        createData(path);
        writeData("Name:"+name+" "+" Class:"+Class+" Subject:"+" Mark:",path);
    }

    public void signUpTeacher(String choose,String name) {
        String data;
        String path = choose+"s.txt";
        data = readFile(path);
        data = data+" "+name;
        writeData(data,path);
    }
    public void addMark(String tLogin,String choose,String name,String subject,String mark) {
        String dat = readFile("teachers.txt");
        String[] log = dat.split(dat);
        for(int i=0 ;i<log.length;i++) {
            if(log[i].equals(tLogin)){
                String path = choose+"/"+name+".txt";
                String data = readFile(path);

                System.out.println(data);
                String newData;
                newData = data.replace("Subject:","Subject: "+subject + " ");
                data = newData;
                newData = data.replace("Mark:", "Mark: "+mark+" ");

                writeData(newData,path);
            }
        }
        System.out.println("Error");
    }

    public void studentsViewMarks(String name) {
        String data = readFile("students"+"/"+name+".txt");
        String[] words = data.split(" ");
        Vector<String> subjects = new Vector<>();
        Vector<String> mark = new Vector<>();

        boolean unlock = false;
        for (String word : words) {
            if (word.equals("Subject:")) {
                unlock = true;
            }
            if (unlock) {
                subjects.add(word);
            }
        }
        unlock = false;
        for (String word : words) {
            if (word.equals("Mark:")) {
                unlock = true;
            }
            if (unlock) {
                if (!word.isEmpty()) {
                    mark.add(word);
                    System.out.println(word);
                }
            }
        }
        for(int i=0 ; i< mark.size();i++) {
            System.out.println(subjects.get(i)+" ");
            System.out.println(mark.get(i)+" ");
        }
        System.out.println("Want to sort? (y/n)");
        Scanner scSorted = new Scanner(System.in);
        String sorted = scSorted.nextLine();
        if(sorted.equals("y")) {
            quickSort(subjects,mark);
            for(int i=0 ; i< mark.size();i++) {
                System.out.println(subjects.get(i)+" ");
                System.out.println(mark.get(i)+" ");
            }
        }else if(sorted.equals("n")) {
            System.out.println("nnn");
        }
    }

}
