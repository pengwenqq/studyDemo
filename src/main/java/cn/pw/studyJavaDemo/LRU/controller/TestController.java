package cn.pw.studyJavaDemo.LRU.controller;


import cn.pw.studyJavaDemo.LRU.client.LRUCacheClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("pwLru")
public class TestController {
    public static LRUCacheClient lruCacheClient = new LRUCacheClient(3);

    @RequestMapping("put")
    public Boolean putCache(String key,String value){
        lruCacheClient.put(key,value);
        return true;
    }

    @RequestMapping("get")
    public String get(String key){
        return lruCacheClient.get(key);
    }

    @RequestMapping("showList")
    public String showLikedList(){
        return lruCacheClient.showLikedList();
    }

}
