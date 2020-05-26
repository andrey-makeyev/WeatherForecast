package lv.citadele.weatherapp.controllers;

import lv.citadele.weatherapp.entities.Forecast;
import lv.citadele.weatherapp.services.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CRUDController {

    private ForecastService forecastService;

    @Autowired
    public void setForecastService(ForecastService forecastService) {
        this.forecastService = forecastService;
    }


    @RequestMapping(value = "/forecasts", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("forecasts", forecastService.listAllForecasts());
        System.out.println("Returning forecasts:");
        return "forecasts";
    }

    @RequestMapping("forecast/{id}")
    public String showForecast(@PathVariable Integer id, Model model) {
        model.addAttribute("forecast", forecastService.getForecastById(id));
        return "forecastshow";
    }

    @RequestMapping("forecast/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("forecast", forecastService.getForecastById(id));
        return "forecastform";
    }

    @RequestMapping("forecast/new")
    public String newCity(Model model) {
        model.addAttribute("forecast", new Forecast());
        return "forecastform";
    }

    @RequestMapping(value = "forecast", method = RequestMethod.POST)
    public String saveForecast(Forecast forecast) {
        forecastService.saveForecast(forecast);
        return "redirect:/forecast/" + forecast.getId();
    }

    @RequestMapping("forecast/delete/{id}")
    public String delete(@PathVariable Integer id) {
        forecastService.deleteForecast(id);
        return "redirect:/forecasts";
    }

}
