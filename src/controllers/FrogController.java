package controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.Frog;
import data.FrogDAO;

@Controller
public class FrogController {
	//auto wire will have the bean injected by servlet xml
	//which will instantiate 
	@Autowired
	private FrogDAO frogDao;
	
  @RequestMapping("route.do")
  public ModelAndView processData(@RequestParam("data") String s) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("result1.jsp");
    mv.addObject("frog", frogDao.getFrogByName(s));
    return mv;
  }
  
  //testing adding a frog component to the controller
  @RequestMapping(path="NewFrog.do",
			method=RequestMethod.POST)
	public ModelAndView newFrog(Frog frog) {
	  frogDao.addFrog(frog);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result1.jsp");
		return mv;
	}
 
  
}