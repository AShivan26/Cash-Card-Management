package com.example.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/*
@JsonTest, Using this annotation will disable full auto-configuration
 and instead apply only configuration relevant to JSON tests
 */
@JsonTest
public class CashCardJsonTest {

    @Autowired
    private JacksonTester<CashCard> json;

    /*
    JacksonTester is a convenience wrapper to the
    Jackson JSON parsing library.
    It handles serialization and deserialization of JSON objects.
    */

    @Test
    void cashCardSerializationTest() throws IOException {
        {
            CashCard cashCard = new CashCard(99L, 123.45);
            assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
            assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
            assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                    .isEqualTo(99);
            assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
            assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
                    .isEqualTo(123.45);
        }
    }

    @Test
    /*
    Test for Deserialization
    transforms data from a file or byte stream back into an object for our application.
    * */
    void cashCardDeserializationTest() throws IOException {
        String expected = """
           {
               "id":99,
               "amount":123.45
           }
           """;
        assertThat(json.parse(expected))
                .isEqualTo(new CashCard(99L, 123.45));
        assertThat(json.parseObject(expected).getId()).isEqualTo(99);
        assertThat(json.parseObject(expected).getAmount()).isEqualTo(123.45);
    }
}
