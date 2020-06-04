package java.korean.librarymanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import java.korean.librarymanagement.collection.*;
import java.korean.librarymanagement.person.*;

public class Library {
	private ArrayList<Person> people;
	private ArrayList<Collection> collections;
	
	public Library() {
		people = new ArrayList<Person>();
		collections = new ArrayList<Collection>();
	}
	
	public Library(String peopleDataDir, String collectionsDataDir) {
		this();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(peopleDataDir));
	        while(true) {


	        	
	        	
	        	
	        	
	        	
	        	
	        }
	        br.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(collectionsDataDir));
	        while(true) {


	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        }
	        br.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public void CheckOutCollection(String title, String name){

	}

	public void CheckOutCollection(String title, int id) {

	}
	
	public void ReturnCollection(String title, String name) {

	}
	
	public void ReturnCollection(String title, int id) {

	}
	
	public void AddPerson(int id, String name, String status) {

	}
	
	public void AddContent(String title, String author, String status) {

	}
	
	public void PrintCollection() {

	}
	
	public void PrintPeople() {

	}
	
	public void SaveLibrary() {

}
