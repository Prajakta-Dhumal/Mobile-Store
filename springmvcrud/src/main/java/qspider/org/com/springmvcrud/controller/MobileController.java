package qspider.org.com.springmvcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import qspider.org.com.springmvcrud.domain.Mobile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class MobileController {
    List<Mobile> mobileList=new ArrayList<>();
    {
        mobileList.add(new Mobile("IPHONE 14","APPLE",60000.0));
        mobileList.add(new Mobile("IPHONE 13","APPLE",56000.25));
    }
    @GetMapping("/")
    public String getMobile(Model model)
    {
        model.addAttribute("mobiles",mobileList);
        return "mobiles";

    }

    @GetMapping("/addmobileform")
    public String getMobileForm(Model model){
        Mobile m1=new Mobile();
        model.addAttribute("mobile",m1);
        return "addmobileform";
    }

    @PostMapping("/addmobile")
    public String addMobileDetails(Mobile mobile){
        mobileList.add(mobile);
        return "redirect:/";
    }
    @GetMapping("/updatemobile/{model}")
    public String getUpdateForm(@PathVariable(value = "model")String modelName,Model model)
    {
        for (Mobile m1:mobileList){
            if (m1.getModelName().equals(modelName)){
                model.addAttribute("mobile",m1);
            }
        }
       return "updatemobileform";
    }

    @PostMapping("/modifymobile")
    public String modifyMobile(Mobile mobile){
        for (int i=0;i<mobileList.size();i++){
            Mobile m=mobileList.get(i);
            if (m.getModelName().equals(mobile.getModelName())){
                mobileList.set(i,mobile);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/deletemobile/{model}")
    public String deleteMobile(@PathVariable(value = "model") String modelName)
    {
        Iterator<Mobile> itr=mobileList.iterator();
        while (itr.hasNext()){
            if (itr.next().getModelName().equals(modelName)){
                itr.remove();
            }
        }
        return "redirect:/";

    }
}
