package com.rays.pro4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.DoctorBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.DoctorModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

//TODO: Auto-generated Javadoc
/**
 * Doctor functionality Controller. Performs operation for add, update, delete
 * and get Doctor
 * 
 * @author Anand choudhary
 */
@WebServlet(name = "DoctorCtl", urlPatterns = { "/ctl/DoctorCtl" })
public class DoctorCtl extends BaseCtl {

	/** The log. */
	private static Logger log = Logger.getLogger(DoctorCtl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.ors.controller.BaseCtl#preload(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		DoctorModel model = new DoctorModel();
		try {
			List l = model.list();
			request.setAttribute("expertiseList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.ors.controller.BaseCtl#validate(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("validate started ... std ctl");
		log.debug("DoctorCtl Method validate Started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Name contains alphabet only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isAge(request.getParameter("dob"))) {
			request.setAttribute("dob", "Age must be Greater then 18 year ");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "Mobile No. must be 10 Digit and No. Series start with 6-9");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("expertise"))) {
			request.setAttribute("expertise", PropertyReader.getValue("error.require", "Expertise"));
			pass = false;
		}
		System.out.println("validate over ,.... Student ctl");
		log.debug("DoctorCtl Method validate Ended");
		return pass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.ors.controller.BaseCtl#populateBean(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("DoctorCtl Method populatebean Started");

		DoctorBean bean = new DoctorBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		System.out.println("dob" + bean.getDob());
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
		bean.setExpertise(DataUtility.getString(request.getParameter("expertise")));
		populateDTO(bean, request);
		log.debug("DoctorCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * Contains Display logics.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("DoctorCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model

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

	/**
	 * Contains Submit logics.
	 *
	 * @param request  the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("DoctorCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));
		// get model

		DoctorModel model = new DoctorModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			DoctorBean bean = (DoctorBean) populateBean(request);
			try {
				if (id > 0) {
					model.Update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(" Data is successfully Updated", request);
				} else {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(" Data is successfully Added", request);
					// bean.setId(pk);
				}
				ServletUtility.setBean(bean, request);
				// ServletUtility.setSuccessMessage(" Student is successfully Added",request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Mobile No. already exists", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.DOCTOR_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.DOCTOR_LIST_CTL, request, response);
			return;
		}
		/*
		 * else if (OP_DELETE.equalsIgnoreCase(op)) {
		 * 
		 * DoctorBean bean = (DoctorBean) populateBean(request); try {
		 * model.delete(bean); ServletUtility.redirect(ORSView.STUDENT_CTL, request,
		 * response); return;
		 * 
		 * } catch (ApplicationException e) { log.error(e);
		 * ServletUtility.handleException(e, request, response); return; } }
		 */ ServletUtility.forward(getView(), request, response);

		log.debug("DoctorCtl Method doPost Ended");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.ors.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.DOCTOR_VIEW;
	}

}
