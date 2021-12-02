//package com.rt.rest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rt.model.Address;
//import com.rt.model.Person;
//import com.rt.service.PersonService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class PersonRestControllerTest {
//        @Mock
//        private PersonService personService;
//
//        private Person person;
//
//        @InjectMocks
//        private PersonRestController personRestController;
//
//        @Autowired
//        private MockMvc mockMvc;
//
//        @BeforeEach
//        public void setup(){
//            mockMvc = MockMvcBuilders.standaloneSetup(personRestController).build();
//            person = new Person(1, "myfirstname", "mylastname");
//        }
//
//        @Test
//        public void PostMappingOfPerson() throws Exception{
//
//                when(personService.savePerson(any())).thenReturn(person);
//                mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/person").
//                        contentType(MediaType.APPLICATION_JSON).
//                        content(asJsonString(person))).
//                        andExpect(status().isCreated());
//
//                verify(personService,times(1)).savePerson(person);
//        }
//
//        @Test
//        public void PostMappingOfAddressToPerson() throws Exception{
//                final Address address = new Address(1, "street", "city", "state", "postal");
//                mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/person/1/addAddress").
//                        contentType(MediaType.APPLICATION_JSON).
//                        content(asJsonString(address))).
//                        andDo(MockMvcResultHandlers.print()).
//                        andExpect(status().isOk());
//                verify(personService).addAddressToPerson(Integer.valueOf(1), address);
//                verify(personService,times(1)).addAddressToPerson(Integer.valueOf(1), address);
//        }
//
//        @Test
//        public void PutMappingOfPerson() throws Exception{
//
//                when(personService.savePerson(any())).thenReturn(person);
//                mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/person").
//                        contentType(MediaType.APPLICATION_JSON).
//                        content(asJsonString(person))).
//                        andExpect(status().isCreated());
//
//                verify(personService,times(1)).savePerson(any());
//        }
//
//        @Test
//        public void GetMappingOfSinglePerson() throws Exception {
//                when(personService.getPerson(Integer.valueOf(1))).thenReturn(person);
//                when(personService.isExist(Integer.valueOf(1))).thenReturn(true);
//                mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person/1").
//                        contentType(MediaType.APPLICATION_JSON)).
//                        andDo(MockMvcResultHandlers.print()).
//                        andExpect(status().isOk());
//                verify(personService).getPerson(Integer.valueOf(1));
//                verify(personService,times(1)).getPerson(Integer.valueOf(1));
//        }
//
//        @Test
//        public void GetMappingOfSinglePersonDontExist() throws Exception {
//                when(personService.isExist(Integer.valueOf(1))).thenReturn(false);
//                mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person/1").
//                        contentType(MediaType.APPLICATION_JSON)).
//                        andDo(MockMvcResultHandlers.print()).
//                        andExpect(status().isNotFound());
//                verify(personService,times(0)).getPerson(Integer.valueOf(1));
//        }
//
//        @Test
//        public void GetMappingOfAllPerson() throws Exception {
//                final List<Person> personList= new ArrayList<>();
//                when(personService.getAll()).thenReturn(personList);
//                mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person/getAll").
//                        contentType(MediaType.APPLICATION_JSON)).
//                        andDo(MockMvcResultHandlers.print()).
//                        andExpect(status().isOk());
//                verify(personService).getAll();
//                verify(personService,times(1)).getAll();
//        }
//
//        @Test
//        public void DeleteMappingOfAPerson() throws Exception {
//                when(personService.isExist(Integer.valueOf(1))).thenReturn(true);
//                mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/person/1").
//                        contentType(MediaType.APPLICATION_JSON)).
//                        andDo(MockMvcResultHandlers.print()).
//                        andExpect(status().isOk());
//                verify(personService).delete(Integer.valueOf(1));
//                verify(personService,times(1)).delete(Integer.valueOf(1));
//        }
//
//
//        private static String asJsonString(final Object obj){
//                try{
//                        return new ObjectMapper().writeValueAsString(obj);
//                }catch (Exception e){
//                        throw new RuntimeException(e);
//                }
//        }
//
//        @AfterEach
//        void tearDown() {
//            person = null;
//        }
//}
