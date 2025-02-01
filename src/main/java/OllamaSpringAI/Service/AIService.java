package OllamaSpringAI.Service;


import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    @Autowired
    VectorStore vectorStore;

    @Value("classpath:/story.txt")
    private Resource textResource;

    
    public VectorStore loadDataInVectorStore(){
        
        TextReader textReader = new TextReader(textResource);
        
        textReader.getCustomMetadata().put("filename", "sample.txt");
        
        List<Document> documents0 = textReader.get();
                
        List<Document> documents = new TokenTextSplitter().apply(documents0);
        
        vectorStore.add(documents);
        
        return vectorStore;
    }
    
}
// public List<Document> getQueryDocs(String query) {

//     // read text file
//     TextReader textReader = new TextReader(textResource);

//     // convert the text into Document object
//     List<Document> documents = textReader.get();

//     System.out.println(documents);

//     // chunking of text
//     var textSplitter = new TokenTextSplitter();

//     // add it into vector store
//     vectorStore.add(textSplitter.apply(documents));

//     // vector search
//     List<Document> results = vectorStore.similaritySearch(query);

//     return results;

// }

// public List<Document> getQueryDocsSR(String query) {

//     // read text file
//     TextReader textReader = new TextReader(textResource);

//     // convert the text into Document object
//     List<Document> documents = textReader.get();

//     // chunking of text
//     var textSplitter = new TokenTextSplitter();

//     // add it into vector store
//     vectorStore.add(textSplitter.apply(documents));
 
//          // vector search
//     SearchRequest searchRequest = SearchRequest.builder().similarityThreshold(0.8d).topK(6).build();
//     List<Document> results = vectorStore.similaritySearch(searchRequest);

//     return results;
// }