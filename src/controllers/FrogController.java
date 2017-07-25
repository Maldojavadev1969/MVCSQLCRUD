package controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//import com.skilldistillery.film.data.Film;

import data.Frog;
import data.FrogDAO;

@Controller
public class FrogController {
	//auto wire will have the bean injected by servlet xml
	//which will instantiate 
	@Autowired
	private FrogDAO frogDao;
	
	//*********************************************************
	//working on adding this to the controller
	// so i can find a frog by id via the database
	@RequestMapping(path = "searchId.do", params = "frogId")
	public ModelAndView getFrogById(@RequestParam(name = "frogId") Integer frogId)
	{
	  String viewName = "result1.jsp";
	  ModelAndView mv = new ModelAndView(viewName);
	  Frog frog = frogDao.getFrogById(frogId);
	  mv.addObject("frog", frog);
	  return mv;
	}
	
	@RequestMapping(path="addFrogToDb.do",
			method=RequestMethod.POST)
	public ModelAndView addFrogToDb(Frog frog) {
	  frogDao.addFrogToDb(frog);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result1.jsp");
		return mv;
	}
	
	//**************************************************************
	
	
  @RequestMapping("route.do")
  public ModelAndView processData(@RequestParam("data") String s) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("result1.jsp");
    mv.addObject("frog", frogDao.getFrogByName(s));
    return mv;
  }
   
  @RequestMapping(path="deleteFrog.do",
		   params="name",
			method=RequestMethod.POST)
  public ModelAndView deleteTheFrog(String name)
 {
	  frogDao.deleteFrog(name);
	  	ModelAndView mv = new ModelAndView();
		mv.setViewName("result1.jsp");
		return mv; 
 }
  
  @RequestMapping(path="NewFrog.do",
			method=RequestMethod.POST)
	public ModelAndView newFrog(Frog frog) {
	  frogDao.addFrog(frog);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result1.jsp");
		return mv;
	}
  
  @RequestMapping(path="showAllFrogs.do",
			method=RequestMethod.GET)
  public ModelAndView getAllFrogs()
  {
	  frogDao.getAllFrogs();
	  	ModelAndView mv = new ModelAndView();
		mv.setViewName("showallfrogs.jsp");
		mv.addObject("AllFrog", frogDao.getAllFrogs());
		return mv; 
  }
  
  @RequestMapping(path="updateFrog.do",
			method=RequestMethod.POST)
public ModelAndView updateFrog(Frog frog)
{
	  frogDao.updateFrog(frog);
	  	ModelAndView mv = new ModelAndView();
		mv.setViewName("result1.jsp");
		mv.addObject(frog);
		return mv; 
}
  
  
  
}