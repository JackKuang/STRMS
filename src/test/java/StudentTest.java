

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hurenjieee.entity.Student;
import com.hurenjieee.entity.Userr;
import com.hurenjieee.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class StudentTest {
	
	@Autowired
	LoginService loginService;
	
	public static void main(String[] args){
		
		Userr user = new Userr();
		user.setUserId(111);
		user.setUserName("zhang");
		user.setPassWord("123456");
		
		Configuration cfg = new Configuration();
		SessionFactory sf = cfg.configure().buildSessionFactory();
	   	
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		sf.close();		
	}
	
	@Test
	public void test(){
		Student s = new Student();
		s.setId(1143131);
		s.setName("s1");
		s.setAge(1);
		/*
		Configuration cfg = new Configuration();
		SessionFactory sf = cfg.configure().buildSessionFactory();
*/	   	
	/*	Session session = sessionFactory.openSession();
		Transaction t=session.beginTransaction();
		session.save(s);
		t.commit();
		session.close();
		sessionFactory.close();
		*/
	}
	
	@Test
	public void test2(){
		Userr userr = new Userr();
		userr.setUserId(111);
		userr.setUserName("aaa32123a");
		userr.setPassWord("bb321b");
		loginService.save(userr);
	}
}