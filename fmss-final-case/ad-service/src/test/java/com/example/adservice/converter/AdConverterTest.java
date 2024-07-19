package com.example.adservice.converter;

import com.example.adservice.dto.response.AdResponse;
import com.example.adservice.model.Ad;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AdConverterTest {

    @InjectMocks
    private AdConverter adConverter;

    @Test
    void testToResponse_shouldReturnAdResponse() {


        Ad ad = Ad.builder()
                .id(1L)
                .title("Test")
                .description("Test")
                .build();

        AdResponse response = adConverter.toResponse(ad);

        assertEquals(ad.getId(), response.getId());
        assertEquals(ad.getTitle(), response.getTitle());
        assertEquals(ad.getDescription(), response.getDescription());

    }

    @Test
    void testToResponse_shouldReturnAdResponseList() {


        List<Ad> ads = List.of(new Ad(),new Ad());

        List<AdResponse> response = adConverter.toResponse(ads);

        assertEquals(ads.size(),response.size());

    }
}
