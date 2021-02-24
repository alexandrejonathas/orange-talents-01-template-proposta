package br.com.zup.propostas.comum;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    @Autowired
    private CryptDecryptUtil dataCryptUtil;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return dataCryptUtil.encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dataCryptUtil.decode(dbData);
    }
}
