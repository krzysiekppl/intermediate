package pl.sda.intermediate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.intermediate.categories.CategoryDTO;
import pl.sda.intermediate.categories.CategorySearchService;
import pl.sda.intermediate.customers.*;

import java.util.List;
import java.util.Map;

@Controller
public class OnlyOneController {

    @Autowired
    private UserValidationService userValidationService;
    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private CategorySearchService categorySearchService;

    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginEffect(UserLoginDTO userLoginDTO, Model model) {
        boolean logged = userLoginService.loginUser(userLoginDTO);
        if (logged) {
            return "index";
        }
        model.addAttribute("form", new UserLoginDTO());
        model.addAttribute("error", "Błąd logowania");
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("form", new UserLoginDTO());
        return "loginForm";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutForm(Model model) {
        model.addAttribute("form", new UserLoginDTO());
        return "loginForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerEffects(UserRegistrationDTO userRegistrationDTO, Model model) {
        Map<String, String> errorsMap = userValidationService.validateUser(userRegistrationDTO);
        model.addAttribute("form", userRegistrationDTO);
        if (errorsMap.isEmpty()) {
            userRegistrationService.registerUser(userRegistrationDTO);
            return "registerEffect";
        } else {
            model.addAllAttributes(errorsMap);
            model.addAttribute("countries", Countries.values());
            return "registerForm";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("countries", Countries.values());
        model.addAttribute("form", new UserRegistrationDTO());
        return "registerForm";
    }

    @RequestMapping(value = "/categories")
    public String categories(String searchText, Model model) {
        List<CategoryDTO> categories = categorySearchService.filterCategories(searchText);
        model.addAttribute("catsdata", categories);
        return "catspage"; //takiego htmla chcemy otworzyc
    }

}
