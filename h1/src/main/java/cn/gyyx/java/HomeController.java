package cn.gyyx.java;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gyyx.java.model.Game;
import cn.gyyx.java.bll.GameBLL;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate =  "222222";// dateFormat.format(date);
		
		GameBLL gameBll = new GameBLL();
		//Game theGmme = gameBll.GetGameById(1);
		
		List<Game> list = gameBll.GetGameList();
		model.addAttribute("list", list);
		
		//model.addAttribute("gameName", theGmme.getGameName() );
		//model.addAttribute("gameDes", theGmme.getGameDes() );
		return "home";
	}
	
	@RequestMapping(value="/postNewGame" , method = RequestMethod.POST)
	public String postNewGame(@RequestParam(value="gameName") String gameName,
			@RequestParam(value="gameDes") String gameDes,
			HttpServletResponse response,Model model){
		GameBLL gameBll = new GameBLL();
		Game gameRes = gameBll.AddNewGame(gameName, gameDes);
		
		List<Game> list = gameBll.GetGameList();
		model.addAttribute("list", list);
		
		if(gameRes != null){
			return "home";
		}
		return "home";
	}
	
	@RequestMapping(value="/ModifyGame" , method = RequestMethod.GET)
	public String ModifyGame( int id,
			HttpServletResponse response,Model model){
		
		GameBLL gameBll = new GameBLL();
		Game theGmme = gameBll.GetGameById(id);
		model.addAttribute("game", theGmme);
		
		if(theGmme != null){
			return "ModifyGame";
		}
		return "home";
	}
	
	@RequestMapping(value="/ModifyGame" , method = RequestMethod.POST)
	public String ModifyGame(@RequestParam(value="gameName") String gameName,
			@RequestParam(value="gameDes") String gameDes,
			@RequestParam(value="id") int id,
			HttpServletResponse response,Model model){
		GameBLL gameBll = new GameBLL();
		Game gameRes = gameBll.updateGame(gameName, gameDes,id);
		
		List<Game> list = gameBll.GetGameList();
		model.addAttribute("list", list);
		
		if(gameRes != null){
			return "home";
		}
		return "home";
	}
	
}
