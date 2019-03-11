package com.ramersoft.pos.ui.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface BillsService {
   
	String generateBillsData(HttpServletRequest request, HttpSession session);
}
