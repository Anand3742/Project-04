package com.rays.pro4.Exception;
/**
 * ApplicationException is propogated from Service classes when an business
 * logic exception occurered.
 * 
 * @author Anand Choudhary
 *
 */
public class ApplicationException extends Exception {

	public ApplicationException(String msg){
		super(msg);
	}
	
}
