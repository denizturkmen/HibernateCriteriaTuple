package com.denizturkmen.Client;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;


import com.denizturkmen.Entity.Employee;
import com.denizturkmen.Util.HibernateUtil;

public class TupleTest {

	public static void main(String[] args) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			Path<Object> employeeName =root.get("employeeName");
			Path<Object> email = root.get("email");
			Path<Object> salary = root.get("salary");
			
			criteriaQuery.multiselect(employeeName,email,salary);
			
			List<Tuple> tuples = session.createQuery(criteriaQuery).getResultList();
			System.out.println("---------------------------------------------------");
			for (Tuple tuple : tuples) {
				String employeeNames = (String) tuple.get(employeeName);
				String emails=(String) tuple.get(email);
				Double sal =(Double) tuple.get(salary);
				System.out.println(employeeNames+"\t"+emails+"\t"+sal);
				System.out.println("---------------------------------------------------");
			}
			
		} catch (HibernateException e) {
			
		}
		
		
	}

	
		
}

