/*
  Pratik - Öğrenci Bilgi Sistemi
  Course sınıfına derse ait sözlü notu kısmını girin ve ortalamaya etkisini her ders için ayrı ayrı belirtin. 
  Sözlü notların ıda ortalamaya etkileme yüzdesi ile dahil edin.

  Örnek : Fizik dersine ait sözlü notunun ortalamaya etkisi %20 ise sınav notunun etkisi %80'dir.

  Öğrenci sözlüden 90, sınavdan 60 almış ise, o dersin genel ortalamaya etkisi şu şekilde hesaplanır :

  Fizik Ortalaması : (90 * 0.20) + (60* 0.80);
*/


public class Student {
    String name,stuNo;
    int classes;
    Course mat;
    Course fizik;
    Course kimya;
    double avarage;
    boolean isPass;


    Student(String name, int classes, String stuNo, Course mat,Course fizik,Course kimya) {
        this.name = name;
        this.classes = classes;
        this.stuNo = stuNo;
        this.mat = mat;
        this.fizik = fizik;
        this.kimya = kimya;
        calcAvarage();
        this.isPass = false;
    }


    public void addBulkExamNote(int mat, int fizik, int kimya) {

        if (mat >= 0 && mat <= 100) {
            this.mat.noteExam = mat;                     //***********************MODIFIED
        }

        if (fizik >= 0 && fizik <= 100) {
            this.fizik.noteExam = fizik;                 //***********************MODIFIED
        }

        if (kimya >= 0 && kimya <= 100) {
            this.kimya.noteExam = kimya;                 //***********************MODIFIED
        }

    }
     
    public void addOralExamNote(int mat, int fizik, int kimya) {            //***********************ADDED
        if (mat >= 0 && mat <= 100) {                                       //***********************ADDED
            this.mat.noteOral = mat;                                        //***********************ADDED
        }                                                                   //***********************ADDED

        if (fizik >= 0 && fizik <= 100) {                                   //***********************ADDED
            this.fizik.noteOral = fizik;                                    //***********************ADDED
        }                                                                   //***********************ADDED

        if (kimya >= 0 && kimya <= 100) {                                   //***********************ADDED
            this.kimya.noteOral = kimya;                                    //***********************ADDED
        }
    }

    public void isPass() {
        if (this.mat.note == 0 || this.fizik.note == 0 || this.kimya.note == 0) {
            System.out.println("Notlar tam olarak girilmemiş");
        } else {
            this.isPass = isCheckPass();
            printNote();
            System.out.println("Ortalama : " + this.avarage);
            if (this.isPass) {
                System.out.println("Sınıfı Geçti. ");
            } else {
                System.out.println("Sınıfta Kaldı.");
            }
        }
    }

    public void calcAvarage() {
        this.fizik.note = (this.fizik.examFac * this.fizik.noteExam) + (this.fizik.oralFac * this.fizik.noteOral);          //***********************ADDED   
        this.kimya.note = (this.kimya.examFac * this.kimya.noteExam) + (this.kimya.oralFac * this.kimya.noteOral);          //***********************ADDED
        this.mat.note = (this.mat.examFmatac * this.mat.noteExam) + (this.mat.oralFac * this.mat.noteOral);                 //***********************ADDED
        this.avarage = (this.fizik.note + this.kimya.note + this.mat.note) / 3;
    }

    public boolean isCheckPass() {
        calcAvarage();
        return this.avarage > 55;
    }

    public void printNote(){
        System.out.println("=========================");
        System.out.println("Öğrenci : " + this.name);
        System.out.println("Matematik Sınav Notu : " + this.mat.noteExam);          //***********************MODIFIED
        System.out.println("Matematik Sözlü Notu : " + this.mat.noteOral);          //***********************ADDED
        System.out.println("Fizik Sınav Notu : " + this.fizik.noteExam);            //***********************MODIFIED
        System.out.println("Fizik Sözlü Notu : " + this.fizik.noteOral);            //***********************ADDED
        System.out.println("Kimya Sınav Notu : " + this.kimya.noteExam);            //***********************MODIFIED
        System.out.println("Kimya Sözlü Notu : " + this.kimya.noteOral);            //***********************ADDED
    }

}


public class Course {
    Teacher courseTeacher;
    String name;
    String code;
    String prefix;
    int noteExam;                    //***********************ADDED
    int noteOral;                    //***********************ADDED
    int note;                 
    double examFac;                  //***********************ADDED
    double oralFac;                  //***********************ADDED
 

    public Course(String name, String code, String prefix) {
        this.name = name;
        this.code = code;
        this.prefix = prefix;
        this.noteExam = 0;               //***********************ADDED
        this.noteOral = 0;               //***********************ADDED
        this.note = 0;           
    }

    public void addTeacher(Teacher t) {
        if (this.prefix.equals(t.branch)) {
            this.courseTeacher = t;
            System.out.println("İşlem başarılı");
        } else {
            System.out.println(t.name + " Akademisyeni bu dersi veremez.");
        }
    }

    public void printTeacher() {
        if (courseTeacher != null) {
            System.out.println(this.name + " dersinin Akademisyeni : " + courseTeacher.name);
        } else {
            System.out.println(this.name + " dersine Akademisyen atanmamıştır.");
        }
    }
}


public class Teacher {
    String name;
    String mpno;
    String branch;

    public Teacher(String name, String mpno, String branch) {
        this.name = name;
        this.mpno = mpno;
        this.branch = branch;
    }

}



public class Main {
    public static void main(String[] args) {

        Course mat = new Course("Matematik", "MAT101", "MAT");
        Course fizik = new Course("Fizik", "FZK101", "FZK");
        Course kimya = new Course("Kimya", "KMY101", "KMY");

        Teacher t1 = new Teacher("Mahmut Hoca", "90550000000", "MAT");
        Teacher t2 = new Teacher("Fatma Ayşe", "90550000001", "FZK");
        Teacher t3 = new Teacher("Ali Veli", "90550000002", "KMY");

        mat.addTeacher(t1);
        fizik.addTeacher(t2);
        kimya.addTeacher(t3);

        Student s1 = new Student("İnek Şaban", 4, "140144015", mat, fizik, kimya);
        s1.addBulkExamNote(50,20,40);
        s1.addOralExamNote(90, 75, 95);                             //***********************ADDED
        s1.isPass();

        Student s2 = new Student("Güdük Necmi", 4, "2211133", mat, fizik, kimya);
        s2.addBulkExamNote(100,50,40);
        s2.addOralExamNote(95, 100, 90);                           //***********************ADDED
        s2.isPass();

        Student s3 = new Student("Hayta İsmail", 4, "221121312", mat, fizik, kimya);
        s3.addBulkExamNote(50,20,40);
        s2.addOralExamNote(90, 85, 80);                            //***********************ADDED
        s3.isPass();

    }
}



