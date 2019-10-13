package com.silionie.finance.transfers;

import com.silionie.FinanceApp;
import com.silionie.enums.TransferTypeEnum;
import com.silionie.finance.FinanceAppTest;
import com.silionie.model.Transfer;
import com.silionie.model.TransferType;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetTransferByAccountTest extends FinanceAppTest {


    @Test
    public void testGetTransferListByAccount() throws URISyntaxException
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
        assertThat(response.getBody().size()).isEqualTo(1);


        Transfer historicTransfer = response.getBody().get(0);

        assertThat(historicTransfer.getAmount()).isEqualTo(new BigDecimal(500));
        assertThat(historicTransfer.getTransferType().getName()).isEqualTo(TransferTypeEnum.INTRA.name());
        assertThat(historicTransfer.getSourceAccountNumber()).isEqualTo("GR12345467898900");
        assertThat(historicTransfer.getTargetAccountNumber()).isEqualTo("GRTH9878998766");


    }

}
