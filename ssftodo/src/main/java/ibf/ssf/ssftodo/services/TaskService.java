package ibf.ssf.ssftodo.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf.ssf.ssftodo.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    public boolean hasKey(String key) {
        Optional<String> opt = taskRepo.get(key);
        return opt.isPresent();
    }

    public List<String> get(String key) {
        Optional<String> opt = taskRepo.get(key);
        List<String> list = new LinkedList<>();
        if (opt.isPresent())
            for (String t: opt.get().split("\\|"))
                list.add(t);
        return list;
    }

    public void save(String key, List<String> values) {
        String l = values.stream()
            .collect(Collectors.joining("|"));
        this.save(key, l);
    }
    public void save(String key, String value) {
        taskRepo.save(key, value);
    }
    
}
