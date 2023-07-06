package com.kh;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.BookController;
import com.kh.model.vo.Book;
import com.kh.model.vo.Member;
import com.kh.model.vo.Rent;

public class Application {

	private Scanner sc = new Scanner(System.in);
	private BookController bc = new BookController();
	
	public static void main(String[] args) {

		Application app = new Application();
		app.mainMenu();
		
	}
	
	public void mainMenu() {
		System.out.println("===== 도서 관리 프로그램 =====");
		
		boolean check = true;
		while(check) {
			System.out.println("1. 전체 책 조회");
			System.out.println("2. 책 등록");
			System.out.println("3. 책 삭제");
			System.out.println("4. 회원가입");
			System.out.println("5. 로그인");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 입력 : ");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				printBookAll();
				break;
			case 2:
				registerBook();
				break;
			case 3:
				sellBook();
				break;
			case 4:
				registerMember();
				break;
			case 5:
				login();
				break;
			case 9:
				check = false;
				System.out.println("프로그램 종료");
				break;
			}
			
		}
	}
	
	public void printBookAll() {
		ArrayList<Book> bookList = bc.printBookAll();
		for(Book book : bookList) {
			System.out.println(book);
		}
	}
	public void registerBook() {
		System.out.print("책 제목 : ");
		String title = sc.nextLine();
		System.out.print("책 저자 : ");
		String author = sc.nextLine();
		
		if(bc.registerBook(new Book(title, author))) {
			System.out.println("성공적으로 책을 등록했습니다.");
		} else {
			System.out.println("책을 등록하는데 실패했습니다.");
		}
	}
	public void sellBook() {
		printBookAll();
		System.out.print("삭제할 책 번호 선택 : ");
		int no = Integer.parseInt(sc.nextLine());
		
		if(bc.sellBook(no)) {
			System.out.println("성공적으로 책을 삭제했습니다.");
		} else {
			System.out.println("책을 삭제하는데 실패했습니다.");
		}
	}
	public void registerMember() {
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String password = sc.nextLine();
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		if(bc.registerMember(new Member(id, password, name))) {
			System.out.println("성공적으로 회원가입을 완료하였습니다.");
		} else {
			System.out.println("회원가입에 실패했습니다.");
		}
	}
	public void login() {
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String password = sc.nextLine();
		
		Member member = bc.login(id, password);
		if(member!=null) {
			System.out.println(member.getMemberName() + "님, 환영합니다!");
			memberMenu();
		} else {
			System.out.println("로그인에 실패했습니다.");
		}
	}
	public void memberMenu() {
		boolean check = true;
		while(check) {
			System.out.println("1. 책 대여");
			System.out.println("2. 내가 대여한 책 조회");
			System.out.println("3. 대여 취소");
			System.out.println("4. 로그아웃");
			System.out.println("5. 회원탈퇴");
			System.out.print("메뉴 번호 입력 : ");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				rentBook();
				break;
			case 2:
				printRentBook();
				break;
			case 3:
				deleteRent();
				break;
			case 4:
				check = false;
				break;
			case 5:
				deleteMember();
				check = false;
				break;
			}
		}
		
	}
	
	public void rentBook() {
		printBookAll();
		System.out.print("대여할 책 번호 선택 : ");
		int no = Integer.parseInt(sc.nextLine());
		if(bc.rentBook(no)) {
			System.out.println("성공적으로 책을 대여했습니다.");
		} else {
			System.out.println("책을 대여하는데 실패했습니다.");
		}
	}
	public void printRentBook() {
		ArrayList<Rent> rentList = bc.printRentBook();
 
		for(Rent rent : rentList) {
			LocalDate localDate = new java.sql.Date(rent.getRentDate().getTime()).toLocalDate();
			System.out.println(rent.getRentNo() + " / " + rent.getBook().getBkTitle() + " / " + rent.getBook().getBkAuthor()
								+ " / 대여 날짜 : " + rent.getRentDate() + " / 반납 기한 : " + localDate.plusDays(7));
		}
	}
	
	public void deleteRent() {
		printRentBook();
		System.out.print("취소할 책 번호 선택 : ");
		int no = Integer.parseInt(sc.nextLine());
		if(bc.deleteRent(no)) {
			System.out.println("성공적으로 대여를 취소했습니다.");
		} else {
			System.out.println("대여를 취소하는데 실패했습니다.");
		}
	}
	
	public void deleteMember() {
		if(bc.deleteMember()) {
			System.out.println("회원탈퇴 하였습니다 ㅠㅠ");
		} else {
			System.out.println("회원탈퇴하는데 실패했습니다.");
		}
	}
	
}