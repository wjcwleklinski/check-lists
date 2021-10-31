package com.wjcwleklinski.listservice;


import com.wjcwleklinski.listservice.repository.CheckListRepository;
import com.wjcwleklinski.listservice.repository.EntryRepository;
import com.wjcwleklinski.listservice.repository.FileRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class
})
public class CommonResourceTest {

    @MockBean
    protected CheckListRepository checkListRepository;

    @MockBean
    protected EntryRepository entryRepository;

    @MockBean
    protected FileRepository fileRepository;

    @LocalServerPort
    private int randomServerPort;

    protected RestTemplate restTemplate = new RestTemplate();

    protected URI getUrl(String path) throws URISyntaxException {
        return new URI("http://localhost:" + randomServerPort + path);
    }
}
