package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.DoctorBean;
import com.rays.pro4.Bean.PatientBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Model.DoctorModel;
import com.rays.pro4.Model.PatientModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "PatientCtl", urlPatterns = {"/ctl/patient/"})
public class PatientCtl  extends BaseCtl{

	private static Logger log = Logger.getLogger(DoctorCtl.class);

	
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("validate started ... std ctl");
		log.debug("PatientCtl Method validate Started");
		boolean pass = true;

			if(DataValidator.isNull(request.getParameter("name"))) {
				request.setAttribute("name",PropertyReader.getValue("error.require", "Name"));
			pass = false;
			} else if (!DataValidator.isName(request.getParameter("name"))) {
				request.setAttribute("name", "Name contains alphabet only");
				pass = false;
			}
			if (DataValidator.isNull(request.getParameter("dateofvisit"))) {
				request.setAttribute("dateofvisit",PropertyReader.getValue("error.require", "Date of Visit"));
				pass = false;
			}
			
			if (DataValidator.isNull(request.getParameter("mobileNo"))) {
				request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
				pass = false;
			} else if (!DataValidator.isPhoneLength(request.getParameter("mobileNo"))) {
				request.setAttribute("mobileNo", "Mobile No must have 10 digits");
				pass = false;
			}
			if (DataValidator.isNull(request.getParameter("decease"))) {
				request.setAttribute("decease", PropertyReader.getValue("error.require", "decease"));
				pass = false;
			}
		
		// TODO Auto-generated method stub
			System.out.println("validate over ,.... Student ctl");
			log.debug("PatientCtl Method validate Ended");
			return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("PatientCtl Method populatebean Started");
		
		PatientBean bean = new PatientBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDateofvisit(DataUtility.getDate(request.getParameter("dateofvisit")));
		bean.setMobileno(DataUtility.getString(request.getParameter("mobileno")));
		bean.setDecease(DataUtility.getString(request.getParameter("decease")));
		populateDTO(bean, request);
		log.debug("Patientctl Method populatebean Ended");

		
		// TODO Auto-generated method stub
		return bean;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("PatientCtl Method doGet Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		
		DoctorModel model = new DoctorModel();
		if (id > 0 || op != null) {
			DoctorBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("DoctorCtl Method doGett Ended");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("PatientCtl Method DoPost Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		
		PatientModel model = new PatientModel();
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase("op")) {
			
			PatientBean bean = (PatientBean) populateBean(request);
			
			if(id>=0) {
				try {
					model.update(bean);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage(" Data Is Successfully  Updated", request);
			}
			else {
				try {
					long pk = model.add(bean);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage(" Data Is Successfully Added ", request);
			}
			ServletUtility.setBean(bean, request);
		}
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return null;
	}

}
