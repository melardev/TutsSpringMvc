package com.melardev.controllers;

import com.melardev.models.MockModel;
import com.melardev.models.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/bindings/init_binder")
public class InitBinderController {

    @InitBinder
    public void extendBinding(WebDataBinder binder) {
        // You can also register formatters with WebMvcConfigurer, the difference is that
        // they will be globally available whereas these ones are only available for this controller

        DateFormat dateFormat = new SimpleDateFormat("yyyy**MM**dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        DateFormatter dateFormatter = new DateFormatter("dd-MM-yyyy");
        binder.addCustomFormatter(dateFormatter, "birthDate"); // Only birthDate
        binder.addCustomFormatter(dateFormatter); // Will be applied on all fields

    }

    @GetMapping
    public String home() {
        return "bindings/init_binder";
    }

    @GetMapping("/demo1")
    public String getInitBinderDemo1(Model model) {
        model.addAttribute("user", new User());
        return "bindings/init_binder1";
    }

    @PostMapping("/demo1")
    public String processDemo1(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors())
            return "bindings/init_binder1";
        return "bindings/init_binder";
    }

    @InitBinder(value = "mockModel")
    public void extendBinding2(WebDataBinder binder) {

        NumberStyleFormatter numberFormatter = new NumberStyleFormatter();
        numberFormatter.setPattern("#,###.#");
        binder.addCustomFormatter(numberFormatter, "salary");

        CurrencyStyleFormatter currencyFormatter = new CurrencyStyleFormatter();
        currencyFormatter.setCurrency(Currency.getInstance(Locale.FRANCE));
        binder.addCustomFormatter(currencyFormatter, "money");
    }

    @GetMapping("/demo2")
    public String getDemo2(Model model) {
        model.addAttribute("mockModel", new MockModel(12034.23, 130002.11));
        return "bindings/init_binder2";
    }
}
