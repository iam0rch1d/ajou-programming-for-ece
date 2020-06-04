package java.korean.librarymanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import java.korean.librarymanagement.collection.*;
import java.korean.librarymanagement.person.*;

public class Core {
	public static void main(String[] args) throws Exception{
		Library library = new Library("People.txt", "Collections.txt");
		Scanner scanner = new Scanner(System.in);
		
		int selectNum = 0;
		boolean loop = true;

		while(loop)
		{
			System.out.println("---------------- 아주대학교 도서관 관리 페이지입니다 --------------");
			System.out.println("1.회원 등록");
			System.out.println("2.회원 목록 출력");
			System.out.println("3.자료 등록");
			System.out.println("4.자료 목록 출력");
			System.out.println("5.도서관 정보 저장");
			System.out.println("6.자료 대출");
			System.out.println("7.자료 반납");
			System.out.println("8.종료");
			System.out.println("--------------------------------------------------------");
			System.out.print("진행하고자 하는 행동을 선택해 주세요 : ");
			selectNum = Integer.parseInt(scanner.nextLine());
			switch(selectNum) {
			case 1:
			{
				System.out.println("회원 고유번호를 입력해주세요 : ");
				int id = Integer.parseInt(scanner.nextLine());
				System.out.println("회원 이름을 입력해주세요 : ");
				String name = scanner.nextLine();
				System.out.println("회원의 직위를 입력해주세요 (Student/Professor)");
				String status = scanner.nextLine();
				System.out.println(id + " " + name + " " + status);
				library.AddPerson(id, name, status);
				break;
			}
			case 2:
				library.PrintPeople();
				break;
			case 3:
			{
				System.out.println("자료의 제목을 입력해주세요 : ");
				String title = scanner.nextLine();
				System.out.println("자료의 저자를 입력해주세요 : ");
				String author = scanner.nextLine();
				System.out.println("자료의 종류를 입력해주세요(Book/ClassMaterial) : ");
				String status = scanner.nextLine();
				library.AddContent(title, author, status);
				break;
			}
			case 4:
				library.PrintCollection();
				break;
			case 5:
				library.SaveLibrary();
				break;
			case 6:
			{
				System.out.println("이름으로 대출할 경우 1, 고유번호로 대출할 경우 2를 입력하세요 : ");
				int selectNum2 = Integer.parseInt(scanner.nextLine());
				System.out.println("대출할 자료의 제목을 입력하세요 : ");
				String title = scanner.nextLine();
				switch(selectNum2)
				{
				case 1:
					System.out.println("대출할 사용자의 이름을 입력하세요 : ");
					String name = scanner.nextLine();
					library.CheckOutCollection(title, name);
					break;
				case 2:
					System.out.println("대출할 사용자의 고유번호를 입력하세요 : ");
					int id = Integer.parseInt(scanner.nextLine());
					library.CheckOutCollection(title, id);
					break;
				}
				break;
			}				
			case 7:
			{
				System.out.println("이름으로 반납할 경우 1, 고유번호로 반납할 경우 2를 입력하세요 : ");
				int selectNum2 = Integer.parseInt(scanner.nextLine());
				System.out.println("반납할 자료의 제목을 입력하세요 : ");
				String title = scanner.nextLine();
				switch(selectNum2)
				{
				case 1:
					System.out.println("반납할 사용자의 이름을 입력하세요 : ");
					String name = scanner.nextLine();
					library.ReturnCollection(title, name);
					break;
				case 2:
					System.out.println("반납할 사용자의 고유번호를 입력하세요 : ");
					int id = Integer.parseInt(scanner.nextLine());
					library.ReturnCollection(title, id);
					break;
				}
				break;
			}				
			case 8:
				loop = false;
				break;
			default:
				break;
			}
		}
	}
}
