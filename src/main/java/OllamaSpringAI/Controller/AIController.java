package OllamaSpringAI.Controller;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import OllamaSpringAI.Service.AIService;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    AIService aiService;
    
    @Autowired
    ChatClient chatClient;
    
    @PostMapping("/response")
    public Map<String, String> getResponse(@RequestParam String message){
        
        VectorStore vectorStore = aiService.loadDataInVectorStore();
        
        return Map.of("response",chatClient.prompt()
        .advisors(new QuestionAnswerAdvisor(vectorStore))
        .user(message).call().content());
    }
}
