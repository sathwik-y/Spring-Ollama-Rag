    @Bean
    VectorStore vectorStore(EmbeddingModel embeddingModel){
        return new SimpleVectorStore(embeddingModel);
    }