package com.silionie.finance.transfers;

import com.silionie.enums.TransferTypeEnum;
import com.silionie.finance.FinanceAppTest;
import com.silionie.exceptions.TransferException;
import com.silionie.model.Transfer;
import com.silionie.model.TransferType;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.fail;
import static org.junit.Assert.assertEquals;

public class SendNotAcceptableTransfersTest extends FinanceAppTest {


    @Test
    public void testSendOver1000Transfer() throws URISyntaxException
    {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/account/1/transfers";
        URI uri = new URI(baseUrl);


        ResponseEntity<List<Transfer>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transfer>>(){});

        //Verify request succeed
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Transfer historicTransfer = response.getBody().get(0);
        BigDecimal initialAccountBalance = historicTransfer.getAccount().getBalance();

        Transfer transfers = new Transfer(new TransferType(TransferTypeEnum.INTRA.name()),"GR12345467898900",
                "GRTH9878998766", new BigDecimal(1800));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> httpEntity = new HttpEntity<>(transfers, headers);

        try {
            restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    httpEntity,
                    Transfer.class);
            //Verify request succeed
            fail("Should have thrown TransferException");
        } catch (TransferException ex) {
            assertEquals("User has a limit of 1000€ per transfer", ex.getMessage());
        }



        ResponseEntity<List<Transfer>> responseAfterTransfers = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transfer>>(){});

        //Verify request succeed
        assertThat(responseAfterTransfers.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseAfterTransfers.getBody().size()).isEqualTo(1);


        BigDecimal updatedBalance = responseAfterTransfers.getBody().get(0).getAccount().getBalance();

        assertEquals(initialAccountBalance, updatedBalance);
    }

}
