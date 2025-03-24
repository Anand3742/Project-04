
package com.rays.proj4.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.PatientBean;
import com.rays.pro4.Bean.ProductBean;
import com.rays.pro4.Bean.ProductBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.PatientModel;
import com.rays.pro4.Model.ProductModel;


public class PatientTest {

	public static void main(String[] args) throws Exception {
	// testAdd();
	// testDelete();
	// testFindByPk();
		//testUpdate();
  //  testSearch();
    getList();
	}

	

	private static void getList() throws Exception {
		try {
			PatientBean bean = new PatientBean();
			PatientModel model = new PatientModel();
			List list = new ArrayList();
			list = model.list();
			if (list.size() < 0) {
				System.out.println("Test list fail ");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (PatientBean) it.next();
				System.out.println(bean.getId() + " ");
				System.out.print(bean.getName() + " ") ;
				System.out.print(bean.getDateofvisit() + " ");
				System.out.print(bean.getMobileno() + " ");
				System.out.print(bean.getDecease() + " ");

			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	
	private static void testSearch() throws Exception {
		try {
			PatientBean bean = new PatientBean();
			PatientModel model = new PatientModel();
			List list = new ArrayList();
			
			list = model.search(bean, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (PatientBean) it.next();
				System.out.println(bean.getId() + "    ");
				System.out.println(bean.getName() + "    ");
				System.out.println(bean.getDateofvisit() + "    ");
				System.out.println(bean.getMobileno() + "    ");
				System.out.println(bean.getDecease() + "    ");

			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testUpdate() throws Exception {
		try {
			PatientBean bean = new PatientBean();
			PatientModel model = new PatientModel();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
			bean = model.findByPK(2L);
			bean.setName("Anand Sharma");
			bean.setDateofvisit(sdf.parse("05-07-2023"));
			bean.setMobileno("9898989898");;
			bean.setDecease("heart");
			model.update(bean);

			System.out.println("test update succ");
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDateofvisit());
			System.out.println(bean.getMobileno());
			System.out.println(bean.getDecease());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testFindByPk() throws Exception {
		try {
			PatientBean bean = new PatientBean();
			 long pk = 2L; 
			PatientModel model = new PatientModel();
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test find by pk fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDateofvisit());
			System.out.println(bean.getMobileno());
			System.out.println(bean.getDecease());


		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	

	public static void testDelete() throws Exception {
		PatientBean bean = new PatientBean();
		bean.setId(1L);
		PatientModel model = new PatientModel();
		model.delete(bean);

	}

	public static void testAdd() {
		try {
			PatientBean bean = new PatientBean();

			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			bean.setId(1);
			bean.setName("niraj chopra");
			bean.setDateofvisit(sdf.parse("01-10-2024"));
			bean.setMobileno("9989998899");
			bean.setDecease("heart");
			
			PatientModel model = new PatientModel();

			long pk = model.add(bean);
			PatientBean addedbean = model.findByPK(pk);
			System.out.println("Test add succ");

			if (addedbean == null) {
				System.out.println("Test add fail");
			}

			System.out.println("record insert");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
