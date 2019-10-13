package com.silionie.finance.transfers;

import com.silionie.enums.TransferTypeEnum;
import com.silionie.finance.FinanceAppTest;
import com.silionie.model.Transfer;
import com.silionie.model.TransferType;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class SendTransferToAccountTest extends FinanceAppTest {


    @Test
    public void testSendTransfersToAccount() throws URISyntaxException
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

        List<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(new TransferType(TransferTypeEnum.INTRA.name()),"GR12345467898900",
                "GRTH9878998766", new BigDecimal(800)));
        transfers.add(new Transfer(new TransferType(TransferTypeEnum.INTER.name()),"GR12345467898900",
                "BNHTTH9878998766", new BigDecimal(650)));
        transfers.add(new Transfer(new TransferType(TransferTypeEnum.INTRA.name()),"GR12345467898900",
                "GRTH98789989087", new BigDecimal(410)));

        transfers.forEach(t -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Transfer> httpEntity = new HttpEntity<>(t, headers);

            ResponseEntity<Transfer> responseTransfer = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    httpEntity,
                    Transfer.class);
            //Verify request succeed
            assertThat(responseTransfer.getStatusCodeValue()).isEqualTo(202);
        });


        ResponseEntity<List<Transfer>> responseAfterTransfers = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transfer>>(){});

        //Verify request succeed
        assertThat(responseAfterTransfers.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseAfterTransfers.getBody().size()).isEqualTo(4);


        BigDecimal updatedBalance = responseAfterTransfers.getBody().get(0).getAccount().getBalance();

        assertEquals(
        initialAccountBalance.subtract(new BigDecimal(800))
                .subtract(new BigDecimal(655))
                .subtract(new BigDecimal(410)), updatedBalance);
    }

}
