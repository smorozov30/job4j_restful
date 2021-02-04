package ru.job4j.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.job4j.auth.AuthApplication;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AuthApplication.class)
@AutoConfigureMockMvc
class PersonControllerTest {

    @MockBean
    private PersonRepository persons;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenFindAllThenReturnListOgPersonInJSON() throws Exception {
        when(persons.findAll()).thenReturn(List.of(Person.of("login", "password")));

        MvcResult mvcResult = this.mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[{\"id\":0,\"login\":\"login\",\"password\":\"password\"}]";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenFindByIdZeroThenReturnPersonInJSONAndStatusOK() throws Exception {
        when(persons.findById(0)).thenReturn(Optional.of(Person.of("login", "password")));

        MvcResult mvcResult = this.mockMvc.perform(get("/person/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"id\":0,\"login\":\"login\",\"password\":\"password\"}";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenFindByIdOneThenReturnStatusIsNotFound() throws Exception {
        when(persons.findById(1)).thenReturn(Optional.empty());

        MvcResult mvcResult = this.mockMvc.perform(get("/person/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        String expected = "{\"id\":0,\"login\":null,\"password\":null}";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenCreateNewPersonThenReturnSavedPersonAndStatusCreated() throws Exception {
        Person person = Person.of("login", "password");
        when(persons.save(person)).thenReturn(person);

        String data = "{\"login\":\"login\",\"password\":\"password\"}";
        MvcResult mvcResult = this.mockMvc.perform(post("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String expected = "{\"id\":0,\"login\":\"login\",\"password\":\"password\"}";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenUpdatePersonThenReturnStatusOk() throws Exception {
        Person person = Person.of("login", "password");
        when(persons.save(person)).thenReturn(person);

        String data = "{\"login\":\"login\",\"password\":\"password\"}";
        this.mockMvc.perform(put("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void whenDeleteThenReturnStatusOk() throws Exception {
        this.mockMvc.perform(delete("/person/0"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}