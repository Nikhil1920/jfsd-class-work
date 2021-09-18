package skill6;

import java.util.List;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class skill6 {
	public static Session getSession() throws HibernateException {
		String cfg = "hibernate.cfg.xml";
		SessionFactory sessionFactory = new Configuration().configure(cfg).buildSessionFactory();
		return sessionFactory.openSession();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		boolean exit = false;

		while (!exit) {
			System.out.println("1. Get employee details");
			System.out.println("2. Update employee salary");
			System.out.println("3. Delete employee with least salary");
			System.out.println("4. Exit the program");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Please enter employee id");
				int employeeId = sc.nextInt();
				get_employee_details(employeeId);
				break;

			case 2:
				System.out.println("Please enter employee id");
				int employId = sc.nextInt();
				update_employee_details(employId);
				break;

			case 4:
				exit = true;
				break;

			default:
				System.out.println("Please Try Again");
				break;
			}
		}
		sc.close();
	}

	public static void get_employee_details(int Empid) {
		try (Session session = getSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
			Root<Employee> employeeRoot = criteria.from(Employee.class);
			criteria.select(employeeRoot);

			criteria.where(builder.equal(employeeRoot.get("Empid"), Empid));
			List<Employee> employeeList = session.createQuery(criteria).getResultList();
			if (employeeList.isEmpty()) {
				System.out.println("No employee found");
			} else {
				for (Employee temp : employeeList) {
					System.out.println("Details of employee with empid " + Empid + " are");
					System.out.println("Employee name " + temp.getEname());
					System.out.println("Employee Department " + temp.getDepartment());
					System.out.println("Employee age " + temp.getAge());
					System.out.println("Employee Salary " + temp.getSalary());
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong");
		}
	}

	public static void update_employee_details(int Empid) {
		try (Session session = getSession()) {
			List<Double> average = session.createQuery("select avg(salary) from Employee").getResultList();
			Transaction tx = session.beginTransaction();
			Integer avg = average.get(0).intValue();
			int updatedEntities = session.createQuery("update Employee e set e.salary=:n where e.Empid=:i")
					.setParameter("n", avg).setParameter("i", Empid).executeUpdate();

			System.out.println(updatedEntities + " Entities updated succesfully");
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Something went wrong");
		}
	}

}
