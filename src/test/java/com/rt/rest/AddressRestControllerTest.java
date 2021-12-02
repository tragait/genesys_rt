//package com.rt.rest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rt.model.Address;
//import com.rt.rest.controller.SensorRestController;
//import com.rt.service.SensorService;
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
//public class AddressRestControllerTest {
//        @Mock
//        private SensorService addressService;
//
//        private Address address;
//
//        @InjectMocks
//        private SensorRestController addressRestController;
//
//        @Autowired
//        private MockMvc mockMvc;
//
//        @BeforeEach
//        public void setup(){
//            mockMvc = MockMvcBuilders.standaloneSetup(addressRestController).build();
//            address = new Address(1, "street", "city", "state", "postal");
//        }
//
//        @Test
//        public void PutMappingOfAddress() throws Exception{
//                when(addressService.isExist(Integer.valueOf(1))).thenReturn(true);
//                when(addressService.saveAddress(any())).thenReturn(address);
//                when(addressService.getAddress(Integer.valueOf(1))).thenReturn(address);
//                mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/address").
//                        contentType(MediaType.APPLICATION_JSON).
//                        content(asJsonString(address))).
//                        andExpect(status().isOk());
//
//                verify(addressService,times(1)).isExist(Integer.valueOf(1));
//                verify(addressService,times(1)).saveAddress(address);
//        }
//
//        @Test
//        public void PutMappingOfAddressAlreadyExists() throws Exception{
//                when(addressService.isExist(Integer.valueOf(1))).thenReturn(false);
//                mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/address").
//                        contentType(MediaType.APPLICATION_JSON).
//                        content(asJsonString(address))).
//                        andExpect(status().isNotAcceptable());
//
//                verify(addressService,times(1)).isExist(Integer.valueOf(1));
//                verify(addressService,times(0)).saveAddress(address);
//
//        }
//
//        @Test
//        public void GetMappingOfSingleAddress() throws Exception {
//                when(addressService.getAddress(Integer.valueOf(1))).thenReturn(address);
//                when(addressService.isExist(Integer.valueOf(1))).thenReturn(true);
//                mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/address/1").
//                        contentType(MediaType.APPLICATION_JSON)).
//                        andDo(MockMvcResultHandlers.print()).
//                        andExpect(status().isOk());
//                verify(addressService).getAddress(Integer.valueOf(1));
//                verify(addressService,times(1)).getAddress(Integer.valueOf(1));
//        }
//
//        @Test
//        public void GetMappingOfAllAddress() throws Exception {
//                final List<Address> addressList= new ArrayList<>();
//                when(addressService.getAll()).thenReturn(addressList);
//                mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/address/getAll").
//                        contentType(MediaType.APPLICATION_JSON)).
//                        andDo(MockMvcResultHandlers.print()).
//                        andExpect(status().isOk());
//                verify(addressService).getAll();
//                verify(addressService,times(1)).getAll();
//        }
//
//        @Test
//        public void DeleteMappingOfAddress() throws Exception {
//                mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/address/1").
//                        contentType(MediaType.APPLICATION_JSON)).
//                        andDo(MockMvcResultHandlers.print()).
//                        andExpect(status().isOk());
//                verify(addressService).delete(Integer.valueOf(1));
//                verify(addressService,times(1)).delete(Integer.valueOf(1));
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
//            address = null;
//        }
//}
