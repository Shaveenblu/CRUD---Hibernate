package dao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import model.Student;
import util.HibernateUtil;

public class StudentDao implements IStudentDao{
	@Override
	 public void saveStudent(Student student) {
	 Transaction transaction = null;
	 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	 // start the transaction
	 transaction = session.beginTransaction();
	 // save student object
	 session.persist(student);
	 // commit the transaction
	 transaction.commit();
	 } catch (Exception e) {
	 if (transaction != null) {
	 transaction.rollback();
	 }
	 }
	 }
	
	 @Override
	 public void updateStudent(Student student) {
		 Transaction transaction = null;
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		 // start the transaction
		 transaction = session.beginTransaction();
		 // update student object
		 session.merge(student);
		 // commit the transaction
		 transaction.commit();
		 } catch (Exception e) {
		 if (transaction != null) {
		 transaction.rollback();
		 }
		 }
		 }
	 
		 @Override
		 public Student getStudentById(long id) {
		 Transaction transaction = null;
		 Student student = null;
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		 // start the transaction
		 transaction = session.beginTransaction();
		 // get student object
		 student = session.get(Student.class, id);
		 
		 // commit the transaction
		 transaction.commit();
		 } catch (Exception e) {
		 if (transaction != null) {
		 transaction.rollback();
		 }
		 }
		 return student;
		 }

		 @Override
		 public List < Student > getAllStudents() {
		 Transaction transaction = null;
		 List < Student > students = null;
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		 // start the transaction
		 transaction = session.beginTransaction();
		 // get students
		 JpaCriteriaQuery<Student> cq = session.getCriteriaBuilder().createQuery(Student.class);
		cq.from(Student.class);
		students = session.createQuery(cq).getResultList();
		 //student = session.load(Student.class, id);
		 // commit the transaction
		 transaction.commit();
		 } catch (Exception e) {
		 if (transaction != null) {
		 transaction.rollback();
		 }
		 }
		 return students;
		 }
		 
		 @Override
		 public void deleteStudent(long id) {
		 Transaction transaction = null;
		 Student student = null;
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		 // start the transaction
		 transaction = session.beginTransaction();
		 student = session.get(Student.class, id);
		 // remove student object
		 session.remove(student);
		 
		 // commit the transaction
		 transaction.commit();
		 } catch (Exception e) {
		 if (transaction != null) {
		 transaction.rollback();
		 }
		 }
		 }

	 
}
