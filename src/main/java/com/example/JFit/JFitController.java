package com.example.JFit;

import com.example.JFit.food.Food;
import com.example.JFit.food.FoodRepository;
import com.example.JFit.walk.Walk;
import com.example.JFit.walk.WalkRepository;
import com.example.JFit.weight.Weight;
import com.example.JFit.weight.WeightRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.stream.IntStream;

@Controller
public class JFitController {

    FoodRepository foodRepository;
    WeightRepository weightRepository;
    WalkRepository walkRepository;

    public JFitController(FoodRepository foodRepository, WeightRepository weightRepository, WalkRepository walkRepository) {
        this.foodRepository = foodRepository;
        this.weightRepository = weightRepository;
        this.walkRepository = walkRepository;
    }

    @GetMapping("/signupFood")
    public String showSignUpForm(Food food) {
        return "add-food";
    }

    @GetMapping("/signupWalk")
    public String showSignUpWalkForm(Walk walk) {
        return "add-walk";
    }

    @GetMapping("/signupWeight")
    public String showSignUpWeightForm(Weight weight) {
        return "add-weight";
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showLists(Model model) {
//        addTestData();
        model.addAttribute("foods", foodRepository.findAllByFoodDateBetween(
                LocalDate.now().minusDays(1),
                LocalDate.now(),
                Sort.by(Sort.Direction.ASC, "foodDate"))
        );
        model.addAttribute("walks", walkRepository.findAllByWalkDateBetween(
                LocalDate.now().minusDays(7),
                LocalDate.now(),
                Sort.by(Sort.Direction.ASC, "walkDate"))
        );
        model.addAttribute("weights", weightRepository.findAllByWeightDateBetween(
                LocalDate.now().minusDays(7),
                LocalDate.now(),
                Sort.by(Sort.Direction.ASC, "weightDate"))
        );
        model.addAttribute("allFoods", foodRepository.findAll());
        model.addAttribute("allWalks", walkRepository.findAll());
        model.addAttribute("allWeights", weightRepository.findAll());
        return "index";
    }

//    private void addTestData() {
//        LocalDate date = LocalDate.now();
//        for (int x = 0; x < 12; x++) {
//            date = date.minusDays(1);
//            Weight weight = new Weight();
//            weight.setPounds(x);
//            weight.setWeightDate(date);
//            weightRepository.save(weight);
//
//            Walk walk = new Walk();
//            walk.setDistance(x);
//            walk.setTime(x + 12);
//            walk.setWalkDate(date);
//            walkRepository.save(walk);
//
//            Food food = new Food();
//            food.setFoodDate(date);
//            food.setCalories(x + 100);
//            food.setDescription("Random food " + x);
//            foodRepository.save(food);
//        }
//    }

    @PostMapping("/addfood")
    public String addFood(@Validated Food food, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-food";
        }

        food.setFoodDate(LocalDate.now());
        foodRepository.save(food);
        return "redirect:/index";
    }

    @PostMapping("/addwalk")
    public String addWalk(@Validated Walk walk, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-walk";
        }

        walk.setWalkDate(LocalDate.now());
        walkRepository.save(walk);
        return "redirect:/index";
    }

    @PostMapping("/addweight")
    public String addWeight(@Validated Weight weight, BindingResult result, Model model) {
        System.out.println("here1");
        if (result.hasErrors()) {
            return "add-weight";
        }

        weight.setWeightDate(LocalDate.now());
        System.out.println("here2 " + weightRepository.save(weight));;
        return "redirect:/index";
    }

    @GetMapping("/deleteFood/{id}")
    public String deleteFood(@PathVariable("id") long id, Model model) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid food Id:" + id));
        foodRepository.delete(food);
        return "redirect:/index";
    }

    @GetMapping("/deleteWalk/{id}")
    public String deleteWalk(@PathVariable("id") long id, Model model) {
        Walk walk = walkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid walk Id:" + id));
        walkRepository.delete(walk);
        return "redirect:/index";
    }

    @GetMapping("/deleteWeight/{id}")
    public String deleteWeight(@PathVariable("id") long id, Model model) {
        Weight weight = weightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid weight Id:" + id));
        weightRepository.delete(weight);
        return "redirect:/index";
    }
}
