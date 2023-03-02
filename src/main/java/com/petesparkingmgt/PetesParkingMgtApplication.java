package com.petesparkingmgt;

import com.petesparkingmgt.objects.users.Admin;
import com.petesparkingmgt.objects.users.Student;
import com.petesparkingmgt.objects.users.User;
import com.petesparkingmgt.objects.users.UserType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class PetesParkingMgtApplication {


	/**
	 * Static list containing all active users of the app, easy access for updating points and history.
	 *
	 * Below are implementations for student and admin lists, but all are stored as "User"
	 */
	private static List<User> userList;
	public static void main(String[] args) {
		SpringApplication.run(PetesParkingMgtApplication.class, args);
	}


	/*
	Loads an user into memory
	 */
	public static void loadUser(User user) {
		userList.add(user);
	}

	/**
	 * @return list of users
	 */
	public static List<User> getUserList() {
		return userList;
	}


	/**
	 * @return list of students
	 */
	public static List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		for (User i : userList) {
			if (i instanceof Student) {
				students.add((Student) i);
			}
		}
		return students;
	}

	/**
	 *
	 * @return list of admins
	 */
		public static List<Admin> getAdmins() {
		List<Admin> admins = new ArrayList<>();
		for (User i : userList) {
			if (i instanceof Admin) {
				admins.add((Admin) i);
			}
		}
		return admins;
	}

}
