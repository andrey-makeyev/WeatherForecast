package lv.aqal.weatherforecast.controllers;

import lv.aqal.weatherforecast.entities.Forecast;
import lv.aqal.weatherforecast.services.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * CRUD controller.
 */
@Controller
public class CRUDController {

    private ForecastService forecastService;

    @Autowired
    public void setForecastService(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    /**
     * List all forecasts.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/forecasts", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("forecasts", forecastService.listAllForecasts());
        System.out.println("Returning forecasts:");
        return "forecasts";
    }

    /**
     * View a specific forecast by its id.
     *
     * @param id
     * @param model
     * @return
     */
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

    /**
     * New forecast.
     *
     * @param model
     * @return
     */
    @RequestMapping("forecast/new")
    public String newCity(Model model) {
        model.addAttribute("forecast", new Forecast());
        return "forecastform";
    }

    /**
     * Save forecast to database.
     *
     * @param forecast
     * @return
     */
    @RequestMapping(value = "forecast", method = RequestMethod.POST)
    public String saveForecast(Forecast forecast) {
        forecastService.saveForecast(forecast);
        return "redirect:/forecast/" + forecast.getId();
    }

    /**
     * Delete forecast by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("forecast/delete/{id}")
    public String delete(@PathVariable Integer id) {
        forecastService.deleteForecast(id);
        return "redirect:/forecasts";
    }

}
