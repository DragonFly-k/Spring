package by;
import by.model.Scooter;
import by.repository.ScooterRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ApplicationTests {

    @MockBean
    ScooterRepository scooterRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetScootersFromRepository() {
        List<Scooter> scooters = Arrays.asList(
                new Scooter("Test 1",1),
                new Scooter("Test 2",2)
        );

        when(scooterRepository.findAll()).thenReturn(scooters);

        Assert.assertEquals(scooterRepository.findAll(), scooters);
    }

    @Test
    public void testGetScooters() throws Exception {
        setUp();
        List<Scooter> scooters = Arrays.asList(
                new Scooter("Test 1",1),
                new Scooter("Test 2",2)
        );

        when(scooterRepository.findAll()).thenReturn(scooters);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/scooter/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }
}