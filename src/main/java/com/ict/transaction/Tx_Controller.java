package com.ict.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Tx_Controller {
	@Autowired
	private TxServiceImpl txServiceImpl;
	
	@RequestMapping("/transaction.do")
	public ModelAndView getTransactionForm() {
		return new ModelAndView("treansactionForm");
	}
	
	@RequestMapping("/result4.do")
	public ModelAndView getTreansactionOK(@ModelAttribute("txVO") TxVO txvo) {
		ModelAndView mv = new ModelAndView("result4");
		try {
		   int result =	txServiceImpl.getInsert(txvo);
		   mv.addObject("res", result);
		   return mv;
		} catch (Exception e) {
			return new ModelAndView("error");
		}
	}
}
