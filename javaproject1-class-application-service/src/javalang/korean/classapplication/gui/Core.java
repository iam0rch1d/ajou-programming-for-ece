package javalang.korean.classapplication.gui;

import java.util.ArrayList;
import javalang.korean.classapplication.*;

class Core {
    static final ArrayList<Student> studentArrayList = new ArrayList<>();
    static final ArrayList<Professor> professorArrayList = new ArrayList<>();
    static final ArrayList<Staff> staffArrayList = new ArrayList<>();
    static final ArrayList<Lecture> lectureArrayList = new ArrayList<>();

    public static void main(String[] args) {
        Initialization.initialize();

        new EntranceUi();
    }
}
