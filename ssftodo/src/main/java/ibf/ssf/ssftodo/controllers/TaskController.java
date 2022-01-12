package ibf.ssf.ssftodo.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ibf.ssf.ssftodo.Constants;
import ibf.ssf.ssftodo.SsftodoApplication;
import ibf.ssf.ssftodo.services.TaskService;

@Controller
@RequestMapping(path="/task", produces=MediaType.TEXT_HTML_VALUE)
public class TaskController {

    private final Logger logger = Logger.getLogger(SsftodoApplication.class.getName());

    @Autowired
    private TaskService taskSvc;

    @PostMapping("save")
    public String postTaskSave(@RequestBody MultiValueMap<String, String> form) {

        String contents = form.getFirst("contents");

        logger.log(Level.INFO, "to be saved: '%s'".formatted(contents));

        taskSvc.save(Constants.TODO_KEY, contents);

        return "index";
    }

    @PostMapping
    public String postTask(@RequestBody MultiValueMap<String, String> form
            , Model model) {

        String taskName = form.getFirst("taskName");
        String contents = form.getFirst("contents");

        logger.log(Level.INFO, "contents: '%s'".formatted(contents));

        // Split the contents into List, delimited by |
        List<String> tasks = new LinkedList<>();
        if ((null != contents) && (contents.trim().length() > 0)) {
            // append new task to contents
            contents = "%s|%s".formatted(contents, taskName);
            tasks = Arrays.asList(contents.split("\\|"));
        } else {
            contents = taskName;
            tasks.add(contents);
        }

        logger.log(Level.INFO, "taskName: %s".formatted(taskName));
        logger.log(Level.INFO, "tasks: %s".formatted(tasks));

        model.addAttribute("contents", contents);
        model.addAttribute("tasks", tasks);
        
        return "index";
    }
    
}
