package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.data.SpittleRepository;

@Controller
@RequestMapping(value = {"/spittles"})
public class SpittleController {

	private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;
	
	private SpittleRepository spittleRepository;
	
	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model,
			@RequestParam(name = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(name = "count", defaultValue = "20") int count) {
		
		model.addAttribute(spittleRepository.findSpittles(max, count));
		return "spittles";
	}
	
	@RequestMapping(value = {"/{spittleId}"}, method = RequestMethod.GET)
	public String spittle(Model model, @PathVariable long spittleId) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}
	
}
