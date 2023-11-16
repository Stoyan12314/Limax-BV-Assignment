package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.buisness.ArticleService;
import org.example.domain.Article;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @Test
    void createArticleTest() throws Exception {
        Article article = new Article();
        Long expectedArticleId = 1L; // Example ID
        when(articleService.saveArticle(any(Article.class))).thenReturn(expectedArticleId);

        mockMvc.perform(MockMvcRequestBuilders.post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(article)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedArticleId));

        verify(articleService, times(1)).saveArticle(any(Article.class));
    }

    @Test
    void getArticleByIdTest() throws Exception {
        Long id = 1L;
        Article article = new Article(); // populate article fields as needed
        when(articleService.getArticle(id)).thenReturn(article);

        mockMvc.perform(MockMvcRequestBuilders.get("/articles/" + id))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(article.getArticleId()));

        verify(articleService, times(1)).getArticle(id);
    }

    @Test
    void getAllArticlesTest() throws Exception {
        Article article1 = new Article();
        Article article2 = new Article();
        when(articleService.getAllArticles()).thenReturn(List.of(article1, article2));

        mockMvc.perform(MockMvcRequestBuilders.get("/articles"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));

        verify(articleService, times(1)).getAllArticles();
    }

    @Test
    void deleteArticleTest() throws Exception {
        Long id = 1L;
        doNothing().when(articleService).deleteArticle(id);

        mockMvc.perform(delete("/articles/" + id))
                .andDo(print())
                .andExpect(status().isOk());

        verify(articleService, times(1)).deleteArticle(id);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    }