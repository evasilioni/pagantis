package com.silionie.finance.transfers;

import com.silionie.enums.TransferTypeEnum;
import com.silionie.exceptions.MyErrorHandler;
import com.silionie.finance.FinanceAppTest;
import com.silionie.exceptions.TransferException;
import com.silionie.model.Account;
import com.silionie.model.Transfer;
import com.silionie.model.TransferType;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
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
        } catch(HttpStatusCodeException e){
            String errorpayload = e.getResponseBodyAsString();
            assertThat(errorpayload).contains("User has a limit of 1000€ per transfer");
        }catch (TransferException ex) {
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


    @Test
    public void testSendOverBalanceTransfer() throws URISyntaxException
    {
       restTemplate.setErrorHandler(new MyErrorHandler());
        final String baseUrl = "http://localhost:" + randomServerPort + "/bank/1/customers/3";
        URI uri = new URI(baseUrl);


        ResponseEntity<List<Account>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Account>>(){});

        //Verify request succeed
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Account account = response.getBody().stream().filter(a -> a.getAccountNumber().equals("NGB0987654321")).findFirst().get();

        Transfer transfers = new Transfer(new TransferType(TransferTypeEnum.INTRA.name()),"GR12345467898900",
                "GRTH9878998766", new BigDecimal(100).add(account.getBalance()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> httpEntity = new HttpEntity<>(transfers, headers);

        try {
            restTemplate.exchange(
                    new URI("http://localhost:" + randomServerPort + "/account/"+account.getId()+"/transfers"),
                    HttpMethod.POST,
                    httpEntity,
                    Transfer.class);
        } catch(HttpStatusCodeException e){
            String errorpayload = e.getResponseBodyAsString();
            assertThat(errorpayload).contains("User's balance account does not meet the requirements of transfer");
        }catch (TransferException ex) {
            assertEquals("User's balance account does not meet the requirements of transfer", ex.getMessage());
        }


        ResponseEntity<List<Transfer>> responseAfterTransfers = restTemplate.exchange(
                new URI("http://localhost:" + randomServerPort + "/account/"+account.getId()+"/transfers"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transfer>>(){});

        //Verify request succeed
        assertThat(responseAfterTransfers.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseAfterTransfers.getBody().size()).isEqualTo(0);
    }

}
